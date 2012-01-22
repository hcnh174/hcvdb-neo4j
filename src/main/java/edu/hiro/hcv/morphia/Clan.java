package edu.hiro.hcv.morphia;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Transient;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class Clan extends Tag
{	
	protected Integer numsequences=0;
	protected Set<Pfam> pfams=new LinkedHashSet<Pfam>();

	public Clan() {}
	
	public Clan(String identifier)
	{
		this.identifier=identifier;
	}
	
	public void add(Pfam pfam)
	{
		if (!this.pfams.contains(pfam))
		{
			//pfam.setClan(this);
			this.pfams.add(pfam);
		}
	}

	@Transient
	public String getUrl()
	{
		return "http://pfam.sanger.ac.uk/clan?acc="+this.name;
	}
}