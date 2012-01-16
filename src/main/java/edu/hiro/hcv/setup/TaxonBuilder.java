package edu.hiro.hcv.setup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

import edu.hiro.hcv.morphia.Taxon;
import edu.hiro.hcv.util.Dom4jHelper;

public final class TaxonBuilder
{
	private TaxonBuilder(){}
	
	@SuppressWarnings("unchecked")
	public static List<Taxon> parseTaxa(String xml)
	{
		//CFileHelper.writeFile("D:\\temp\\taxa.xml",xml);
		Document document=Dom4jHelper.parse(xml);
		Element root=document.getRootElement();
		Map<Integer,Taxon> map=new LinkedHashMap<Integer,Taxon>();
		for (Iterator iter=root.selectNodes("/TaxaSet/Taxon").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			parseTaxon(element,map);
		}
		List<Taxon> taxa=new ArrayList<Taxon>();
		taxa.addAll(map.values());
		return taxa;
	}
	
	
	@SuppressWarnings("unchecked")
	private static void parseTaxon(Element taxonnode, Map<Integer,Taxon> taxa)
	{
		Taxon taxon=new Taxon(Dom4jHelper.getValue(taxonnode,"TaxId"));
		taxon.setName(Dom4jHelper.getValue(taxonnode,"ScientificName"));
		taxon.setLevel(Taxon.TaxonomicLevel.lookup(Dom4jHelper.getValue(taxonnode,"Rank")));
		taxon.setParent_id(Integer.parseInt(Dom4jHelper.getValue(taxonnode,"ParentTaxId")));
		taxa.put(taxon.getTaxid(),taxon);
		
		Taxon parent=null;
		for (Iterator iter=taxonnode.selectNodes("LineageEx/Taxon").iterator();iter.hasNext();)
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
					ancestor.setParent_id(parent.getTaxid());
			}
			parent=taxa.get(taxid);
		}
	}
}