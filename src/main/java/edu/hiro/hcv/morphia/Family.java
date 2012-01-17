package edu.hiro.hcv.morphia;

import java.util.LinkedHashSet;
import java.util.Set;

public class Family extends Tag
{
	protected Integer pathogen_id;
	protected Integer ortholog_id;
	protected Integer alignment_id;
	protected String alias="";
	protected String abbr;
	
	protected String familysize="";
	protected String switchingrate="";
	protected String chromosomes="";
	protected String expression="";
	protected String introns="";
	
	protected String protein="";
	protected String daltons="";
	protected String location="";
	protected String function="";
	protected String ligands="";
	protected String antigenicvariation="";
	
	protected String url="";
	protected String notes="";
	protected Integer numsequences=0;
	
	protected Pathogen pathogen;
	protected Ortholog ortholog;
	protected Set<Domain> domains=new LinkedHashSet<Domain>();
//	protected Set<CSubgroup> subgroups=new LinkedHashSet<CSubgroup>();
//	protected Set<CParalog> paralogs=new LinkedHashSet<CParalog>();
//	protected Set<CSequenceset> sequencesets=new LinkedHashSet<CSequenceset>();
//	protected Set<CStructure> structures=new LinkedHashSet<CStructure>();
	
	public Family() {}
	
	public Family(String identifier)
	{
		this.identifier=identifier;
	}

	public void add(Domain domain)
	{
		//domain.setFamily(this);
		this.domains.add(domain);
	}

	/*
	public void getXml(StringBuilder buffer)
	{
		buffer.append("<family");
		CXmlHelper.attribute(buffer,"identifier",getIdentifier());
		CXmlHelper.attribute(buffer,"pathogen",getPathogen().getIdentifier());
		buffer.append(">\n");		
		CXmlHelper.element(buffer,"name",this.name);
		CXmlHelper.element(buffer,"alias",this.alias);
		CXmlHelper.element(buffer,"description",this.description);		
		CXmlHelper.element(buffer,"familysize",this.familysize);
		CXmlHelper.element(buffer,"switchingrate",this.switchingrate);
		CXmlHelper.element(buffer,"chromosomes",this.chromosomes);
		CXmlHelper.element(buffer,"expression",this.expression);
		CXmlHelper.element(buffer,"introns",this.introns);
		CXmlHelper.element(buffer,"protein",this.protein);
		CXmlHelper.element(buffer,"daltons",this.daltons);
		CXmlHelper.element(buffer,"location",this.location);
		CXmlHelper.element(buffer,"function",this.function);
		CXmlHelper.element(buffer,"ligands",this.ligands);
		CXmlHelper.element(buffer,"url",this.url);
		CXmlHelper.element(buffer,"notes",this.notes);
		for (CStructure structure : this.structures)
		{
			CXmlHelper.element(buffer,"structure",structure.getIdentifier());
		}
		for (CRef ref : this.refs)
		{
			CXmlHelper.element(buffer,"reference",ref.getIdentifier());
		}
		buffer.append("</family>\n");
	}
	*/
}