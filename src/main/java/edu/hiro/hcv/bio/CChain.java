package edu.hiro.hcv.bio;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class CChain
{
	protected String identifier;
	protected String name;
	protected String swissprot=null;
	protected String sequence="";
	protected String secondaryStructure="";
	protected CStructure structure;
	protected Integer numsequences=0;
	//protected Set<CChainBlastHit> hits=new LinkedHashSet<CChainBlastHit>();

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="structure_id")
	public CStructure getStructure(){return this.structure;}
	public void setStructure(final CStructure structure){this.structure=structure;}
	
	public CChain() {}
	
	public CChain(String identifier)
	{
		this.identifier=identifier;
		this.name=identifier;
	}
	
	@Transient
	public int getLength()
	{
		return this.sequence.length();
	}

	/*
	public Set<CChainBlastHit> getHits(){return this.hits;}
	public void setHits(final Set<CChainBlastHit> hits){this.hits=hits;}
	
	public void addHit(CChainBlastHit hit)
	{
		if (!this.hits.contains(hit))
		{
			hit.setChain(this);
			hit.setChainname(this.name);
			this.hits.add(hit);
		}
	}
	*/
}