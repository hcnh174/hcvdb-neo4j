package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class Protist extends Pathogen
{	
	public Protist() {}
	
	public Protist(String identifier)
	{
		super(identifier);
	}
}