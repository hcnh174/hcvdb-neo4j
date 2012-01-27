package edu.hiro.hcv.bio;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import edu.hiro.util.BeanHelper;
import edu.hiro.util.StringHelper;

@RooJavaBean
@RooToString
@RooEquals
public class GenbankSequence
{
	protected Integer id;
	protected String genome="";
	protected String chromosome="";
	protected String family="";
	protected String taxon="";
	protected String country="";
	protected String ref="";
	protected String sequenceset="";
	protected String source="";
	protected String identifier;
	protected boolean visible=true;
	protected String user_id;
	protected boolean uploaded=false;
	protected String accession="";
	protected Integer gi=null;
	protected String defline="";
	protected String version="";
	protected String uniprot="";	
	protected String isolate="";
	protected String isolation_source="";
	protected Integer molwt=null;
	protected String subregion="";
	protected String collection_date="";
	protected String locus_tag="";
	protected Integer codon_start=null;
	protected String transl_table="";
	protected String mol_type="";
	protected String strain="";
	protected String segment="";
	protected String serotype="";
	protected String serogroup="";
	protected String serovar=""; // new
	protected String subtype=""; // new
	protected String host=""; // new
	protected String lab_host=""; // new
	protected String specific_host="";
	protected String plasmid="";
	protected String allele="";
	protected String codedby="";
	protected String product="";
	protected String oldid="";
	protected Boolean truncated=false;
	protected Date udate=null;
	protected String division="";
	protected String natype="";
	protected Boolean circular=null;
	protected String clone="";
	protected String comments="";
	protected String notes="";
	protected Date created;
	protected Date updated;
	protected String method=null;
	protected String model=null;
	protected Double score=null;
	protected Double evalue=null;
	protected String hmmloc=null;

	// nucleotide
	protected Boolean pseudogene=false;
	protected Integer start=null;
	protected Integer end=null;
	protected StrandType strand=null;
	protected Integer numexons=null;
	protected String locus="";
	protected String gene="";
	protected Integer geneid=null;
	protected String protein_id="";
	protected Integer protein_gi=null;
	protected String protein="";
	protected String splicing="";
	protected String seg="";
	protected String dust="";
	protected Float gc=null;
	protected Float gc3=null;
	protected Float gc3skew=null;
	
	protected Integer domainnum=0;
	protected Integer totaldomainnum=0;
	protected String domains="";
	protected String architecture="";
	
	// protein
	protected String ec=null;
	protected Boolean conceptual=false;
	
	protected Integer ntlength=0;
	protected Integer aalength=0;
	
	protected String spliced="";
	protected String sequence="";	
	protected String translation=null;
	protected String secondary=null;

	public GenbankSequence(){}
	
	public GenbankSequence(GenbankSequence template)
	{
		BeanHelper beanhelper=new BeanHelper();
		beanhelper.copyProperties(this,template);
	}

	@Transient
	public String getName()
	{
		return this.accession;
	}
	
	@Transient
	public String getDescription()
	{
		return this.defline;
	}
	
	@Transient
	public String getAbbreviation()
	{
		return this.accession;
	}
	
	@Transient
	public String getLocation()
	{
		if (this.start==null && this.end==null)
			return "";
		return this.start+".."+this.end;
	}
	
	@Transient
	public CompoundLocation getPfamdomains()
	{
		return new CompoundLocation(domains);
	}

	@Transient
	public String getCodingSequence()
	{
		return SequenceHelper.getCodingSequence(this.accession, this.spliced, this.sequence);
	}
	
	public String getSequence(SequenceType type)
	{
		return (type==SequenceType.NT) ? this.sequence : this.translation;
	}
	
	public void addNote(String note)
	{
		if (!StringHelper.hasContent(note))
			return;
		if (StringHelper.hasContent(this.notes))
			this.notes+="|";
		this.notes+=note.trim();
	}

	/*
	public static CTable getTable()
	{
		CTable table=new CTable();
		for (CSequenceProperties.Property property : PROPERTIES)
		{
			table.getHeader().add(property.name());
		}
		return table;
	}
	
	public void append(CTable table)
	{
		CTable.Row row=table.addRow();
		for (CSequenceProperties.Property property : PROPERTIES)
		{
			String value=getValue(property);
			row.add(value);
		}
	}
	
	
	public static String getHeader()
	{
		return getHeader(PROPERTIES);
	}
	
	public static String getHeader(List<CSequenceProperties.Property> properties)
	{
		return CStringHelper.join(properties,"\t")+"\n";
	}
	
	public String getData()
	{
		return getData(PROPERTIES);
	}
	
	public void setValue(CSequenceProperties.Property property, Object value)
	{
		CBeanHelper beanhelper=new CBeanHelper();
		beanhelper.setPropertyFromString(this,property.name(),value.toString());
	}
	
	public String getValue(CSequenceProperties.Property property)
	{
		CBeanHelper beanhelper=new CBeanHelper();
		String value=null;
		if (property==CSequenceProperties.Property.udate && this.udate!=null)
			value=CDateHelper.format(this.udate,CConstants.UDATE_PATTERN);
		else value=(String)beanhelper.getProperty(this,property.name());
		if (value==null)
			value="";
		return value;
	}
	
	public String getData(List<CSequenceProperties.Property> properties)
	{
		List<String> values=new ArrayList<String>();
		for (CSequenceProperties.Property property : properties)
		{
			String value=getValue(property);
			values.add(value);
		}
		return CStringHelper.join(values,"\t")+"\n";
	}
	
	public String getSequenceFasta()
	{
		return CSequenceHelper.getFastaChunked(this.accession, this.sequence)+"\n";
	}
	
	public String getTranslationFasta()
	{
		if (this.translation==null)
			return "";
		return CSequenceHelper.getFastaChunked(this.accession, this.translation)+"\n";
	}
	
	public void updateAalength()
	{
		if (CStringHelper.hasContent(this.translation))
			this.aalength=this.translation.length();			
	}

	public static CTable createTable(List<CGenbankSequence> sequences)
	{
		CTable table=new CTable();
		for (CSequenceProperties.Property property : PROPERTIES)
		{
			table.getHeader().add(property.name());
		}
		for (CGenbankSequence sequence : sequences)
		{
			CTable.Row row=table.addRow();
			for (CSequenceProperties.Property property : PROPERTIES)
			{
				row.add(sequence.getValue(property));
			}
		}
		//table=table.condense();
		return table;
	}
	
	public static CTable createTable(Collection<CGenbankSequence> sequences, List<CSequenceProperties.Property> properties)
	{
		return createTable(sequences,properties,true);
	}
	
	public static CTable createTable(Collection<CGenbankSequence> sequences, List<CSequenceProperties.Property> properties, boolean condense)
	{
		//System.out.println("createTable: sequences="+sequences.size()+", properties="+properties.size());
		CTable table=new CTable();
		if (properties.size()==1) //skip header row
			table.setShowHeader(false);
		for (CSequenceProperties.Property property : properties)
		{
			table.getHeader().add(property.name());
		}
		for (CGenbankSequence sequence : sequences)
		{
			//System.out.println("next sequence: "+sequence.getAccession());
			CTable.Row row=(sequence.getId()!=null) ? table.addRow(sequence.getId()) : table.addRow();
			for (CSequenceProperties.Property property : properties)
			{
				String value=sequence.getValue(property);
				row.add(value);
				//System.out.println("--adding property "+property+"="+value);
			}
		}
		if (condense && properties.size()>1)
			table=table.condense();
		return table;
	}
	
	public static void setProperty(List<CGenbankSequence> sequences, CSequenceProperties.Property property, Object value)
	{
		for (CGenbankSequence sequence : sequences)
		{
			sequence.setValue(property,value);
		}
	}
	
	public static void setProperties(List<CGenbankSequence> sequences, CTable table, boolean overwrite)
	{
		Map<String,CGenbankSequence> map=new HashMap<String,CGenbankSequence>();
		for (CGenbankSequence sequence : sequences)
		{
			map.put(sequence.getAccession(),sequence);
		}
		for (int index=1;index<table.getHeader().getCells().size();index++)
		{
			String name=table.getHeader().getValue(index);
			CSequenceProperties.Property property=CSequenceProperties.findProperty(name);
			for (CTable.Row row : table.getRows())
			{
				String accession=row.getValue(0);
				Object value=row.getCell(index).getValue();
				CGenbankSequence sequence=map.get(accession);
				if (!overwrite)
				{
					Object curvalue=sequence.getValue(property);
					if (!CStringHelper.hasContent(curvalue))
						sequence.setValue(property, value);
				}
				else sequence.setValue(property, value);
			}
		}
	}
	
	
	public static Map<String,String> createSequenceMap(List<CGenbankSequence> sequences)
	{
		Map<String,String> map=new LinkedHashMap<String,String>();
		for (CGenbankSequence sequence : sequences)
		{
			if (CStringHelper.hasContent(sequence.getSequence()))
				map.put(sequence.getIdentifier(),sequence.getSequence());
		}
		return map;
	}
	
	public static Map<String,String> createSplicedMap(List<CGenbankSequence> sequences)
	{
		Map<String,String> map=new LinkedHashMap<String,String>();
		for (CGenbankSequence sequence : sequences)
		{
			if (CStringHelper.hasContent(sequence.getSpliced()))
				map.put(sequence.getIdentifier(),sequence.getSpliced());
		}
		return map;
	}
	
	public static Map<String,String> createTranslationMap(List<CGenbankSequence> sequences)
	{
		Map<String,String> map=new LinkedHashMap<String,String>();
		for (CGenbankSequence sequence : sequences)
		{
			if (CStringHelper.hasContent(sequence.getTranslation()))
				map.put(sequence.getIdentifier(),sequence.getTranslation());
		}
		return map;
	}
	*/
}
