package edu.hiro.hcv.morphia;


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