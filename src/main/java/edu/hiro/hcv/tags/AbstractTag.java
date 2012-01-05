package edu.hiro.hcv.tags;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import edu.hiro.hcv.sequences.Sequence;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public abstract class AbstractTag
{  
	@GraphId
	protected Long id;
	    
    @Indexed
    protected String type;
    
    @Indexed
    protected String name;
    
    protected String description;
    
    @RelatedTo(type = "TAGGED", elementClass = Sequence.class, direction = Direction.BOTH)
    private Set<Sequence> sequences = new HashSet<Sequence>();

    public AbstractTag()
    {
    }
    
    public AbstractTag(String type, String name)
    {
    	this.type=type;
        this.name=name;
    }
    
    public void addSequence( Sequence sequence )
    {
    	sequences.add(sequence);
    }

    public boolean hasSequence( Sequence sequence )
    {
        return sequences.contains( sequence );
    }
}