package edu.hiro.hcv.sequences;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import edu.hiro.hcv.tags.Tag;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public class Sequence 
{   
    @GraphId protected Long id;
	protected String accession="";
	protected String sequence="";
	protected Integer ntlength;
	protected Integer gi;
	protected String description="";
	protected String version="";
	protected String taxon="";
	protected Date udate;
	protected String ref="";
	
	protected Feature source=new Feature();
	
	@RelatedTo(type = "FEATURE")
	protected Set<Feature> features=Sets.newHashSet();
	
	@RelatedTo(type = "TAG") //, direction = Direction.BOTH
	protected Set<Tag> tags=Sets.newHashSet();

    public Sequence()
    {
    }
    
    public Sequence(String accession)
    {
        this.accession = accession;
    }   
    
    public Sequence(String accession, String sequence)
    {
        this.accession = accession;
        this.sequence = sequence;
    }  
    
    public void addFeature(Feature feature)
    {
    	feature.setSequence(this);
    	this.features.add(feature);
    }
    
    public Feature createFeature(String type)
    {
    	Feature feature=new Feature(type);
    	addFeature(feature);
    	return feature;
    }

    public boolean hasFeature(Feature feature)
    {
        return features.contains(feature);
    }
    
    /////////////////////////////////////////////////////////////
    
    public void addTag(Tag tag)
    {
    	tag.addSequence(this);
    	tags.add(tag);
    }

    public boolean hasTag(Tag tag)
    {
        return tags.contains(tag);
    }
}
