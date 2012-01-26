package edu.hiro.hcv.setup;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.code.morphia.query.Query;
import com.google.common.collect.Sets;

import edu.hiro.hcv.morphia.RefRepository;
import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.morphia.SequenceRepository;
import edu.hiro.hcv.morphia.TaxonRepository;
import edu.hiro.hcv.neo4j.RefNode;
import edu.hiro.hcv.neo4j.RefNodeRepository;
import edu.hiro.hcv.neo4j.TaxonNode;
import edu.hiro.hcv.neo4j.TaxonNodeRepository;
import edu.hiro.hcv.sequences.SequenceService;
import edu.hiro.hcv.util.MathHelper;
import edu.hiro.hcv.util.StringHelper;

@Service("setupService")
//@Transactional
public class SetupServiceImpl implements SetupService
{	
	@Resource(name="sequenceService")
	private SequenceService sequenceService; 
	
	@Resource(name="sequenceRepository")
	private SequenceRepository sequenceRepository;
	
	@Resource(name="refRepository")
	private RefRepository refRepository;
	
	@Resource(name="taxonRepository")
	private TaxonRepository taxonRepository;
	
	@Resource(name="graphDatabaseService")
	private GraphDatabaseService graphDatabaseService;
	
	@Autowired
	private TaxonNodeRepository taxonNodeRepository;
	
	@Autowired
	private RefNodeRepository refNodeRepository;
	
	@Transactional("neo4jTransactionManager")
	public void updateTaxa()
	{
		Query<Sequence> query=sequenceRepository.createQuery().retrievedFields(true,"taxon");
		Set<Integer> taxids=Sets.newHashSet();
		for (Sequence sequence : query.asList())
		{
			System.out.println("taxon="+sequence.getTaxon());
			if (sequence.getTaxon()!=null)
				taxids.add(sequence.getTaxon());
		}
		System.out.println("taxonids: "+StringHelper.join(taxids,","));
		Map<Integer,TaxonNode> taxa=TaxonBuilder.getTaxa(taxids);
    	for (TaxonNode taxon : taxa.values())
    	{
    		//System.out.println("saving taxon node: "+taxon.toString());
        	//taxonNodeRepository.save(taxon);
    	}
    	
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
	
	/*
	@Transactional("neo4jTransactionManager")
	public void updateTaxa()
	{
		Query<Sequence> query=sequenceRepository.createQuery().retrievedFields(true,"taxon");//.filter("foo >", 12);
		Set<Integer> taxids=Sets.newHashSet();
		for (Sequence sequence : query.asList())
		{
			System.out.println("taxon="+sequence.getTaxon());
			if (sequence.getTaxon()!=null)
				taxids.add(sequence.getTaxon());
		}
		System.out.println("taxonids: "+StringHelper.join(taxids,","));
		Map<Integer,TaxonNode> nodes=TaxonBuilder.getTaxa(taxids);
		
		Map<Integer,TaxonNode> nodes=Maps.newHashMap();
    	for (Taxon taxon : taxa)
    	{
    		TaxonNode node = new TaxonNode(taxon.getId(), taxon.getName());
    		System.out.println("creating taxon node: "+node.toString());
        	nodes.put(taxon.getId(),node);
        	taxonNodeRepository.save(node);
    	}
    	
    	for (Taxon taxon : taxa)
    	{
    		if (taxon.getParent_id()==null)
    			continue;
    		TaxonNode node=nodes.get(taxon.getId());
    		TaxonNode parent=nodes.get(taxon.getParent_id());
    		node.setParent(parent);
    		parent.addChild(node);
    		System.out.println("creating relationsip between nodes "+taxon.getId()+" and "+taxon.getParent_id());
    	}
    	
    	for (TaxonNode node : nodes.values())
    	{
    		taxonNodeRepository.save(node);
    	}

	}
	*/
	
	@Transactional("neo4jTransactionManager")
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
//		Map<Integer,Ref> map=RefBuilder.getRefs(refids);
//		for (Ref ref : map.values())
//		{
//			System.out.println("saving ref: "+ref.toString());
//			refRepository.save(ref);
//		}
		
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
		List<Sequence> sequences=GenbankSequenceBuilder.parseFile(filename);
		for (Sequence sequence : sequences)
		{
			System.out.println("saving sequene: "+sequence.getAccession());
			sequenceRepository.save(sequence);
		}
		System.out.println("finished loading");
	}
	
	public void loadSampleData(int num)
	{
		System.out.println("Loading sample data");
		for (int i=0;i<num; i++)
		{			
			sequenceRepository.save(new Sequence("S"+MathHelper.randomInteger(1000),"acgtcttgctgtgctgctgctacctgtgctgctgctactgctactgctgctacctgctgctgctacgttgctgctgcttgctgctgctacaccacgtctcgtc"));
		}
		System.out.println("Finished");
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
