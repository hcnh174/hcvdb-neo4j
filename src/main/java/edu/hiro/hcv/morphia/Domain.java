package edu.hiro.hcv.morphia;


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