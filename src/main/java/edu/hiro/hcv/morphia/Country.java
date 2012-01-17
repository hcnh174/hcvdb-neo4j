package edu.hiro.hcv.morphia;

import java.util.Date;

public class Country extends Tag
{
	protected Integer id;
	protected String identifier;
	protected GeographicRegion region;
	protected Float latitude=null;
	protected Float longitude=null;
	protected String iso=null;
	protected Integer numsequences=0;
	protected Date created;
	protected Date updated;
	
	//@PreUpdate
	 protected void onUpdate()
	 {
		 this.updated = new Date();
		 System.out.println("onUpdate called: "+this.updated.toString());
	 }
	 
	 public enum GeographicRegion
	{
		AFRICA("Africa"),
		ANTARCTICA("Antarctica"),
		ASIA("Asia"),
		AUSTRALIA("Australia"),
		CARIBBEAN("Caribbean"),
		CENTRAL_AMERICA("Central America"),
		EUROPE("Europe"),
		NORTH_AMERICA("North America"),
		OCEANIA("Oceania"),
		OTHER("Other"),
		SOUTH_AMERICA("South America");
		
		protected String title;
		
		GeographicRegion(final String title)
		{
			this.title=title;
		}
		
		public String getTitle() {return this.title;}
	}
}