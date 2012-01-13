package edu.hiro.hcv.morphia;


import java.util.Map;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Embedded;
import com.google.common.collect.Maps;


/**
 */
@RooJavaBean
@RooToString
@RooEquals
@Embedded
public class Feature
{
	protected String type;
	protected String name;
    protected Integer start;
    protected Integer end;
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
	
	public void setProperty(String name, Object value)
	{
		properties.put(name,value);
	}
	
	public Object getProperty(String property)
	{
		return this.properties.get(property);
	}
	
}
