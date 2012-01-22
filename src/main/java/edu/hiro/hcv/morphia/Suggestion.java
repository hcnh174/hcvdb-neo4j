package edu.hiro.hcv.morphia;

public class Suggestion
{
	protected String identifier;
	protected String keyword;
	protected String type="item";		
	
	public Suggestion(String identifier, String keyword)
	{
		this.identifier=identifier;
		this.keyword=keyword;			
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public String getKeyword() {
		return keyword;
	}
	
	public String getType() {
		return keyword;
	}
}