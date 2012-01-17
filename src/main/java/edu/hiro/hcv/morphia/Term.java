package edu.hiro.hcv.morphia;


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