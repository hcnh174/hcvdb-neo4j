package edu.hiro.hcv.morphia;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;

import edu.hiro.hcv.morphia.Sequence;

/**
 * Exploratory testing of Spring Data Neo4j using
 * the SequenceRepositoryImpl.
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml",
		"classpath:META-INF/spring/applicationContext-mongo.xml",
		"classpath:META-INF/spring/applicationContext-neo4j.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class MorphiaTest
{
	@Resource(name="mongo")
	private Mongo mongo;
	
	@Resource(name="morphia")
	private Morphia morphia;
	
	@Resource(name="sequenceRepository")
	private SequenceRepository sequenceRepository;
	
	@Test
	public void testSetup()
	{
		
	}
    
    @Test
    public void test()
    {

    }
    
    @Test
    public void testMorphia()
    {
    	/*
    	 ArrayList<Sequence> sequences = new ArrayList<Sequence>();
    	 sequences.add(new Sequence("Jupiter", "acgtctcgtca"));
         sequences.add(new Sequence("Saturn", "acgtctcgtca"));
         sequences.add(new Sequence("Uranus", "acgtctcgtca"));
         sequences.add(new Sequence("Neptune", "acgtctcgtca"));

         sequences.add(new Sequence("Alfheimr", "acgtctcgtca"));
         sequences.add(new Sequence("Midgard", "acgtctcgtca"));
         sequences.add(new Sequence("Muspellheim", "acgtctcgtca"));
         sequences.add(new Sequence("Asgard", "acgtctcgtca"));
         sequences.add(new Sequence("Hel", "acgtctcgtca"));
         
         for (Sequence sequence : sequences)
         {
        	 sequenceRepository.save(sequence);
         }
         System.out.println(sequenceRepository.find().asKeyList());
         */
    }
}
