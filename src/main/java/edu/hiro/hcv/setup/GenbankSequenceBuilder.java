package edu.hiro.hcv.setup;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.biojavax.bio.seq.RichFeature;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import com.google.common.collect.Lists;

import edu.hiro.hcv.bio.BiojavaHelper;
import edu.hiro.hcv.morphia.Feature;
import edu.hiro.hcv.morphia.Sequence;
import edu.hiro.hcv.util.CException;
import edu.hiro.hcv.util.FileHelper;
import edu.hiro.hcv.util.StringHelper;

public class GenbankSequenceBuilder
{
	// private constructor to enforce singleton pattern
	private GenbankSequenceBuilder(){}
	
	/*
	public static void parseFolder(String folder, List<Sequence> sequences)
	{
		List<String> filenames=FileHelper.listFilesRecursively(folder,".gb");
		parseFiles(filenames,sequences);
	}
	
	public static void parseFiles(List<String> filenames, List<Sequence> sequences)
	{
		for (String filename : filenames)
		{
			parseFile(filename,sequences);
		}
	}
	
	public static List<Sequence> parseFile(String filename)
	{
		List<Sequence> sequences=Lists.newArrayList();
		parseFile(filename,sequences);
		return sequences;
	}
	
	public static void parseFile(String filename, List<Sequence> sequences)
	{
		String str=FileHelper.readFile(filename);
		parse(str,sequences);
	}
	
	public static void parse(String gb, List<Sequence> sequences)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new StringReader(gb));
			RichSequenceIterator iter = RichSequence.IOTools.readGenbankDNA(reader,BiojavaHelper.NAMESPACE);
			RichSequence richsequence=null;
			while(iter.hasNext())
			{
				richsequence=iter.nextRichSequence();
				convert(richsequence,sequences);
			}
		}
		catch (Exception e)
		{
			throw new CException(e);
		}
	}
	
//	public static RichSequence read(String gb)
//	{
//		try
//		{
//			BufferedReader reader = new BufferedReader(new StringReader(gb));
//			RichSequenceIterator iter = RichSequence.IOTools.readGenbankDNA(reader,NAMESPACE);
//			if (!iter.hasNext())
//				return null; 
//			return iter.nextRichSequence();
//		}
//		catch (Exception e)
//		{
//			System.err.println("could not parse GenBank file: "+e);
//			throw new CException(e);
//		}		
//	}
	
	private static void convert(RichSequence richsequence, List<Sequence> sequences)
	{		
		Sequence sequence=convert(richsequence);
		sequences.add(sequence);
	}
	
	public static Sequence convert(RichSequence richsequence)
	{
		Sequence sequence=createSequence(richsequence);
		sequence.setSequence(richsequence.seqString());
		sequence.setNtlength(sequence.getSequence().length());
		for (Iterator<?> i = richsequence.features();i.hasNext();)
		{
			RichFeature feature = (RichFeature)i.next();
			setProperties(sequence,richsequence,feature);
		}
		return sequence;
	}

	private static Sequence createSequence(RichSequence richsequence)
	{
		String accession=BiojavaHelper.stripVersion(richsequence.getName());
		Sequence sequence=new Sequence();
		Map<String,String> annotations=BiojavaHelper.getAnnotations(richsequence);
		sequence.setGi(Integer.parseInt(richsequence.getIdentifier()));
		sequence.setAccession(accession);
		sequence.setDescription(BiojavaHelper.clean(richsequence.getDescription()));
		sequence.setVersion(richsequence.getAccession());
		sequence.setTaxon(BiojavaHelper.getTaxon(richsequence));
		sequence.setUdate(BiojavaHelper.getUdate(annotations));
		sequence.setRef(StringHelper.join(BiojavaHelper.getRefs(richsequence),";"));

//		//display(richsequence);		
//		sequence.setGi(Integer.parseInt(richsequence.getIdentifier()));
//		sequence.setAccession(stripVersion(richsequence.getName()));
//		sequence.setDefline(clean(richsequence.getDescriptin()));
//		sequence.setVersion(richsequence.getAccession());
//		sequence.setCircular(getCircular(richsequence));
//		sequence.setDivision(richsequence.getDivision());
//		sequence.setTaxon(getTaxon(richsequence));
//		sequence.setUdate(getUdate(annotations));
//		//sequence.setKw(getKw(annotations));
//		sequence.setComments(getComments(richsequence));
//		sequence.setConceptual(getConceptual(sequence.getComments()));
//		sequence.setEc(getEc(sequence.getComments()));
//		sequence.setStrand(StrandType.forward);
//		sequence.setRef(StringHelper.join(getRefs(richsequence),";"));
		return sequence;
	}
	
	private static void setProperties(Sequence sequence, RichSequence richsequence, RichFeature feature)
	{
		String featuretype=feature.getType();
		Map<String,String> annotations=BiojavaHelper.getAnnotations(feature);
		Map<String,String> crossrefs=BiojavaHelper.getCrossrefs(feature);
		if ("source".equals(featuretype))
			setSourceProperties(sequence,richsequence,feature,annotations);
		else if ("gene".equals(featuretype))
			addGeneProperties(sequence,richsequence,feature,annotations,crossrefs);
		else if ("CDS".equals(featuretype))
			addCdsProperties(sequence,richsequence,feature,annotations,crossrefs);
		else System.out.println("unhandleded feature type: "+featuretype);
	}

	private static void setSourceProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations)
	{		
		Feature source=sequence.getSource();
		source.setProperty("start",richfeature.getLocation().getMin());
		source.setProperty("end",richfeature.getLocation().getMax());
		
		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("pseudogene"))
				source.setProperty("pseudogene",BiojavaHelper.getPseudogene(annotations));
			else if (name.equals("biojavax:note"))
				source.setProperty("note",BiojavaHelper.getNote(annotations));
			else if (name.equals("biojavax:country"))
			{
				source.setProperty("country",BiojavaHelper.getCountry(value));
				source.setProperty("subregion",BiojavaHelper.getSubregion(value));
			}
			else source.setProperty(name,value);
		}
		
		source.setProperty("Subtype",BiojavaHelper.getSubtype(annotations));
		source.setProperty("codedby",richsequence.getName());
		source.setProperty("note",BiojavaHelper.getNote(annotations));		
	}	
	
	private static void addGeneProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations, Map<String,String> crossrefs)
	{
		Feature feature=sequence.createFeature("gene");
		feature.setName(annotations.get("biojavax:gene"));
		feature.setStart(richfeature.getLocation().getMin());
		feature.setEnd(richfeature.getLocation().getMax());
		//feature.setSequence(BiojavaHelper.extractSubsequence(richsequence,richfeature));
		//feature.setNtlength(sequence.getSequence().length());
		
		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("pseudogene"))
				feature.setProperty("pseudogene",BiojavaHelper.getPseudogene(annotations));
			else if (name.equals("biojavax:note"))
				feature.setProperty("note",BiojavaHelper.getNote(annotations));
			else feature.setProperty(name,value);
		}
		
		for (String name : crossrefs.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("GeneID"))
				feature.setProperty("geneid",BiojavaHelper.getIntCrossRef(crossrefs,"GeneID"));
			else feature.setProperty(name,value);
		}
	}
	
	private static void addCdsProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations, Map<String,String> crossrefs)
	{
		Feature feature=sequence.createFeature("CDS");
		feature.setName(annotations.get("biojavax:product"));
		feature.setStart(richfeature.getLocation().getMin());
		feature.setEnd(richfeature.getLocation().getMax());
		//feature.setSequence(BiojavaHelper.extractSubsequence(richsequence,richfeature));

		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("biojavax:codon_start"))
				feature.setProperty("codon_start",BiojavaHelper.getIntAnnotation(annotations,"biojavax:codon_start"));
			else if (name.equals("biojavax:note"))
				feature.setProperty("note",BiojavaHelper.getNote(annotations));
			else feature.setProperty(name,value);
		}
		
		for (String name : crossrefs.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("GI"))
				feature.setProperty("protein_gi",BiojavaHelper.getIntCrossRef(crossrefs,"GI"));
			else feature.setProperty(name,value);
		}
		
		feature.setProperty("uniprot",BiojavaHelper.getUniprot(crossrefs));
		feature.setProperty("splicing",BiojavaHelper.getSplicing(richfeature.getLocation().getMin(),richfeature));
		feature.setProperty("spliced",BiojavaHelper.extractSubsequence(richsequence,richfeature));
		feature.setProperty("pseudogene",BiojavaHelper.getPseudogene(annotations));
		feature.setProperty("strand",BiojavaHelper.getStrand(richfeature));
		feature.setProperty("note",BiojavaHelper.getNote(annotations));	
	}
	*/
}