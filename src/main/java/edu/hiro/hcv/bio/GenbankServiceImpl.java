package edu.hiro.hcv.bio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Required;

import edu.hiro.hcv.util.CException;
import edu.hiro.hcv.util.FileHelper;
import edu.hiro.hcv.util.HttpHelper;
import edu.hiro.hcv.util.MathHelper;
import edu.hiro.hcv.util.MessageWriter;
import edu.hiro.hcv.util.StringHelper;
import edu.hiro.hcv.util.ThreadHelper;

public class GenbankServiceImpl implements GenbankService
{

	private static final int DELAY=5000;
	private static final int BATCHSIZE=500;
	private static final String EFETCH_URL="http://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi";
	private String genbankcache="d:/temp/genbankcache/";

	public String getGenbankcache(){return this.genbankcache;}
	@Required public void setGenbankcache(final String genbankcache){this.genbankcache=genbankcache;}

	public void downloadGenbankEntries(Collection<String> idlist, GenbankService.EntrezDatabase database, 
			String filename, int batchsize, MessageWriter writer)
	{
		ensureCacheExists();
		List<String> ids=StringHelper.trim(idlist);
		if (filename==null)
			ids=getUncachedIds(ids);
		System.out.println("idlist="+StringHelper.join(ids,","));
		if (ids.isEmpty())
			return;
		// do in batches
		for (List<String> batch : MathHelper.getBatches(ids,batchsize))
		{
			ThreadHelper.sleep(DELAY);
			downloadGenbankEntry(StringHelper.join(batch,","),database,filename,writer);
		}
		if (filename==null)
		{
			List<String> notfound=verifyCached(ids,false);
			writer.write("Could not find the following accessions: "+StringHelper.join(notfound,","));
		}
	}
	
	public void downloadGenbankEntry(String id, GenbankService.EntrezDatabase database, String filename, MessageWriter writer)
	{
		writer.message("downloading: "+id);
		downloadGenbankEntry(id,database,filename);
	}

	public void downloadGenbankEntry(String id, int from, int to, GenbankService.EntrezDatabase database, String filename,
		MessageWriter writer)
	{
		writer.message("downloading: "+id);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("from",from);
		params.put("to",to);
		downloadGenbankEntry(id,database,params,filename);
	}
	
	private void downloadGenbankEntry(String id, GenbankService.EntrezDatabase database, String filename)
	{
		Map<String,Object> params=new HashMap<String,Object>();
		downloadGenbankEntry(id,database,params,filename);
	}

	private void downloadGenbankEntry(String id, GenbankService.EntrezDatabase database, Map<String,Object> params, String filename)
	{
		if (StringHelper.isEmpty(id))
			return;
		params.put("db",database.name());
		params.put("rettype","gbwithparts");
		params.put("id",id);
		String str=HttpHelper.getRequest(EFETCH_URL,params);
		LargeGenbankFileReader.splitString(str,this.genbankcache);
		if (filename!=null)
			FileHelper.appendFile(filename,str);		
	}
	
	///////////////////////////////////////////////////
	
	private List<String> verifyCached(Collection<String> ids, boolean throwException)
	{
		List<String> notfound=getUncachedIds(ids);
		if (!notfound.isEmpty() && throwException)
			throw new CException("could not download ids:\n"+StringHelper.join(notfound,"\n"));
		else return notfound;
	}
	
	private List<String> getUncachedIds(Collection<String> ids)
	{
		List<String> list=new ArrayList<String>();
		for (String id : ids)
		{
			if (!isCached(id))
				list.add(id);
		}
		return list;
	}
	
	private boolean isCached(String id)
	{
		return FileHelper.exists(this.genbankcache+id+GENBANK_SUFFIX);
	}
	
	private void ensureCacheExists()
	{
		if (!FileHelper.exists(this.genbankcache))
			FileHelper.createDirectory(this.genbankcache);
	}
	
	////////////////////////////////////////////////////////
	
	/*
	//Taxonomy database example //e.g. 9685
	public String downloadTaxa(List<Integer> taxids)
    {
		String id=StringHelper.join(taxids,",");
		String url=EFETCH_URL;
		Map<String,Object> model=new LinkedHashMap<String,Object>();
    	model.put("db",GenbankService.EntrezDatabase.taxonomy.name());
    	model.put("mode","xml");
    	model.put("report","brief");
    	model.put("id",id);
		String xml=HttpHelper.postRequest(url,model);
		return xml;
		//return TaxonParser.parseTaxa(xml);
	}
	
	///////////////////////////////////////////////////////////
	
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