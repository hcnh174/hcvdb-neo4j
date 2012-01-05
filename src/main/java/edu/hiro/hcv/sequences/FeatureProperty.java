package edu.hiro.hcv.sequences;


import java.util.Map;

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
public class FeatureProperty
{
	@GraphId protected Long id;
	protected String name;
    protected Object value;
	protected Feature feature;
	
	public FeatureProperty(){}
	
	public FeatureProperty(String name, Object value)
	{
		this.name = name;
		this.value=value;
	}
}
