package edu.hiro.hcv.setup;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.hiro.hcv.bio.GenbankHelper;
import edu.hiro.hcv.bio.TaxonomicLevel;
import edu.hiro.hcv.neo4j.TaxonNode;
import edu.hiro.util.Batcher;
import edu.hiro.util.BeanHelper;
import edu.hiro.util.Dom4jHelper;
import edu.hiro.util.FileHelper;

public final class TaxonBuilder
{
	private TaxonBuilder(){}
	
	public static Map<Integer,TaxonNode> getTaxa(Set<Integer> ids)//, MessageWriter writer)
	{
		final List<Integer> idlist=Lists.newArrayList(ids);
		final BeanHelper beanhelper=new BeanHelper();
		final Map<Integer,TaxonNode> taxa=Maps.newHashMap();
		
		Batcher batcher=new Batcher(GenbankHelper.BATCHSIZE, idlist.size(), GenbankHelper.DELAY)
		{
			protected void doBatch(final int fromIndex, final int toIndex, final int batchnumber)
			{
				String xml=GenbankHelper.downloadTaxa(idlist.subList(fromIndex,toIndex));
				parseTaxa(xml,taxa);
			}
		};
		batcher.doInBatches();
//		
//		List<TaxonNode> rootTaxa=new ArrayList<TaxonNode>();
//		// assemble parents
//		for (TaxonNode taxon : lookup.values())
//		{
//			Integer parent_id=taxon.getParent_id();
//			if (parent_id==null)
//			{
//				rootTaxa.add(taxon);
//				continue;
//			}
//			TaxonNode parent=(TaxonNode)lookup.get(parent_id);
//			if (parent==null)
//				continue;
//			//parent.add((TaxonNode)taxon);
//		}
//		for (TaxonNode taxon : rootTaxa)
//		{
//			//taxon.assemble(taxon);
//			System.out.println(taxon.toString());
//		}
		return taxa;
	}
	
	@SuppressWarnings("unchecked")
	public static void parseTaxa(String xml, Map<Integer,TaxonNode> taxa)
	{
		FileHelper.writeFile("D:\\temp\\taxa.xml",xml);
		Document document=Dom4jHelper.parse(xml);
		Element root=document.getRootElement();
		for (Iterator<?> iter=root.selectNodes("/TaxaSet/Taxon").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			parseTaxon(element,taxa);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private static void parseTaxon(Element taxonnode, Map<Integer,TaxonNode> taxa)
	{
		TaxonNode taxon=new TaxonNode(Dom4jHelper.getIntValue(taxonnode,"TaxId"));
		taxon.setName(Dom4jHelper.getValue(taxonnode,"ScientificName"));
		taxon.setLevel(TaxonomicLevel.lookup(Dom4jHelper.getValue(taxonnode,"Rank")));
		taxon.setParent_id(Dom4jHelper.getIntValue(taxonnode,"ParentTaxId"));
		taxa.put(taxon.getId(),taxon);
		
		TaxonNode parent=null;
		for (Iterator<?> iter=taxonnode.selectNodes("LineageEx/Taxon").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			int taxid=Dom4jHelper.getIntValue(element,"TaxId");
			if (!taxa.containsKey(taxid))
			{
				TaxonNode ancestor=new TaxonNode(taxid);
				ancestor.setName(Dom4jHelper.getValue(element,"ScientificName"));
				ancestor.setLevel(TaxonomicLevel.lookup(Dom4jHelper.getValue(element,"Rank")));
				taxa.put(taxid,ancestor);
				if (parent!=null)
					ancestor.setParent_id(parent.getId());
			}
			parent=taxa.get(taxid);
		}
	}
}