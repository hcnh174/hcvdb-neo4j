package edu.hiro.hcv.morphia;

import java.util.List;
import java.util.Set;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@Entity("sequences")
public class Sequence 
{   
    @Id protected String accession="";
	protected String sequence="";
	protected Integer length;
	protected Integer gi;
	protected String description="";
	protected Integer taxon_id;

	@Embedded protected List<String> comments=Lists.newArrayList();
	@Embedded protected Set<Feature> features=Sets.newHashSet();	
	@Embedded protected Set<Integer> refs=Sets.newHashSet();
	@Embedded protected Set<String> tags=Sets.newHashSet();
	
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
        this.length=sequence.length();
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

    public boolean hasFeature(Feature feature)
    {
        return features.contains(feature);
    }
    
    ///////////////////////////////////////
    
    public void addRef(Integer ref)
    {
    	this.refs.add(ref);
    }
    
    ///////////////////////////////////////
    
    public void addComment(String comment)
    {
    	this.comments.add(comment);
    }
    
    public void addTag(String identifier)
    {
    	this.tags.add(identifier);
    }
}
