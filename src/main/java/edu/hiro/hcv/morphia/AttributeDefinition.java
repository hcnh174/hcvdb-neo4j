package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;


/**
 * 
 */
@RooJavaBean
@RooToString
@RooEquals
public class AttributeDefinition
{
	protected String name;
	protected Type type=Type.STRING;

    public AttributeDefinition()
    {
    }
    
    public AttributeDefinition(String name, Type type)
    {
    	this.name=name;
    	this.type=type;
    }
    
    public enum Type
    {
    	STRING,
    	INTEGER,
    	FLOAT,
    	BOOLEAN
    }
}
