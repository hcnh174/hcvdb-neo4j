package edu.hiro.hcv.neo4j;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.hiro.hcv.sequences.SequenceService;

/**
 * Exploratory testing of Spring Data Neo4j using
 * the SequenceRepositoryImpl.
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml",
		"classpath:META-INF/spring/applicationContext-mongo.xml",
		"classpath:META-INF/spring/applicationContext-neo4j.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class Neo4jTest
{
	@Resource(name="graphDatabaseService")
	private GraphDatabaseService graphDatabaseService;
	
	@Resource(name="neo4jTemplate")
	private Neo4jTemplate neo4jTemplate;
	
	@Autowired
	private SequenceNodeRepository sequenceNodeRepository;
	
	@Autowired
	private TagNodeRepository tagNodeRepository;
	
	@Autowired
	private SequenceService sequenceService;

	@Test
    public void testDynamicProperties()
    {
		/*
		Transaction tx = graphDatabaseService.beginTx();
        try {
    	 SequenceNode sequence=new SequenceNode("ABCDEF");
    	 sequence.getProperties().setProperty("ntlength",100);
    	 
    	 TagNode genotype=new TagNode("genotype","1b");
    	 sequence.addTag(genotype);
    	 
    	 TagNode patient=new TagNode("patient","Q1045");
    	 patient.getProperties().setProperty("age",37);
    	 patient.getProperties().setProperty("weight",75);
    	 sequence.addTag(patient);
    	 
    	 sequenceNodeRepository.save(sequence);
    	 tagNodeRepository.save(genotype);
    	 tagNodeRepository.save(patient);
    	tx.success();
	    } finally {
	    	tx.finish();
	    }
	    */
    }
	
    @Test
    public void testNeo4j()
    {
    	/*
    	Transaction tx = graphDatabaseService.beginTx();
        try {
    	 ArrayList<SequenceNode> sequences = new ArrayList<SequenceNode>();
    	 sequences.add(new SequenceNode("Jupiter"));
         sequences.add(new SequenceNode("Saturn"));
         sequences.add(new SequenceNode("Uranus"));
         sequences.add(new SequenceNode("Neptune"));

         sequences.add(new SequenceNode("Alfheimr"));
         sequences.add(new SequenceNode("Midgard"));
         sequences.add(new SequenceNode("Muspellheim"));
         sequences.add(new SequenceNode("Asgard"));
         sequences.add(new SequenceNode("Hel"));
         
         for (SequenceNode sequence : sequences)
         {
        	 neo4jTemplate.save(sequence);
         }
     	tx.success();
        } finally {
        	tx.finish();
        }
        */
    }
}
