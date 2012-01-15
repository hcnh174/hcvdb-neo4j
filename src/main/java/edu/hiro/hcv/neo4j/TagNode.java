package edu.hiro.hcv.neo4j;


import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.common.collect.Sets;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public class TagNode
{   
    @GraphId protected Long id;
    protected String type="";
    protected String name="";
    protected DynamicProperties properties = new DynamicPropertiesContainer();
		
	@RelatedTo(type = "TAG")
	protected Set<SequenceNode> sequences=Sets.newHashSet();

    public TagNode()
    {
    }
    
    public TagNode(String type, String name)
    {
    	this.type=type;
        this.name=name;
    }   
    
    /////////////////////////////////////////////////////////////
    
    public void addSequence(SequenceNode sequence)
    {
    	//sequence.addTag(this);
    	sequences.add(sequence);
    }

    public boolean hasSequence(SequenceNode sequence)
    {
        return sequences.contains(sequence);
    }
}
