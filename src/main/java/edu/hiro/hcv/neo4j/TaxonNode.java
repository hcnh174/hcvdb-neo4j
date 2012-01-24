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
public class TaxonNode
{   
    @GraphId protected Long id;
    protected Integer taxid;
    protected String name="";
    protected DynamicProperties properties = new DynamicPropertiesContainer();
	
    protected TaxonNode parent;
    
	@RelatedTo(type = "LINEAGE")
	protected Set<TaxonNode> children=Sets.newHashSet();

    public TaxonNode()
    {
    }
    
    public TaxonNode(Integer taxid, String name)
    {
    	this.taxid=taxid;
        this.name=name;
    }   
    
    /////////////////////////////////////////////////////////////
    
    public void addChild(TaxonNode taxon)
    {
    	//sequence.addTag(this);
    	children.add(taxon);
    }

    public boolean hasChild(TaxonNode taxon)
    {
        return children.contains(taxon);
    }
}
