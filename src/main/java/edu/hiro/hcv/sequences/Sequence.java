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
	
	protected Map<String,Object> source=Maps.newLinkedHashMap();
	protected List<Feature> features=Lists.newArrayList();
	
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
    	this.features.add(feature);
    }
    
    public Feature createFeature(String type)
    {
    	Feature feature=new Feature(type);
    	addFeature(feature);
    	return feature;
    }
    
    public class Feature
    {
    	protected String type;
    	protected String name;
	    protected Integer start;
	    protected Integer end;
	    protected String sequence;
    	protected Map<String,Object> properties=Maps.newLinkedHashMap();
    	
    	public Feature(){}
    	
    	public Feature(String type)
    	{
    		this.type=type;
    	}
    	
    	public Feature(String type, String name, int start, int end)
    	{
    		this.type=type;
    		this.name = name;
	        this.start = start;
	        this.end = end;
    	}
    	
    	public String getType(){return this.type;}
    	public void setType(final String type){this.type=type;}
    	
    	public String getName(){return this.name;}
    	public void setName(final String name){this.name=name;}

    	public Integer getStart(){return this.start;}
    	public void setStart(final Integer start){this.start=start;}

    	public Integer getEnd(){return this.end;}
    	public void setEnd(final Integer end){this.end=end;}
    	
    	public String getSequence(){return this.sequence;}
    	public void setSequence(final String sequence){this.sequence=sequence;}

    	public Map<String,Object> getProperties(){return this.properties;}
    	public void setProperties(final Map<String,Object> properties){this.properties=properties;}
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
