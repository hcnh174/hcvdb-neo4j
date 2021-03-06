package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * 
 */
@RooJavaBean
@RooToString
@RooEquals
@Entity("tags")
public class Tag
{
	@Id protected String identifier;
    protected String type;    
    protected String name;
    protected String description;    
	
    public Tag()
    {
    }
    
    public Tag(String identifier, String type, String name)
    {
    	this.identifier=identifier;
    	this.type=type;
        this.name=name;
    }
}
