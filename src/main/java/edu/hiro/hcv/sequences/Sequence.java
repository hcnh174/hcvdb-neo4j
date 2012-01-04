package edu.hiro.hcv.sequences;


import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import edu.hiro.hcv.tags.Tag;

/**
 * A Spring Data Neo4j enhanced Sequence entity.
 * <p/>
 * This is the initial POJO in the Universe.
 */
@NodeEntity
public class Sequence 
{   
    @GraphId Long id;
    
    @Indexed
    private String name;

    @Indexed
    private String sequence;

    @RelatedTo(type = "TAGGED", elementClass = Tag.class, direction = Direction.BOTH)
    private Set<Tag> tags = new HashSet<Tag>();

    public Sequence()
    {
    }
    
    public Sequence(String name, String sequence)
    {
        this.name = name;
        this.sequence = sequence;
    }   

    public String getName()
    {
        return name;
    }

    public String getSequence()
    {
        return sequence;
    }

    @Override
    public String toString()
    {
        return String.format("Sequence{name='%s, sequence=%d}", name, sequence);
    }

    public void addTag( Tag tag )
    {
    	tags.add(tag);
    }

    public boolean isTagged( Tag tag )
    {
        return tags.contains( tag );
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
		Sequence other = (Sequence) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
