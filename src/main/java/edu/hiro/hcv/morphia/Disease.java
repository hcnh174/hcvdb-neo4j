package edu.hiro.hcv.morphia;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class Disease extends Tag
{
	protected Integer parent_id;
	protected String icd10="";
	protected String host="";
	protected String vector="";
	protected Boolean human=false;
	protected String distribution="";
	protected String morbidity="";
	protected String mortality="";
	protected String pathogenesis="";
	protected String transmission="";
	protected String symptoms="";
	protected String diagnosis="";
	protected String prevention="";
	protected String treatment="";	
	protected String vaccines="";
	protected String history="";
	protected String url="";
	protected Boolean list=true;
	protected String notes="";
	protected Integer numsequences=0;
	protected Disease parent;
	protected Set<Pathogen> pathogens=new LinkedHashSet<Pathogen>();

	public Disease() {}
	
	public Disease(String identifier)
	{
		this.identifier=identifier;
	}
	
	/*
	public void getXml(StringBuilder buffer)
	{
		buffer.append("<disease");
		CXmlHelper.attribute(buffer,"identifier",getIdentifier());
		buffer.append(">\n");
		
		CXmlHelper.element(buffer,"name",this.name);
		CXmlHelper.element(buffer,"description",this.description);
		CXmlHelper.element(buffer,"host",this.host);
		CXmlHelper.element(buffer,"distribution",this.distribution);
		CXmlHelper.element(buffer,"morbidity",this.morbidity);
		CXmlHelper.element(buffer,"mortality",this.mortality);
		CXmlHelper.element(buffer,"pathogenesis",this.pathogenesis);
		CXmlHelper.element(buffer,"symptoms",this.symptoms);
		CXmlHelper.element(buffer,"diagnosis",this.diagnosis);
		CXmlHelper.element(buffer,"prevention",this.prevention);
		CXmlHelper.element(buffer,"treatment",this.treatment);
		//CXmlHelper.element(buffer,"drugs",this.drugs);
		CXmlHelper.element(buffer,"vaccines",this.vaccines);
		CXmlHelper.element(buffer,"history",this.history);
		CXmlHelper.element(buffer,"url",this.url);
		CXmlHelper.element(buffer,"notes",this.notes);
		for (Pathogen pathogen : getPathogens())
		{
			CXmlHelper.element(buffer,"pathogen",pathogen.getIdentifier());
		}
		for (CDrug drug : getDrugs())
		{
			CXmlHelper.element(buffer,"drug",drug.getIdentifier());
		}
		for (CRef ref : getRefs())
		{
			CXmlHelper.element(buffer,"reference",ref.getIdentifier());
		}
		buffer.append("</disease>\n");
	}
	*/
}