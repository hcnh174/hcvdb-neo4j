package edu.hiro.hcv.neo4j;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	//@Resource(name="graphDatabaseService")
	//private GraphDatabaseService graphDatabaseService;
	
	@Resource(name="neo4jTemplate")
	private Neo4jTemplate neo4jTemplate;

    @Test
    public void testNeo4j()
    {
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
         
         neo4jTemplate.save(sequences);
    }

}
