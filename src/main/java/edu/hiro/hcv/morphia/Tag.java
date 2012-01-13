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
@Entity
public class Tag
{
	@Id protected Long id;
    protected String type;    
    protected String name;
    protected String description;    
	
    public Tag()
    {
    }
    
    public Tag(Long id, String type, String name)
    {
    	this.id=id;
    	this.type=type;
        this.name=name;
    }
}
