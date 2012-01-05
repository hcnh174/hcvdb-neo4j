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
 * 
 */
@NodeEntity
public class Tag extends AbstractTag
{

    public Tag()
    {
    }
    
    public Tag(String type, String name)
    {
       super(type,name);
    }
}
