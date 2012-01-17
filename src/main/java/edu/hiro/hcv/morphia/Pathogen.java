package edu.hiro.hcv.morphia;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Transient;

public class Pathogen extends Tag
{
	protected Integer taxon_id;
	protected String taxgroup;
	protected String kegg;
	protected String alias="";
	protected String abbr;
	protected String hosts="";
	protected String distribution="";
	protected String lifecycle="";
	protected String host="";
	protected String vector="";
	protected String chromosomes="";
	protected String gccontent="";
	protected String numgenes="";
	protected String numproteins="";
	protected String numbases="";
	protected String genome="";
	protected String antigenicvariation="";
	protected String url="";
	protected String notes="";
	protected String codonusage="";
	protected Integer numsequences=0;
	
	protected Taxon taxon;
	protected Set<Disease> diseases=new LinkedHashSet<Disease>();
	protected Set<Family> families=new LinkedHashSet<Family>();
	protected List<Taxon> lineage;

	public Pathogen() {}
	
	public Pathogen(String identifier)
	{
		this.identifier=identifier;
	}
	
	
	// get the first disease if there are more than one or null if none
	@Transient
	public Disease getDisease()
	{
		if (this.diseases.isEmpty())
			return null;
		return this.diseases.iterator().next();
	}

	public void addDisease(Disease disease)
	{
		if (disease!=null && !this.diseases.contains(disease))
			this.diseases.add(disease);
	}
	
	public void addDiseases(Collection<Disease> diseases)
	{
		this.diseases=new LinkedHashSet<Disease>();
		for (Disease disease : diseases)
		{
			addDisease(disease);
		}
	}
	
	public void addFamily(Family family)
	{
		//family.setPathogen(this);
		this.families.add(family);
	}

	/*
	@Transient
	public CCodonUsageTable getCodonUsageTable()
	{
		if (this.codonusage==null)
			return null;
		CCodonUsageTable table=CCodonUsageParser.parse(this.codonusage);
		table.setName(getAbbreviation());
		return table;
	}
	*/
}