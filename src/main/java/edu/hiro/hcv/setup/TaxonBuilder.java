package edu.hiro.hcv.setup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.hiro.hcv.bio.GenbankHelper;
import edu.hiro.hcv.morphia.Taxon;
import edu.hiro.hcv.util.Batcher;
import edu.hiro.hcv.util.BeanHelper;
import edu.hiro.hcv.util.Dom4jHelper;
import edu.hiro.hcv.util.FileHelper;
import edu.hiro.hcv.util.ThreadHelper;

public final class TaxonBuilder
{
	private TaxonBuilder(){}
	
	public static Collection<Taxon> getTaxa(Set<Integer> ids)//, MessageWriter writer)
	{
		final List<Integer> idlist=Lists.newArrayList(ids);
		final BeanHelper beanhelper=new BeanHelper();
		final Map<Integer,Taxon> lookup=Maps.newHashMap();
		
		Batcher batcher=new Batcher(GenbankHelper.BATCHSIZE, idlist.size(),GenbankHelper.DELAY)
		{
			protected void doBatch(final int fromIndex, final int toIndex, final int batchnumber)
			{
				String xml=GenbankHelper.downloadTaxa(idlist.subList(fromIndex,toIndex));
				lookup.putAll(parseTaxa(xml));
			}
		};
		batcher.doInBatches();
		
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
			//taxon.assemble(taxon);
			System.out.println(taxon.toString());
		}
		return lookup.values();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer,Taxon> parseTaxa(String xml)
	{
		FileHelper.writeFile("D:\\temp\\taxa.xml",xml);
		Document document=Dom4jHelper.parse(xml);
		Element root=document.getRootElement();
		Map<Integer,Taxon> map=Maps.newLinkedHashMap();
		for (Iterator<?> iter=root.selectNodes("/TaxaSet/Taxon").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			parseTaxon(element,map);
		}
		//List<Taxon> taxa=new ArrayList<Taxon>();
		//taxa.addAll(map.values());
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	private static void parseTaxon(Element taxonnode, Map<Integer,Taxon> taxa)
	{
		Taxon taxon=new Taxon(Dom4jHelper.getValue(taxonnode,"TaxId"));
		taxon.setName(Dom4jHelper.getValue(taxonnode,"ScientificName"));
		taxon.setLevel(Taxon.TaxonomicLevel.lookup(Dom4jHelper.getValue(taxonnode,"Rank")));
		taxon.setParent_id(Integer.parseInt(Dom4jHelper.getValue(taxonnode,"ParentTaxId")));
		taxa.put(taxon.getId(),taxon);
		
		Taxon parent=null;
		for (Iterator<?> iter=taxonnode.selectNodes("LineageEx/Taxon").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			int taxid=Integer.parseInt(Dom4jHelper.getValue(element,"TaxId"));
			if (!taxa.containsKey(taxid))
			{
				Taxon ancestor=new Taxon(taxid);
				ancestor.setName(Dom4jHelper.getValue(element,"ScientificName"));
				ancestor.setLevel(Taxon.TaxonomicLevel.lookup(Dom4jHelper.getValue(element,"Rank")));
				taxa.put(taxid,ancestor);
				if (parent!=null)
					ancestor.setParent_id(parent.getId());
			}
			parent=taxa.get(taxid);
		}
	}
}