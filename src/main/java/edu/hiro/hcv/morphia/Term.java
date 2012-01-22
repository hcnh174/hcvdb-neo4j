package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;

@RooJavaBean
@RooToString
@RooEquals
@Entity("terms")
public class Term extends Tag
{
	protected String identifier;
	protected String term="";
	protected String definition="";
	protected Integer ref_id;
	protected String pages="";
	//protected CRef ref;
	
	public Term() {}
	
	public Term(String identifier)
	{
		this.identifier=identifier;
	}
}