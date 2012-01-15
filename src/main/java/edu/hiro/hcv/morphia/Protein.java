package edu.hiro.hcv.morphia;

import java.util.Date;
import java.util.Set;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.common.collect.Sets;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@Entity
public class Protein 
{   
    @Id protected String accession="";
	protected String sequence="";
	
	@Embedded
	protected Set<Feature> features=Sets.newHashSet();
	
    public Protein()
    {
    }
    
    public Protein(String accession)
    {
        this.accession = accession;
    }   
    
    public Protein(String accession, String sequence)
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

    public boolean hasFeature(Feature feature)
    {
        return features.contains(feature);
    }
}
