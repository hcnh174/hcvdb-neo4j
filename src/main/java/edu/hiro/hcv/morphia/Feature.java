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
    protected Integer start;
    protected Integer end;
    protected String sequence;
    protected Integer length;
	protected Map<String,String> annotations=Maps.newLinkedHashMap();
	protected Map<String,String> crossrefs=Maps.newLinkedHashMap();
	
	public Feature(){}
	
	public Feature(String type)
	{
		this.type=type;
	}
	
	public Feature(String type, int start, int end, String sequence)
	{
		this.type=type;
        this.start = start;
        this.end = end;
        this.sequence=sequence;
        this.length=sequence.length();
	}
	
	////////////////////////////////////////////////////
	
	public void setAnnotation(String name, String value)
	{
		annotations.put(name,value);
	}
	
	public Object getAnnotation(String property)
	{
		return this.annotations.get(property);
	}
	
	////////////////////////////////////////////////////
	
	public void setCrossref(String name, String value)
	{
		crossrefs.put(name,value);
	}
	
	public Object getCrossref(String property)
	{
		return this.crossrefs.get(property);
	}
	
}
