package edu.hiro.hcv.setup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.Element;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import edu.hiro.hcv.bio.GenbankHelper;
import edu.hiro.hcv.neo4j.RefNode;
import edu.hiro.hcv.neo4j.TaxonNode;
import edu.hiro.hcv.util.Batcher;
import edu.hiro.hcv.util.Dom4jHelper;
import edu.hiro.hcv.util.MathHelper;
import edu.hiro.hcv.util.StringHelper;
import edu.hiro.hcv.util.ThreadHelper;

public final class RefBuilder
{
	private RefBuilder(){}
	
//	public static Map<Integer,RefNode> getRefs(Set<Integer> ids)//, MessageWriter writer)
//	{
//		Map<Integer,RefNode> map=Maps.newHashMap();
//		List<Integer> idlist=Lists.newArrayList(ids);
//		int numids=idlist.size();
//		int numbatches=MathHelper.getNumbatches(numids,GenbankHelper.BATCHSIZE);		
//		for (int batchnumber=0;batchnumber<numbatches;batchnumber++)
//		{
//			int fromIndex=batchnumber*GenbankHelper.BATCHSIZE;
//			int toIndex=fromIndex+GenbankHelper.BATCHSIZE;
//			if (toIndex>=numids)
//				toIndex=numids;
//			System.out.println("batch load ids - from "+fromIndex+" to "+toIndex);
//			List<Integer> sublist=idlist.subList(fromIndex,toIndex);
//			String xml=GenbankHelper.downloadRefs(sublist);
//			List<RefNode> refs=parseRefs(xml);
//			for (RefNode ref : refs)
//			{
//				map.put(ref.getId(),ref);
//			}
//			if (batchnumber<numbatches-1)
//				ThreadHelper.sleep(GenbankHelper.DELAY);
//		}
//		return map;
//	}
	
	public static Map<Integer,RefNode> getRefs(Set<Integer> ids)//, MessageWriter writer)
	{
		final Map<Integer,RefNode> refs=Maps.newHashMap();
		final List<Integer> idlist=Lists.newArrayList(ids);
		Batcher batcher=new Batcher(GenbankHelper.BATCHSIZE, idlist.size(), GenbankHelper.DELAY)
		{
			protected void doBatch(final int fromIndex, final int toIndex, final int batchnumber)
			{
				String xml=GenbankHelper.downloadRefs(idlist.subList(fromIndex,toIndex));
				parseRefs(xml,refs);
			}
		};
		batcher.doInBatches();
		return refs;
	}
	
    public static void parseRefs(String xml, Map<Integer,RefNode> refs)
	{
		Document document=Dom4jHelper.parse(xml);
		Element root=document.getRootElement();
		for (Iterator<?> iter=root.selectNodes("/PubmedArticleSet/PubmedArticle").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			RefNode ref=parseRef(element);
			refs.put(ref.getId(),ref);
		}
	}
	
	private static RefNode parseRef(Element refnode)
	{	
		RefNode ref=new RefNode(Dom4jHelper.getIntValue(refnode,"MedlineCitation/PMID"));
		Element article=(Element)refnode.selectSingleNode("MedlineCitation/Article");
		ref.setAuthors(getAuthors(article));
    	ref.setTitle(Dom4jHelper.getValue(article,"ArticleTitle"));
    	ref.setJournal(Dom4jHelper.getValue(article,"Journal/Title"));
    	ref.setVolume(Dom4jHelper.getValue(article,"Journal/JournalIssue/Volume"));
    	ref.setYear(Dom4jHelper.getValue(article,"Journal/JournalIssue/PubDate/Year"));
    	ref.setPages(Dom4jHelper.getValue(article,"Pagination/MedlinePgn"));
    	ref.setAbstrct(Dom4jHelper.getValue(article,"Abstract/AbstractText"));
    	return ref;
	}
	
	private static String getAuthors(Element article)
	{
    	List<String> authors=new ArrayList<String>();
		for (Iterator<?> iter=article.selectNodes("AuthorList/Author").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			String lastname=Dom4jHelper.getValue(element,"LastName");
			String initials=Dom4jHelper.getValue(element,"Initials");
			authors.add(lastname+" "+initials);
		}
		return StringHelper.join(authors,", ").trim();
	}
}