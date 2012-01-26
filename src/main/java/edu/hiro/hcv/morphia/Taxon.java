package edu.hiro.hcv.morphia;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import edu.hiro.hcv.bio.TaxonomicLevel;

@RooJavaBean
@RooToString
@RooEquals
@Entity("taxa")
public class Taxon
{
	public final static int BACTERIA=2;
	public final static int EUKARYOTES=2759;
	public final static int VIRUSES=10239;
	
	@Id protected Integer id;
	protected String name="";
	protected String description="";
	protected TaxonomicLevel level=TaxonomicLevel.UNKNOWN;
	protected Integer parent_id;
	protected Set<Integer> child_ids=Sets.newLinkedHashSet();
	protected List<Integer> lineage_ids=Lists.newArrayList();

	@Reference protected Taxon parent;
	@Reference protected Set<Taxon> children=Sets.newLinkedHashSet();
	@Reference protected List<Taxon> lineage=Lists.newArrayList();
	
	
	//protected Boolean initialized=false;
	//protected Integer left=0;
	//protected Integer right=0;
	
	/*
	protected Taxon parent;
	protected Set<Taxon> taxa=new LinkedHashSet<Taxon>();
	protected List<Taxon> lineage=new ArrayList<Taxon>();
	*/
	
	/*
	public String getXml()
	{			
		StringBuilder buffer=new StringBuilder();
		getXml(buffer);
		return buffer.toString();
	}
	*/
	
	public Taxon(){}

	public Taxon(int id)
	{
		this.id=id;
		this.name="taxid:"+id;
	}
	
	public Taxon(String identifier)
	{
		this(Integer.parseInt(identifier));
	}
	
	public void add(Taxon taxon)
	{
		taxon.setParent_id(this.id);
		this.child_ids.add(taxon.id);
	}

	public void assemble(Map<Integer,Taxon> taxa)
	{
		if (this.parent_id!=null)
			this.parent=taxa.get(this.parent_id);
		for (Integer id : this.child_ids)
		{
			Taxon child=taxa.get(id);
			if (child!=null)
				this.children.add(child);
		}
		/*
		for (Integer id : this.lineage_ids)
		{
			Taxon ancestor=taxa.get(id);
			if (ancestor!=null)
				this.lineage.add(ancestor);
		}
		*/
	}
	
	/*
	public boolean getHaschildren()
	{
		return (this.right-this.left==1);			
	}
		
	public void add(Taxon taxon)
	{
		taxon.setParent(this);
		this.taxa.add(taxon);
	}
	
	public void getXml(StringBuilder buffer)
	{
		getXml(buffer,0);
	}
	
	public void getXml(StringBuilder buffer, int indent)
	{
		String padding=StringHelper.repeatString("\t", indent);
		buffer.append(padding+"<taxon");
		buffer.append(" identifier=\""+getIdentifier()+"\"");
		if (this.parent!=null)
			buffer.append(" parent=\""+this.parent.getIdentifier()+"\"");
		buffer.append(">\n");
		buffer.append(padding+"\t<name>"+this.name+"</name>\n");
		buffer.append(padding+"\t<level>"+this.level.name()+"</level>\n");
		buffer.append(padding+"\t<taxid>"+this.taxid+"</taxid>\n");
		buffer.append(padding+"</taxon>\n");
	}
	
	private static Integer counter=0;
	
	public void index()
	{
		for (Taxon taxon : this.taxa)
		{
			index(taxon);
		}
	}

	private void index(Taxon taxon)
	{
		taxon.setLeft(Taxon.counter++);
		for (Taxon child : taxon.getTaxa())
		{
			index(child);
		}
		taxon.setRight(Taxon.counter++);
		taxon.setInitialized(true);
	}
	*/
}
