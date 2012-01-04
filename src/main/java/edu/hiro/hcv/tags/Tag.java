package edu.hiro.hcv.tags;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.hiro.hcv.sequences.Sequence;

/**
 * A Spring Data Neo4j enhanced Tag entity.
 * <p/>
 * This is the initial POJO in the Universe.
 */
@NodeEntity
public class Tag 
{   
    @GraphId Long id;
    
    @Indexed
    private String name;

    @RelatedTo(type = "TAGGED", elementClass = Sequence.class, direction = Direction.BOTH)
    private Set<Sequence> sequences = new HashSet<Sequence>();

    public Tag()
    {
    }
    
    public Tag(String name)
    {
        this.name = name;
    }   

    public String getName()
    {
        return name;
    }
    
    @Override
    public String toString()
    {
        return String.format("World{name='%s}", name);
    }

    public void addSequence( Sequence sequence )
    {
    	sequences.add(sequence);
    }

    public boolean hasSequence( Sequence sequence )
    {
        return sequences.contains( sequence );
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
