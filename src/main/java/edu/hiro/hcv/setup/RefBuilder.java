package edu.hiro.hcv.setup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import edu.hiro.hcv.morphia.Ref;
import edu.hiro.hcv.util.Dom4jHelper;
import edu.hiro.hcv.util.StringHelper;

public final class RefBuilder
{
	private RefBuilder(){}
	
    public static List<Ref> parseRefs(String xml)
	{
		Document document=Dom4jHelper.parse(xml);
		Element root=document.getRootElement();
		List<Ref> refs=new ArrayList<Ref>();
		for (Iterator<?> iter=root.selectNodes("/PubmedArticleSet/PubmedArticle").iterator();iter.hasNext();)
		{
			Element element=(Element)iter.next();
			Ref ref=parseRef(element);
			refs.add(ref);
		}
		return refs;
	}
	
	private static Ref parseRef(Element refnode)
	{	
		Ref ref=new Ref();
		ref.setPmid(Integer.valueOf(Dom4jHelper.getValue(refnode,"MedlineCitation/PMID")));
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