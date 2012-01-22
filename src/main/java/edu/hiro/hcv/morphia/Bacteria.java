package edu.hiro.hcv.morphia;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class Bacteria extends Pathogen
{
	protected String size="";
	protected String morphology="";
	protected GramStain gram;
	protected Boolean aerobic;
	protected String plasmids="";
	protected String appendages="";

	public Bacteria() {}
	
	public Bacteria(String identifier)
	{
		super(identifier);
	}
	
	public enum GramStain
	{
		POSITIVE("Gram-positive"),
		NEGATIVE("Gram-negative");
		private String label;		
		GramStain(String label){this.label=label;}
		public String getLabel() {return this.label;}
	};
}