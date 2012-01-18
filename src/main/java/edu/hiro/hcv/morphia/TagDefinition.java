package edu.hiro.hcv.morphia;

import java.util.List;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.common.collect.Lists;

/**
 * 
 */
@RooJavaBean
@RooToString
@RooEquals
@Entity
public class TagDefinition
{
	@Id protected String identifier;
	protected List<AttributeDefinition> attributes=Lists.newArrayList();

    public TagDefinition()
    {
    }
    
    public TagDefinition(String identifier)
    {
    	this.identifier=identifier;
    }
}
