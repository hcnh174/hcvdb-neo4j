package edu.hiro.hcv.hcv;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.Mongo;

import edu.hiro.hcv.sequences.Sequence;

/**
 * Exploratory testing of Spring Data Neo4j using
 * the SequenceRepositoryImpl.
 */
@ContextConfiguration(locations = {"classpath:META-INF/spring/applicationContext.xml",
		"classpath:META-INF/spring/applicationContext-mongo.xml",
		"classpath:META-INF/spring/applicationContext-neo4j.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
//@Transactional
public class MongoNeo4jTest
{

	//@Resource(name="sequenceRepository") 
	//private SequenceRepository repository;
    
	//@Resource(name="sequenceService")
	//@Autowired
    //private SequenceService sequenceService;
    
	//@Resource(name="neo4jTemplate")
	//@Autowired
	//private Neo4jTemplate neo4jTemplate;
	
	@Autowired
	private HcvGenomeRepository hcvGenomeRepository;
	
	@Resource(name="mongo")
	private Mongo mongo;
	
	@Resource(name="mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Resource(name="graphDatabaseService")
	private GraphDatabaseService graphDatabaseService;
	
	@Test
	public void testSetup()
	{
		
	}
    
    @Test
    public void testWithMongoTemplate()
    {
    	//MongoTemplate mongoTemplate=new MongoTemplate(mongo, "hcvdbneo4j");
    	mongoTemplate.collectionExists("hcvsequences");
    }
    
    @Test
    public void testNeo4j()
    {
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
         
         
         
         Transaction tx = graphDatabaseService.beginTx();
         try {
         	Node firstNode = graphDatabaseService.createNode();
         	firstNode.setProperty("message", "Hello, ");
         	
         	Node secondNode = graphDatabaseService.createNode();
         	secondNode.setProperty("message", "world!");

         	Relationship relationship = firstNode.createRelationshipTo(secondNode,RelTypes.KNOWS);//DynamicRelationshipType.withName("KNOWS") );
         	relationship.setProperty("message","brave Neo4j");
         	
         	System.out.print( firstNode.getProperty( "message" ) );
         	System.out.print( relationship.getProperty( "message" ) );
         	System.out.print( secondNode.getProperty( "message" ) );
         	
         	// let's remove the data
         	//firstNode.getSingleRelationship( RelTypes.KNOWS, Direction.OUTGOING ).delete();
         	//firstNode.delete();
         	//secondNode.delete();
         	
         	tx.success();
         } finally {
         	tx.finish();
         }

  		//DynamicRelationshipType.withName("KNOWS") );
     	//GraphDatabase graphDatabase=new DelegatingGraphDatabase(graphDatabaseService);
     	//Neo4jTemplate neo4Jtemplate=new Neo4jTemplate(graphDatabase);
         //neo4Jtemplate.save(sequences);
         
         //GraphRepository<Sequence> repository = neo4Jtemplate.repositoryFor(Sequence.class);
         //repository.save(sequences);
         //Iterable<Sequence> foundSequences = repository.findAll();
    }
    
    private static enum RelTypes implements RelationshipType
    {
    	KNOWS,
        USERS_REFERENCE,
        USER
    }
    
/*	

    @Test
    public void shouldHaveCorrectNumberOfSequences()
    {
        repository.makeSomeSequences();
        assertEquals(13, (long) repository.count());
    }

    @Test
    public void shouldFindAllSequences()
    {
        Collection<Sequence> madeSequences = repository.makeSomeSequences();
        Iterable<Sequence> foundSequences = repository.findAll();

        int countOfFoundSequences = 0;
        for ( Sequence foundSequence : foundSequences )
        {
            assertTrue( madeSequences.contains( foundSequence ) );
            countOfFoundSequences++;
        }

        assertEquals( madeSequences.size(), countOfFoundSequences );
    }

    @Test
    public void shouldFindSequencesByName()
    {
        for ( Sequence w : repository.makeSomeSequences() )
        {
            assertNotNull( repository.findSequenceNamed( w.getName() ) );
        }
    }

	@SuppressWarnings("unchecked")
    @Test
    public void shouldFindSequencesWith1Moon()
    {
        repository.makeSomeSequences();
        for ( Sequence worldWithOneMoon : repository.findSequencesWithMoons( 1 ) )
        {
        	assertThat( worldWithOneMoon.getName(), is( anyOf( containsString( "Earth" ), containsString( "Midgard" ) ) ) );
        }
    }

    @Test
    public void shouldReachMarsFromEarth()
    {
        repository.makeSomeSequences();

        Sequence earth = repository.findSequenceNamed( "Earth" );
        Sequence mars = repository.findSequenceNamed( "Mars" );

        assertTrue( mars.canBeReachedFrom( earth ) );
        assertTrue( earth.canBeReachedFrom( mars ) );
    }
*/
}
