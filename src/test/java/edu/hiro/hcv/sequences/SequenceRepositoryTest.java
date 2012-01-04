package edu.hiro.hcv.sequences;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.containsString;

/**
 * Exploratory testing of Spring Data Neo4j using
 * the SequenceRepositoryImpl.
 */
@ContextConfiguration(locations = "classpath:META-INF/spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SequenceRepositoryTest
{

    @Autowired
	private SequenceRepository repository;
    
    @Autowired
    private SequenceService sequenceService;
    
	@Autowired
	private Neo4jTemplate template;

	@Rollback(false)
    @BeforeTransaction
    public void clearDatabase()
    {
		Neo4jHelper.cleanDb(template);
    }

	@Test
	public void testSetup()
	{
		
	}
	

    @Test
    public void shouldAllowDirectSequenceCreation()
    {
        assertEquals(0, (long) template.count(Sequence.class));
        Sequence mySequence = repository.save(new Sequence( "ABC123", "cgtgcacgtcactcga" ));
        assertEquals(1, (long) template.count(Sequence.class));
        Sequence foundSequence = repository.findOne(mySequence.id);
        assertEquals(mySequence.getName(), foundSequence.getName());
    }
    
    
    @Test
    public void shouldPopulateGalaxyWithSequences()
    {
        Iterable<Sequence> sequences = sequenceService.makeSomeSequences();
        assertNotNull( sequences );
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
