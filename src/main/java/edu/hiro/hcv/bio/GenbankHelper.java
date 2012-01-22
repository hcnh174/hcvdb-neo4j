package edu.hiro.hcv.bio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.hiro.hcv.util.HttpHelper;
import edu.hiro.hcv.util.StringHelper;

public class GenbankHelper
{
	public enum EntrezDatabase{taxonomy,protein,nucleotide,pubmed,pmc,genome,gene,genomeprj;}
	
	public static final int DELAY=5000;
	public static final int BATCHSIZE=500;
	public static final String EFETCH_URL="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi";
	public static final String GENBANK_SUFFIX=".gbk";
	public static final String GENPEPT_SUFFIX=".gpt";
	
	//Taxonomy database example //e.g. 9685
	public static String downloadTaxa(List<Integer> taxids)
    {
		String id=StringHelper.join(taxids,",");
		String url=EFETCH_URL;
		Map<String,Object> model=new LinkedHashMap<String,Object>();
    	model.put("db",EntrezDatabase.taxonomy.name());
    	model.put("mode","xml");
    	model.put("report","brief");
    	model.put("id",id);
		String xml=HttpHelper.postRequest(url,model);
		return xml;
	}
	
	public static String downloadRefs(List<Integer> ids)
	{
		String id=StringHelper.join(ids,",");
		String url=EFETCH_URL;
		Map<String,Object> model=new LinkedHashMap<String,Object>();
		model.put("db",EntrezDatabase.pubmed.name());
		model.put("mode","xml");
		model.put("id",id);
		String xml=HttpHelper.postRequest(url,model);
		return xml;
	}
	
	
	///////////////////////////////////////////////////////////
	
	/*
	public Collection<Taxon> getTaxa(List<Integer> ids, MessageWriter writer)
	{
		writer.write("Updating taxonomies...");
		//Map<Integer,Taxon> lookup=new HashMap<Integer,Taxon>();
		int numids=ids.size();
		int numbatches=MathHelper.getNumbatches(numids,BATCHSIZE);
		BeanHelper beanhelper=new BeanHelper();
		for (int batchnumber=0;batchnumber<numbatches;batchnumber++)
		{
			int fromIndex=batchnumber*BATCHSIZE;
			int toIndex=fromIndex+BATCHSIZE;
			if (toIndex>=numids)
				toIndex=numids;//toIndex=numids-1;
			System.out.println("batch load ids - from "+fromIndex+" to "+toIndex);
			// look up all the unknown taxa and their ancestors
			List<Integer> sublist=ids.subList(fromIndex,toIndex);
			List<Taxon> taxa=downloadTaxa(sublist);
			for (Taxon bean : taxa)
			{
				Taxon taxon=lookup.get(bean.getId());
				if (taxon==null)
				{
					Taxon tax=new Taxon();					
					beanhelper.copyProperties(tax,bean);
					lookup.put(bean.getId(),tax);
				}
				else beanhelper.copyProperties(taxon,bean);
			}
			if (batchnumber<numbatches-1)
				ThreadHelper.sleep(DELAY);
		}	

		List<Taxon> rootTaxa=new ArrayList<Taxon>();
		
		// assemble parents
		for (Taxon taxon : lookup.values())
		{
			Integer parent_id=taxon.getParent_id();
			if (parent_id==null)
			{
				rootTaxa.add(taxon);
				continue;
			}
			Taxon parent=(Taxon)lookup.get(parent_id);
			if (parent==null)
				continue;
			parent.add((Taxon)taxon);
		}
		for (Taxon taxon : rootTaxa)
		{
			taxon.index();
		}
		
		return rootTaxa;
	}
	*/

	//////////////////////////////////////////////////////////
	
	/*
	// example "11748933,11700088" 
	public List<Ref> downloadRefs(List<Integer> ids)
	{
		String id=StringHelper.join(ids,",");
		String url=EFETCH_URL;
		Map<String,Object> model=new LinkedHashMap<String,Object>();
		model.put("db",GenbankService.EntrezDatabase.pubmed.name());
		model.put("mode","xml");
		model.put("id",id);
		String xml=HttpHelper.postRequest(url,model);
		return RefParser.parseRefs(xml);
	}
	
	public Map<Integer,Ref> getRefs(List<Integer> ids, MessageWriter writer)
	{
		Map<Integer,Ref> map=new HashMap<Integer,Ref>();		
		int numids=ids.size();
		int numbatches=MathHelper.getNumbatches(numids,BATCHSIZE);		
		for (int batchnumber=0;batchnumber<numbatches;batchnumber++)
		{
			int fromIndex=batchnumber*BATCHSIZE;
			int toIndex=fromIndex+BATCHSIZE;
			if (toIndex>=numids)
				toIndex=numids;//toIndex=numids-1;
			System.out.println("batch load ids - from "+fromIndex+" to "+toIndex);
			List<Integer> sublist=ids.subList(fromIndex,toIndex);
			List<Ref> refs=downloadRefs(sublist);
			for (Ref ref : refs)
			{
				map.put(ref.getPmid(),ref);
			}
			if (batchnumber<numbatches-1)
				ThreadHelper.sleep(DELAY);
		}
		return map;
	}
	*/
}