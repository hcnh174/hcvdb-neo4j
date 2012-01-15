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
public class SequenceNode
{   
    @GraphId protected Long id;
	protected String accession="";
	protected DynamicProperties properties = new DynamicPropertiesContainer();
		
	@RelatedTo(type = "TAG") //, direction = Direction.BOTH
	protected Set<TagNode> tags=Sets.newHashSet();

    public SequenceNode()
    {
    }
    
    public SequenceNode(String accession)
    {
        this.accession = accession;
    }   
    
    /////////////////////////////////////////////////////////////
    
    public void addTag(TagNode tag)
    {
    	tags.add(tag);
    }

    public boolean hasTag(TagNode tag)
    {
        return tags.contains(tag);
    }
}
