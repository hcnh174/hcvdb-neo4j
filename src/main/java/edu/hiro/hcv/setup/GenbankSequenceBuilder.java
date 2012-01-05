package edu.hiro.hcv.setup;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.biojavax.Namespace;
import org.biojavax.SimpleNamespace;
import org.biojavax.bio.seq.RichFeature;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;

import com.google.common.collect.Lists;

import edu.hiro.hcv.bio.BiojavaHelper;
import edu.hiro.hcv.sequences.Sequence;
import edu.hiro.hcv.util.CException;
import edu.hiro.hcv.util.FileHelper;
import edu.hiro.hcv.util.StringHelper;

public class GenbankSequenceBuilder
{
	// private constructor to enforce singleton pattern
	private GenbankSequenceBuilder(){}
	
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
		/*
		//display(richsequence);		
		sequence.setGi(Integer.parseInt(richsequence.getIdentifier()));
		sequence.setAccession(stripVersion(richsequence.getName()));
		sequence.setDefline(clean(richsequence.getDescriptin()));
		sequence.setVersion(richsequence.getAccession());
		sequence.setCircular(getCircular(richsequence));
		sequence.setDivision(richsequence.getDivision());
		sequence.setTaxon(getTaxon(richsequence));
		sequence.setUdate(getUdate(annotations));
		//sequence.setKw(getKw(annotations));
		sequence.setComments(getComments(richsequence));
		sequence.setConceptual(getConceptual(sequence.getComments()));
		sequence.setEc(getEc(sequence.getComments()));
		sequence.setStrand(StrandType.forward);
		sequence.setRef(StringHelper.join(getRefs(richsequence),";"));
		*/
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
		Map<String,Object> properties=sequence.getSource();
		properties.put("start",richfeature.getLocation().getMin());
		properties.put("end",richfeature.getLocation().getMax());
		
		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("pseudogene"))
				properties.put("pseudogene",BiojavaHelper.getPseudogene(annotations));
			else if (name.equals("biojavax:note"))
				properties.put("note",BiojavaHelper.getNote(annotations));
			else if (name.equals("biojavax:country"))
			{
				properties.put("country",BiojavaHelper.getCountry(value));
				properties.put("subregion",BiojavaHelper.getSubregion(value));
			}
			else properties.put(name,value);
		}
		
		properties.put("Subtype",BiojavaHelper.getSubtype(annotations));
		properties.put("codedby",richsequence.getName());
		properties.put("note",BiojavaHelper.getNote(annotations));		
	}	
	
	private static void addGeneProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations, Map<String,String> crossrefs)
	{
		Sequence.Feature feature=sequence.createFeature("gene");
		feature.setName(annotations.get("biojavax:gene"));
		feature.setStart(richfeature.getLocation().getMin());
		feature.setEnd(richfeature.getLocation().getMax());
		feature.setSequence(BiojavaHelper.extractSubsequence(richsequence,richfeature));
		//feature.setNtlength(sequence.getSequence().length());
		
		Map<String,Object> properties=feature.getProperties();
		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("pseudogene"))
				properties.put("pseudogene",BiojavaHelper.getPseudogene(annotations));
			else if (name.equals("biojavax:note"))
				properties.put("note",BiojavaHelper.getNote(annotations));
			else properties.put(name,value);
		}
		
		for (String name : crossrefs.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("GeneID"))
				properties.put("geneid",BiojavaHelper.getIntCrossRef(crossrefs,"GeneID"));
			else properties.put(name,value);
		}
	}
	
	private static void addCdsProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations, Map<String,String> crossrefs)
	{
		Sequence.Feature feature=sequence.createFeature("CDS");
		feature.setName(annotations.get("biojavax:product"));
		feature.setStart(richfeature.getLocation().getMin());
		feature.setEnd(richfeature.getLocation().getMax());
		feature.setSequence(BiojavaHelper.extractSubsequence(richsequence,richfeature));
		Map<String,Object> properties=feature.getProperties();
		for (String name : annotations.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("biojavax:codon_start"))
				properties.put("codon_start",BiojavaHelper.getIntAnnotation(annotations,"biojavax:codon_start"));
			else if (name.equals("biojavax:note"))
				properties.put("note",BiojavaHelper.getNote(annotations));
			else properties.put(name,value);
		}
		
		for (String name : crossrefs.keySet())
		{
			String value=annotations.get(name);
			name=BiojavaHelper.stripBiojavaPrefix(name);
			if (name.equals("GI"))
				properties.put("protein_gi",BiojavaHelper.getIntCrossRef(crossrefs,"GI"));
			else properties.put(name,value);
		}
		
		properties.put("uniprot",BiojavaHelper.getUniprot(crossrefs));
		properties.put("splicing",BiojavaHelper.getSplicing(richfeature.getLocation().getMin(),richfeature));
		properties.put("spliced",BiojavaHelper.extractSubsequence(richsequence,richfeature));
		properties.put("pseudogene",BiojavaHelper.getPseudogene(annotations));
		properties.put("strand",BiojavaHelper.getStrand(richfeature));
		properties.put("note",BiojavaHelper.getNote(annotations));	
	}
	
	/*
	private static void convert(RichSequence richsequence, List<Sequence> sequences)
	{		
		FeatureFilter geneFilter=new FeatureFilter.ByType("gene"); // hack? CDS
		FeatureFilter cdsFilter=new FeatureFilter.ByType("CDS");
		FeatureFilter filter = new FeatureFilter.Or(geneFilter,cdsFilter);
		
		NonOverlappingLocations locations=new NonOverlappingLocations();
		FeatureHolder holder=richsequence.filter(filter);
		for (Iterator<?> i = holder.features();i.hasNext();)
		{
			RichFeature feature = (RichFeature)i.next();
			locations.add(feature.getLocation());
		}
		
		if (locations.getLocations().size()==0)
		{
			Sequence sequence=convert(richsequence);
			sequences.add(sequence);
			return;
		}
		
		for (Location location : locations.getLocations())
		{
			Sequence sequence=convert(richsequence,location);
			sequences.add(sequence);
		}
	}
	*/
	
	
	/*
	// assume one CDS - extract translation
	// get all matpeptides
	private static void convertPolyprotein(RichSequence richsequence, List<Sequence> sequences)
	{
		Sequence template=new Sequence();
		RichFeature cds;
		for (Iterator<?> i = richsequence.features();i.hasNext();)
		{
			RichFeature feature = (RichFeature)i.next();
			String featuretype=feature.getType();
			Map<String,String> annotations=getAnnotations(feature);
			Map<String,String> crossrefs=getCrossrefs(feature);
			if ("source".equals(featuretype))
				setSourceProperties(template,richsequence,feature,annotations);
			else if ("CDS".equals(featuretype))
			{
				cds=feature;
				setCdsProperties(template,richsequence,feature,annotations,crossrefs);
			}
		}
			
		for (Iterator<?> i = richsequence.features();i.hasNext();)
		{
			RichFeature feature = (RichFeature)i.next();
			String featuretype=feature.getType();
			Map<String,String> annotations=getAnnotations(feature);
			Map<String,String> crossrefs=getCrossrefs(feature);
			if ("mat_peptide".equals(featuretype))
			{
				Sequence sequence=new Sequence(template);
				setMatPeptideProperties(sequence,richsequence,feature,annotations,crossrefs,cds);
			}
		}
	}
	
	private static void setMatPeptideProperties(Sequence sequence, RichSequence richsequence, RichFeature richfeature,
			Map<String,String> annotations, Map<String,String> crossrefs,
			RichFeature cds)
	{
		String translation=annotations.get("biojavax:translation");
		int transstart=
		
		//sequence.setProduct(annotations.get("biojavax:product"));
		//sequence.setProtein_id(annotations.get("biojavax:protein_id"));
		//sequence.setUniprot(getUniprot(crossrefs));
		//sequence.setTranslation(annotations.get("biojavax:translation"));
		//sequence.updateAalength();
		sequence.setSplicing(getSplicing(richfeature.getLocation().getMin(),richfeature));
		sequence.setSpliced(extractSubsequence(richsequence,richfeature));
		//sequence.setPseudogene(getPseudogene(annotations));
		sequence.setStrand(getStrand(richfeature));
		sequence.addNote(getNote(annotations));
	}
	*/
	

	// don't filter by location
	
	
//	public static Sequence convert(RichSequence richsequence, Location location)
//	{
//		Sequence sequence=createSequence(richsequence);
//		//System.out.println("checking location "+location.getMin()+".."+location.getMax());
//		FeatureFilter locationFilter = new FeatureFilter.ShadowOverlapsLocation(location);
//		for (Iterator<?> i = richsequence.filter(locationFilter).features();i.hasNext();)
//		{
//			RichFeature feature = (RichFeature)i.next();
//			setProperties(sequence,richsequence,feature);
//		}
//		return sequence;
//	}
//	
//	public static ListMultimap<String,Location> findCodingRegions(String gpt, MessageWriter writer)
//	{
//		try
//		{
//			writer.message("finding coding regions");
//			ListMultimap<String,Location> locations=ArrayListMultimap.create();//Multimaps.newArrayListMultimap();
//			BufferedReader reader = new BufferedReader(new StringReader(gpt));			
//			RichSequenceIterator iter = RichSequence.IOTools.readGenbankProtein(reader,NAMESPACE);
//			while(iter.hasNext())
//			{
//				RichSequence richsequence = iter.nextRichSequence();
//				FeatureFilter filter=new FeatureFilter.HasAnnotation("coded_by");
//				for (Iterator<?> i = richsequence.filter(filter).features();i.hasNext();)
//				{
//					RichFeature feature = (RichFeature)i.next();
//					//display(feature);
//					Map<String,String> annotations=getAnnotations(feature);
//					String coded_by=annotations.get("biojavax:coded_by");
//					if (coded_by==null)
//						continue;
//					writer.message("found coded_by annotation: "+coded_by);
//					int index=coded_by.indexOf(':');
//					String accession=stripVersion(coded_by);					
//					String loc=coded_by.substring(index+1);
//					Location location=parseLocation(richsequence,loc);
//					locations.put(accession,location);
//				}
//			}
//			return locations;
//		}
//		catch (Exception e)
//		{
//			throw new CException(e);
//		}
//	}
//	
//	public static ListMultimap<String,Location> getLocationsFromGeneSummaries(String summaries)
//	{
//		String regex="Annotation: ([a-zA-Z0-9_.]+) \\(([0-9]+)\\.\\.([0-9]+)(, complement)?\\)";
//		Pattern pattern=Pattern.compile(regex);
//		Matcher matcher=pattern.matcher(summaries);
//		ListMultimap<String,Location> locations=ArrayListMultimap.create();
//		while (matcher.find())
//		{
//			System.out.println("found regex match: "+matcher.group(0));
//			String accession=matcher.group(1);
//			int start=Integer.parseInt(matcher.group(2));
//			int end=Integer.parseInt(matcher.group(3));
//			Location location=createLocation(start, end);
//			System.out.println("adding location: "+accession+":"+start+".."+end);
//			if (!locations.containsEntry(accession, location))
//				locations.put(accession,location);
//			else System.out.println("found duplicate location: "+accession+":"+start+".."+end);
//		}
//		return locations;
//	}
////	
//	// filter GenBank files based on coding regions	
//	public static void convertByLocation(String gbk, ListMultimap<String,Location> locations, List<Sequence> sequences, MessageWriter writer)
//	{	
//		BufferedReader reader = new BufferedReader(new StringReader(gbk));
//		RichSequenceIterator iter = RichSequence.IOTools.readGenbankDNA(reader,NAMESPACE);
//		while(iter.hasNext())
//		{
//			RichSequence richsequence = nextRichSequence(iter);
//			if (richsequence==null)
//				continue;
//			//String accession=richsequence.getAccession();
//			String accession=richsequence.getName();
//			writer.message("parsing Genbank sequence "+accession);
//			convertByLocation(richsequence, locations.get(accession), sequences);
//		}
//	}
//	
//	public static void convertByLocation(String gbk, List<Location> locations, List<Sequence> sequences, MessageWriter writer)
//	{	
//		BufferedReader reader = new BufferedReader(new StringReader(gbk));
//		RichSequenceIterator iter = RichSequence.IOTools.readGenbankDNA(reader,NAMESPACE);
//		while(iter.hasNext())
//		{
//			RichSequence richsequence = nextRichSequence(iter);
//			if (richsequence==null)
//				continue;
//			String accession=richsequence.getName();
//			writer.message("parsing Genbank sequence "+accession);
//			convertByLocation(richsequence, locations, sequences);
//		}
//	}
//

	
	
	
	////////////////////////////////////////////////////

}