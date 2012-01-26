package edu.hiro.hcv.neo4j;


import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.DynamicPropertiesContainer;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
/**
 */
@RooJavaBean
@RooToString
@NodeEntity
public class FeatureNode
{	
    @GraphId protected Long nodeId;
	protected String type;
    protected Integer start;
    protected Integer end;
    protected String sequence;
    protected Integer length;
	//protected Map<String,String> annotations=Maps.newLinkedHashMap();
	//protected Map<String,String> crossrefs=Maps.newLinkedHashMap();
	protected DynamicProperties annotations = new DynamicPropertiesContainer();
	
	public FeatureNode(){}
	
	public FeatureNode(String type, int start, int end, String sequence)
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
		this.annotations.setProperty(name,value);
	}
	
	public Object getAnnotation(String property)
	{
		return this.annotations.getProperty(property);
	}
	
	////////////////////////////////////////////////////
	
	public void setCrossref(String name, String value)
	{
		setAnnotation(name,value); //hack!
		//this.crossrefs.put(name,value);
	}
//	
//	public Object getCrossref(String property)
//	{
//		return this.crossrefs.get(property);
//	}
}

