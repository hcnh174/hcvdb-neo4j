package edu.hiro.hcv.bio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.biojava.bio.seq.Feature;
import org.biojava.bio.seq.FeatureHolder;
import org.biojava.bio.seq.io.ParseException;
import org.biojava.bio.symbol.Location;
import org.biojava.bio.symbol.LocationTools;
import org.biojavax.Comment;
import org.biojavax.DocRef;
import org.biojavax.Namespace;
import org.biojavax.Note;
import org.biojavax.RankedDocRef;
import org.biojavax.RichAnnotation;
import org.biojavax.SimpleNamespace;
import org.biojavax.SimpleRankedCrossRef;
import org.biojavax.bio.seq.RichFeature;
import org.biojavax.bio.seq.RichLocation;
import org.biojavax.bio.seq.RichSequence;
import org.biojavax.bio.seq.RichSequenceIterator;
import org.biojavax.bio.seq.io.GenbankLocationParser;
import org.biojavax.bio.taxa.NCBITaxon;

import edu.hiro.util.CException;
import edu.hiro.util.DateHelper;
import edu.hiro.util.StringHelper;

public class BiojavaHelper
{
	public static final Namespace NAMESPACE=new SimpleNamespace("hirhcvdb");
	
	public static RichSequence nextRichSequence(RichSequenceIterator iter)
	{
		try
		{	
			return iter.nextRichSequence();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void convertByLocation(RichSequence richsequence, Collection<Location> locations, List<Sequence> sequences)
//	{
//		for (Location location : locations)
//		{
//			Sequence sequence=createSequence(richsequence);
//			sequences.add(sequence);	
//			System.out.println("checking location "+location.getMin()+".."+location.getMax());
//			//FeatureFilter locationFilter = new FeatureFilter.ShadowOverlapsLocation(location);
//			FeatureFilter locationFilter = new FeatureFilter.ContainedByLocation(location);
//			for (Iterator<?> i = richsequence.filter(locationFilter).features();i.hasNext();)
//			{
//				RichFeature feature = (RichFeature)i.next();
//				setProperties(sequence,richsequence,feature);
//			}
//		}
//	}
	
	public static String stripBiojavaPrefix(String name)
	{
		if (name.startsWith("biojavax:"))
			return name.substring(9);
		return name;
	}

	public static Location parseLocation(RichSequence richsequence, String loc)
	{
		try
		{
			return GenbankLocationParser.parseLocation(NAMESPACE,richsequence.getName(),loc);
		}
		catch(ParseException e)
		{
			throw new CException(e);
		}
	}
	
	public static Location createLocation(int start, int end)
	{
		return LocationTools.makeLocation(start,end);
	}
	
	
	public static String stripVersion(String accession)
	{
		int index=accession.indexOf('.');
		if (index==-1)
			return accession;
		return accession.substring(0,index);
	}
	
	public static String clean(String value)
	{
		if (value==null)
			return null;
		value=value.trim();
		if (value.indexOf('\n')==-1)
			return value;
		value=StringHelper.replace(value,"\n","|");
		return value;
	}
	
	public static Integer getTaxon(RichSequence richsequence)
	{
		NCBITaxon taxon=richsequence.getTaxon();
		if (taxon==null)
			return null;
		return taxon.getNCBITaxID();
	}
	
//	@SuppressWarnings("unchecked")
//	public static String getComments(RichSequence richsequence)
//	{
//		StringBuilder buffer=new StringBuilder();
//		for(Comment comment : (Set<Comment>)richsequence.getComments())
//		{
//			buffer.append(clean(comment.getComment()));
//			buffer.append("|");
//		}
//		return buffer.toString().trim();
//	}
	
	public static Boolean getConceptual(String comments)
	{
		if (comments.indexOf("conceptual translation")!=-1)
			return true;
		return null;
	}
	
	public static Boolean getCircular(RichSequence richsequence)
	{
		if (richsequence.getCircular())
			return true;
		return null;
	}
	
	public static String getEc(String str)
	{
		if (str.indexOf("EC=")==-1) //EC=6.3.1.2: or EC=6.3.1.2.
			return null;
		String regex="EC=(([0-9]+)\\.([0-9]+)\\.([0-9]+)\\.([0-9]+))";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(str);
		boolean result=matcher.find();
		if (!result)
		{
			System.err.println("can't find match: "+str);
			return null;
		}
		String ec=matcher.group(1);
		//System.out.println("ec="+ec);
		return ec;
	}
	
	public static List<Integer> getRefs(RichSequence richsequence)
	{
		List<Integer> refs=new ArrayList<Integer>();
		for (Object obj : richsequence.getRankedDocRefs())
		{
			RankedDocRef rankedref=(RankedDocRef)obj;
			DocRef ref=rankedref.getDocumentReference();
			if (ref.getCrossref()!=null)
				refs.add(Integer.parseInt(ref.getCrossref().getAccession()));
		}
		return refs;
	}

	public static Date getUdate(Map<String,String> annotations)
	{		
		String strdate=annotations.get("biojavax:udat");
		//System.out.println("udate="+strdate);
		if (strdate==null)
			return null;
		return DateHelper.parse(strdate,"dd-MMM-yyyy"); //26-MAY-2005
	}
	
	public static String getKw(Map<String,String> annotations)
	{		
		return annotations.get("biojavax:kw");
	}
	
	public static String getNote(Map<String,String> annotations)
	{
		String note=annotations.get("biojavax:note");
		if (note==null)
			return "";
		else return clean(note);
	}
	
	public static String getSubtype(Map<String,String> annotations)
	{
		String note=annotations.get("biojavax:note");
		if (note==null)
			return "";
		if (note.indexOf("subtype")==-1)
			return "";
		String regex="subtype[: ]+([a-zA-Z0-9]+)";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(note);
		if (matcher.find())
			return matcher.group(1);
		return "";		
	}
	
	public static String getCountry(String str)
	{
		if (str==null)
			return null;
		int index=str.indexOf(':');
		if (index==-1)
			return str;
		return str.substring(0,index);
	}
	
	public static String getSubregion(String str)
	{
		if (str==null)
			return null;
		int index=str.indexOf(':');
		if (index==-1)
			return "";
		return str.substring(index+1).trim();
	}
	
	public static boolean getPseudogene(Map<String,String> annotations)
	{
		return annotations.containsKey("biojavax:pseudo");
	}		
	
	public static String getUniprot(Map<String,String> crossrefs)
	{
		return getCrossRef(crossrefs,"UniProtKB/Swiss-Prot","UniProtKB/TrEMBL");
	}
	
	////////////////////////////////////////////////////////////////////////////

	
	public static String extractSubsequence(RichSequence sequence, RichFeature richfeature)
	{
		return richfeature.getLocation().symbols(sequence).seqString();
	}
	
	@SuppressWarnings("unchecked")
	public static String getSplicing(int offset, RichFeature richfeature)
	{
		Iterator iter=richfeature.getLocation().blockIterator();
		List<String> exons=new ArrayList<String>();
		while (iter.hasNext())
		{
			RichLocation location=(RichLocation)iter.next();
			int start=location.getMin()-offset+1;
			int end=location.getMax()-offset+1;
			exons.add(start+".."+end);
		}
		return StringHelper.join(exons,",");
	}
	
	public static String getLocation(RichFeature richfeature)
	{
		Iterator<?> iter=richfeature.getLocation().blockIterator();
		List<String> ranges=new ArrayList<String>();
		while (iter.hasNext())
		{
			RichLocation location=(RichLocation)iter.next();
			int start=location.getMin();//+1;
			int end=location.getMax();//+1;
			ranges.add(start+".."+end);
		}
		return StringHelper.join(ranges,",");
	}
	
	public static Map<String,String> getAnnotations(RichSequence richsequence)
	{
		Map<String,String> annotations=new LinkedHashMap<String,String>();
		Map<?,?> map=richsequence.getAnnotation().asMap();
		for (Map.Entry<?,?> entry : map.entrySet())
		{
			annotations.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return annotations;
	}
	
	public static Map<String,String> getAnnotations(RichFeature richfeature)
	{
		Map<String,String> annotations=new LinkedHashMap<String,String>();
		Map<?,?> map=richfeature.getAnnotation().asMap();
		for (Map.Entry<?,?> entry : map.entrySet())
		{
			annotations.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return annotations;
	}
	
	public static Integer getIntAnnotation(Map<String,String> annotations, String name)
	{
		String value=annotations.get(name);
		if (value==null)
			return null;
		return Integer.parseInt(value);
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public static Map<String,String> getCrossrefs(RichFeature richfeature)
	{
		Map<String,String> crossrefs=new LinkedHashMap<String,String>();
		for (Object obj : richfeature.getRankedCrossRefs())
		{
			SimpleRankedCrossRef crossref=(SimpleRankedCrossRef)obj;
			crossrefs.put(crossref.getCrossRef().getDbname(),crossref.getCrossRef().getAccession());
		}
		return crossrefs;
	}
	
	public static String getCrossRef(Map<String,String> crossrefs, String name1, String name2)
	{
		String value=getCrossRef(crossrefs,name1);
		if (value!=null)
			return value;
		return getCrossRef(crossrefs,name2);
	}
	
	public static String getCrossRef(Map<String,String> crossrefs, String name)
	{
		if (!crossrefs.containsKey(name))
			return null;
		return crossrefs.get(name);
	}
	
	public static Integer getIntCrossRef(Map<String,String> crossrefs, String name)
	{
		String value=getCrossRef(crossrefs,name);
		if (value==null)
			return null;
		return Integer.parseInt(value);
	}
	
	public static StrandType getStrand(RichFeature richfeature)
	{
		RichLocation location=(RichLocation)richfeature.getLocation();
		//System.out.println("strand="+location.getStrand());
		if (location.getStrand()==RichLocation.Strand.POSITIVE_STRAND)
			return StrandType.forward;
		if (location.getStrand()==RichLocation.Strand.NEGATIVE_STRAND)
			return StrandType.reverse;
		return null;				
	}
	
	///////////////////////////////////////////////////////////////////////////////
	
	public static void display(FeatureHolder fh, PrintStream pw, String prefix)
	{
	    for (Iterator<Feature> i = fh.features(); i.hasNext(); )
	    {
	        Feature f=i.next();
			pw.print(prefix);
			pw.print(f.getType());
			pw.print(" at ");
			pw.print(f.getLocation().toString());
			pw.print(f.getParent());
			pw.println();
			display(f, pw, prefix+"   ");
	    }
	}
	
	public static void display(RichFeature feature)
	{
		System.out.println("type="+feature.getType());
		System.out.println("location.min="+feature.getLocation().getMin());
		System.out.println("location.max="+feature.getLocation().getMax());
		System.out.println("location="+feature.getLocation());
		Map<?,?> annotations=feature.getAnnotation().asMap();
		System.out.println("annotations");
		for (Object key : annotations.keySet())
		{
			String name=key.toString();
			Object value=annotations.get(key);
			System.out.println("\t\t: "+name+ ": " + value);
		}
		System.out.println("crossrefs");
		for (Object obj : feature.getRankedCrossRefs())
		{
			SimpleRankedCrossRef crossref=(SimpleRankedCrossRef)obj;
			System.out.println("\t\t: "+crossref.getCrossRef());
			System.out.println("\t\t\t-dbname:"+crossref.getCrossRef().getDbname());
			System.out.println("\t\t\t-accession:"+crossref.getCrossRef().getAccession());
		}
	}
	
	@SuppressWarnings({"unchecked"})
	public static void display(RichSequence richsequence)
	{
		String divider="__________________________________________________";
		System.out.println(divider);
		System.out.println("Accession="+richsequence.getAccession());
		System.out.println("Description="+richsequence.getDescription());
		System.out.println("Identifier="+richsequence.getIdentifier());
		System.out.println("Name="+richsequence.getName());
		System.out.println("Version="+richsequence.getVersion());
		System.out.println("SeqVersion="+richsequence.getSeqVersion());
		System.out.println("URN="+richsequence.getURN());
		System.out.println("Namespace="+richsequence.getNamespace());
		System.out.println("Annotation="+richsequence.getAnnotation());
		//NCBITaxon taxon=richsequence.getTaxon();
		//display(taxon);	
		
		/*
		System.out.println(divider);
		System.out.println("Features");
		//System.out.println(richsequence.toString()+" has "+richsequence.countFeatures()+" features");
		for(Iterator i = richsequence.features(); i.hasNext(); )
		{
			RichFeature richfeature=(RichFeature)i.next();
			System.out.println("___Feature________________________________________");
			display(richfeature);
		}
		*/
		
		System.out.println(divider);
		System.out.println("Annotations");		
		RichAnnotation annotation = (RichAnnotation)richsequence.getAnnotation();
		for (Iterator i = annotation.keys().iterator(); i.hasNext(); )
		{
			Object key=i.next();
			String value=(String)annotation.getProperty(key);
			System.out.println(key+"="+value);
		}
		
		System.out.println(divider);
		System.out.println("Notes");
		Iterator notesIterator = annotation.getNoteSet().iterator();
		while (notesIterator.hasNext())
		{
			Note note = (Note)notesIterator.next();
			// biojavax:udat = submission date
			System.out.println("Note: rank="+note.getRank()+", term="+note.getTerm()+", value="+note.getValue());
			//System.out.println(note);
		}
		
		System.out.println(divider);
		System.out.println("References");
		for (Object ref : richsequence.getRankedDocRefs())
		{
			System.out.println("Ref: "+ref);
		}
		
		System.out.println(divider);
		System.out.println("Sequence");
		//System.out.println(extractSequenceAsFasta(richsequence));
	}
	
	public static void display(NCBITaxon taxon)
	{
		System.out.println("__________________________________________________");
		System.out.println("Taxon");
		System.out.println("Display name="+taxon.getDisplayName());
		System.out.println("Name hierarchy="+taxon.getNameHierarchy());
		System.out.println("NCBITaxID="+taxon.getNCBITaxID());
		System.out.println("NodeRank="+taxon.getNodeRank());
		System.out.println("GeneticCode="+taxon.getGeneticCode());
		System.out.println("LeftValue="+taxon.getLeftValue());
		System.out.println("MitoGeneticCode="+taxon.getMitoGeneticCode());
		System.out.println("NameClasses="+taxon.getNameClasses());
		System.out.println("ParentNCBITaxID="+taxon.getParentNCBITaxID());
		System.out.println("RightValue="+taxon.getRightValue());
	}
}