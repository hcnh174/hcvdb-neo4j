package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class Pfam extends Tag
{	
	protected Integer clan_id;
	protected PfamType type;
	protected Integer numsequences=0;
	protected Clan clan;
	public Pfam() {}
	
	public Pfam(String identifier)
	{
		this.identifier=identifier;
	}

	public String getUrl()
	{
		return "http://pfam.sanger.ac.uk/family?acc="+this.name;
	}
	
	public enum PfamType
	{
		Family,
		Domain,
		Repeat,
		Motif
	}
}