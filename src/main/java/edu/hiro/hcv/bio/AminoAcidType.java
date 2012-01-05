package edu.hiro.hcv.bio;

import edu.hiro.hcv.util.CException;

public enum AminoAcidType
{	
	ALIPHATIC("Aliphatic"),
	AROMATIC("Aromatic"),
	POLAR("Polar"),
	TINY("Tiny"),
	CYSTEINE("Cysteine"),
	OTHER("Other");
	
	private final String name;

	AminoAcidType(String name)
	{
		this.name=name;
	}
	
	public String getName(){return this.name;}
		
	public static AminoAcidType find(String name)
	{
		for (AminoAcidType type : AminoAcidType.values())
		{
			if (type.getName().equals(name))
				return type;
		}
		throw new CException("can't find AminoAcidType: "+name);
	}
}