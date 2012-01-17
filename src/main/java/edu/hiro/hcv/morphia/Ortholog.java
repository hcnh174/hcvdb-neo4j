package edu.hiro.hcv.morphia;

import java.util.LinkedHashSet;
import java.util.Set;

public class Ortholog extends Tag
{
	protected Integer alignment_id;
	protected String notes="";
	protected Integer numsequences=0;
	protected Set<Family> families=new LinkedHashSet<Family>();
	
	public Ortholog() {}
	
	public Ortholog(String identifier)
	{
		this.identifier=identifier;
	}
	
	public void add(Family family)
	{
//		family.setOrtholog(this);
//		if (!getFamilies().contains(family))
//			getFamilies().add(family);
	}
}