package edu.hiro.hcv.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Sets;

import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.morphia.SequenceRepository;
import edu.hiro.hcv.neo4j.FeatureNode;
import edu.hiro.hcv.neo4j.RefNode;
import edu.hiro.hcv.neo4j.RefNodeRepository;
import edu.hiro.hcv.neo4j.SequenceNode;
import edu.hiro.hcv.neo4j.SequenceNodeRepository;
import edu.hiro.hcv.neo4j.TaxonNode;
import edu.hiro.hcv.neo4j.TaxonNodeRepository;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.util.Neo4jHelper;
import edu.hiro.util.RandomHelper;
import edu.hiro.util.StringHelper;

@Service("setupService")
@Transactional("neo4jTransactionManager")
public class SetupServiceImpl implements SetupService
{	
	@Resource(name="sequenceService")
	private SequenceService sequenceService; 
	
	@Resource(name="sequenceRepository")
	private SequenceRepository sequenceRepository;
	
	@Resource(name="graphDatabaseService")
	private GraphDatabaseService graphDatabaseService;

	@Autowired
	private Neo4jTemplate neo4jTemplate;
	
	@Autowired
	private SequenceNodeRepository sequenceNodeRepository;
	
	@Autowired
	private TaxonNodeRepository taxonNodeRepository;
	
	@Autowired
	private RefNodeRepository refNodeRepository;

	
	public void updateTaxa()
	{
		Query<Sequence> query=sequenceRepository.createQuery().retrievedFields(true,"taxon");
		Set<Integer> taxids=Sets.newHashSet();
		for (Sequence sequence : query.asList())
		{
			System.out.println("taxon="+sequence.getTaxon_id());
			if (sequence.getTaxon_id()!=null)
				taxids.add(sequence.getTaxon_id());
		}
		System.out.println("taxonids: "+StringHelper.join(taxids,","));
		Map<Integer,TaxonNode> taxa=TaxonBuilder.getTaxa(taxids);    	
    	for (TaxonNode taxon : taxa.values())
    	{
    		if (taxon.getParent_id()==null)
    			continue;
    		TaxonNode parent=taxa.get(taxon.getParent_id());
    		taxon.setParent(parent);
    		parent.add(taxon);
    		System.out.println("creating relationsip between nodes "+taxon.getId()+" and "+taxon.getParent_id());
    	}
    	
    	for (TaxonNode taxon : taxa.values())
    	{
    		System.out.println("saving taxon node: "+taxon.toString());
    		taxonNodeRepository.save(taxon);
    	}

	}

	public void updateRefs()
	{
		Query<Sequence> query=sequenceRepository.createQuery();
		Set<Integer> refids=Sets.newHashSet();
		for (Sequence sequence : query.asList())
		{
			for (Integer refid : sequence.getRefs())
			{
				System.out.println("ref="+refid);
				refids.add(refid);
			}
		}
		
		Map<Integer,RefNode> map=RefBuilder.getRefs(refids);
		for (RefNode ref : map.values())
		{
			System.out.println("saving ref: "+ref.toString());
			refNodeRepository.save(ref);
		}
		System.out.println("refids: "+StringHelper.join(refids,","));
	}
	
	public void loadGenbankFile(String filename)
	{
		List<SequenceNode> sequences=GenbankSequenceBuilder.parseFile(filename);
		for (SequenceNode sequence : sequences)
		{
			System.out.println("saving sequence: "+sequence.getAccession());
			neo4jTemplate.save(sequence);
			for (FeatureNode feature : sequence.getFeatures())
			{
				System.out.println("saving feature: "+feature.getType());
				neo4jTemplate.save(feature);
			}
		}
		System.out.println("finished loading");
	}
	
	public void loadSampleData(int num)
	{
		System.out.println("Loading sample data");
		for (int i=0;i<num; i++)
		{			
			sequenceNodeRepository.save(new SequenceNode("S"+RandomHelper.randomInteger(1000),"acgtcttgctgtgctgctgctacctgtgctgctgctactgctactgctgctacctgctgctgctacgttgctgctgcttgctgctgctacaccacgtctcgtc"));
		}
		System.out.println("Finished");
	}
	
	public void clearDatabase()
    {
		Neo4jHelper.clearDatabase(neo4jTemplate);
    }
	
	/*
	public void loadSequences(String filename) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/batch-setup.xml");
		JobLauncher jobLauncher = context.getBean(JobLauncher.class);
		Job job = (Job) context.getBean("loadSequences");

		JobParameters jobparams=new JobParametersBuilder().addString("resource",filename).toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job,jobparams);
		System.out.println("Job ended with status: "+jobExecution.getExitStatus());
	}
	*/
	
	/*
	public void loadSequences(String filename) {
		JobExecution jobExecution = runJob("loadSequences","resource",filename);
		System.out.println("Job ended with status: "+jobExecution.getExitStatus());
	}
	
	private JobExecution runJob(String id, Object...args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
			"/META-INF/spring/batch-setup.xml");
		return SpringBatchHelper.runJob(context, id, args);		
	}
	
	public void loadXml(String xml, CMessageWriter writer)
	{
		try
		{
			CXmlDataReader xmlloader=new CXmlDataReader(writer);
			xmlloader.loadXml(xml);
		}
		catch(Exception e)
		{
			CFileHelper.writeFile("c:/setup.xml",xml);
			throw new CException(e);
		}
	}

	public void loadXmlFromFile(String filename, CMessageWriter writer)
	{
		loadXml(CFileHelper.readFile(filename),writer);
		writer.message("Finished loading resources from file: "+filename);
	}
	
	public void loadXmlFromFolder(String folder, CMessageWriter writer)
	{
		String xml=CXmlHelper.mergeXmlFiles(folder,CXmlDataReader.ROOT);
		loadXml(xml,writer);
		writer.message("Finished loading resources from folder: "+folder);
	}
	
	public void validate(String xml, String schema, CMessageWriter writer)
	{
		try
		{
			String xsd=CFileHelper.getResource(schema);
			CXmlHelper.validate(xml,xsd);
		}
		catch(CXmlValidationException e)
		{
			writer.message(e.getMessage());
		}
	}
	
	public void validateFolder(String folder, String schema, CMessageWriter writer)
	{
		List<String> filenames=CFileHelper.listFilesRecursively(folder,".xml");
		for (String filename : filenames)
		{
			String xml=CFileHelper.readFile(filename);
			validate(xml,schema,writer);
		}
	}
	*/
}
