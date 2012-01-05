package edu.hiro.hcv.bio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEquals
public class CStructure
{
	protected String identifier;
	protected String name;
	protected String description;
	protected String technique=null;
	protected Float resolution=null;
	protected Integer numchains=0;
	protected Integer numsequences=0;
	protected List<CChain> chains=new ArrayList<CChain>();

	public CStructure() {}
	
	public CStructure(String pdbId)
	{
		this.identifier=pdbId.toUpperCase();
		this.name=this.identifier;
	}
	
	public void add(CChain chain)
	{
		chain.setStructure(this);
		getChains().add(chain);
		chain.setIdentifier(getName()+chain.getName());
	}

	public CChain findOrCreateChain(String name)
	{
		CChain chain=findChain(name);
		if (chain==null)
		{
			chain=new CChain(name);
			add(chain);
		}
		return chain;
	}
	
	public CChain findChain(String name)
	{
		for (CChain chain : this.chains)
		{
			if (chain.getName().equals(name))
				return chain;
		}
		return null;
	}
}