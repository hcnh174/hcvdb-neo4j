package edu.hiro.hcv.sequences;


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


/**
 */
@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public class Feature
{
	@GraphId protected Long id;
	protected String type;
	protected String name;
    protected Integer start;
    protected Integer end;
    protected Sequence sequence;
    
    @RelatedTo(type = "FEATURE_PROPERTY")
	protected Set<FeatureProperty> properties=Sets.newHashSet();
	
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
	
	public FeatureProperty setProperty(String name, Object value)
	{
		FeatureProperty property=new FeatureProperty(name,value);
		addProperty(property);
		return property;
	}
	public void addProperty(FeatureProperty property)
	{
		property.setFeature(this);
		this.properties.add(property);
	}
	
}
