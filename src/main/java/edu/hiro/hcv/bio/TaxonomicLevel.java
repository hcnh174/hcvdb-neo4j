package edu.hiro.hcv.bio;

public enum TaxonomicLevel
{
	SUPERKINGDOM,KINGDOM,SUBKINGDOM,
	SUPERPHYLUM,PHYLUM,SUBPHYLUM,
	SUPERCLASS,CLASS,SUBCLASS,INFRACLASS,
	SUPERORDER,ORDER,SUBORDER,INFRAORDER,
	SUPERFAMILY,FAMILY,SUBFAMILY,
	GENUS(true),SUBGENUS(true),
	SPECIES_GROUP(true),SPECIES_SUBGROUP(true),SPECIES(true),SUBSPECIES(true),
	SUBTYPE(true),STRAIN(true),NO_RANK(true),
	VARIETAS,TRIBE,SUBTRIBE,FORMA,PARVORDER,
	NONE,
	OTHER,
	UNRECOGNIZED,
	UNKNOWN;
	
	protected boolean italicized=false;
	
	TaxonomicLevel()
	{
		this(false);
	}
	
	TaxonomicLevel(final boolean italicized)
	{
		this.italicized=italicized;
	}
	
	public boolean getItalicized(){return this.italicized;}
	
	public static TaxonomicLevel lookup(final String name)
	{
		String rank=name.toUpperCase().replace(" ","_");
		try
		{
			return TaxonomicLevel.valueOf(rank);
		}
		catch(IllegalArgumentException e)
		{
			System.err.println("UNRECOGNIZED rank: "+name);
			return UNRECOGNIZED;
		}
	}
}