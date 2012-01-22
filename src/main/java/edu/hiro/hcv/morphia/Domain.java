package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class Domain extends Tag
{
	protected Integer family_id;
	protected Family family;

	public Domain() {}
	
	public Domain(String identifier)
	{
		this.identifier=identifier;
	}
}