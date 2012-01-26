package edu.hiro.hcv.setup;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.biojavax.Comment;
import org.biojavax.bio.seq.RichFeature;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import com.google.common.collect.Lists;

import edu.hiro.hcv.bio.BiojavaHelper;
import edu.hiro.hcv.morphia.Feature;
import edu.hiro.hcv.neo4j.SequenceNode;
import edu.hiro.hcv.util.CException;
import edu.hiro.hcv.util.FileHelper;

public class GenbankSequenceBuilder
{
	private GenbankSequenceBuilder(){}
	
	public static void parseFolder(String folder, List<SequenceNode> sequences)
	{
		List<String> filenames=FileHelper.listFilesRecursively(folder,".gb");
		parseFiles(filenames,sequences);
	}
	
	public static void parseFiles(List<String> filenames, List<SequenceNode> sequences)
	{
		for (String filename : filenames)
		{
			parseFile(filename,sequences);
		}
	}
	
	public static List<SequenceNode> parseFile(String filename)
	{
		List<SequenceNode> sequences=Lists.newArrayList();
		parseFile(filename,sequences);
		return sequences;
	}
	
	public static void parseFile(String filename, List<SequenceNode> sequences)
	{
		String str=FileHelper.readFile(filename);
		parse(str,sequences);
	}
	
	public static void parse(String gb, List<SequenceNode> sequences)
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
	
	private static void convert(RichSequence richsequence, List<SequenceNode> sequences)
	{		
		SequenceNode sequence=convertSequence(richsequence);
		sequences.add(sequence);
	}
	
	@SuppressWarnings("unchecked")
	public static SequenceNode convertSequence(RichSequence richsequence)
	{
		BiojavaHelper.display(richsequence);
		String seq=richsequence.seqString();
		String accession=richsequence.getName();
		SequenceNode sequence=new SequenceNode(accession,seq);
		sequence.setGi(Integer.parseInt(richsequence.getIdentifier()));
		sequence.setAccession(accession);
		sequence.setDescription(BiojavaHelper.clean(richsequence.getDescription()));
		sequence.setTaxon_id(BiojavaHelper.getTaxon(richsequence));
		
		for(Comment comment : (Set<Comment>)richsequence.getComments())
		{
			sequence.addComment(BiojavaHelper.clean(comment.getComment()));
		}
		for (Integer ref : BiojavaHelper.getRefs(richsequence))
		{
			sequence.addRef(ref);
		}
		/*
		for (Iterator<?> i = richsequence.features();i.hasNext();)
		{
			RichFeature richfeature = (RichFeature)i.next();
			Feature feature=convertFeature(sequence,richsequence,richfeature);
			sequence.addFeature(feature);
		}
		*/
		return sequence;
	}
	
	/*
	private static Feature convertFeature(SequenceNode sequence, RichSequence richsequence, RichFeature richfeature)
	{
		//BiojavaHelper.display(richfeature);
		String featuretype=richfeature.getType();		
		int start=richfeature.getLocation().getMin();
		int end=richfeature.getLocation().getMax();
		String seq=BiojavaHelper.extractSubsequence(richsequence,richfeature);
		Feature feature=new Feature(featuretype,start,end,seq);
		
		Map<String,String> annotations=BiojavaHelper.getAnnotations(richfeature);		
		for (String annotation : annotations.keySet())
		{
			String name=BiojavaHelper.stripBiojavaPrefix(annotation);
			feature.setAnnotation(name,annotations.get(annotation));
		}
		Map<String,String> crossrefs=BiojavaHelper.getCrossrefs(richfeature);
		for (String crossref : crossrefs.keySet())
		{
			feature.setCrossref(crossref,crossrefs.get(crossref));
		}
		return feature;
	}
	*/
}