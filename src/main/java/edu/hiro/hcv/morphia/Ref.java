package edu.hiro.hcv.morphia;

import java.util.List;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import edu.hiro.hcv.util.MathHelper;
import edu.hiro.hcv.util.StringHelper;

@RooJavaBean
@RooToString
@RooEquals
@Entity("refs")
public class Ref
{
	public enum ReferenceType{JOURNAL,BOOK,CHAPTER};
	
	@Id protected Integer id;
	protected ReferenceType type=ReferenceType.JOURNAL;
	protected String authors="";
	protected String year="";
	protected String title="";
	protected String journal="";
	protected String volume="";
	protected String pages="";
	protected String publisher="";
	protected String city="";
	protected String abstrct="";

	public Ref() {}
	
	public Ref(int id)
	{
		this.id=id;
	}
//
//	public String createAbbreviation()
//	{
//		if (StringHelper.isEmpty(this.authors))
//			return this.id.toString();
//		List<String> authors=StringHelper.splitAsList(this.authors,",");
//		String firstauthor=authors.get(0);
//		if (!StringHelper.isEmpty(firstauthor) && firstauthor.indexOf(' ')!=-1)
//			firstauthor=firstauthor.substring(0,firstauthor.indexOf(' '));
//		return firstauthor+" et al., "+this.year;
//	}

	public String getCitation()
	{
		return getCitation(this.pages);
	}
	
	public String getCitation(String pages)
	{
		if (StringHelper.isEmpty(this.title))
			return "";
		StringBuilder buffer=new StringBuilder();
		if (this.type==ReferenceType.JOURNAL)
		{
			buffer.append(this.authors).append(". ").append(this.year).append(". ").append(this.title).append(" ");
			buffer.append(this.journal).append(" ").append(this.volume).append(":").append(this.pages).append(".");
		}
		else if (this.type==ReferenceType.BOOK)
		{
			buffer.append(this.authors).append(". ").append(this.year).append(". ").append(this.title).append(". ");
			buffer.append(this.publisher).append(", ").append(this.city).append(".");
			if (StringHelper.hasContent(pages))
				 buffer.append(" (pp. "+pages+").");
		}
		else if (this.type==ReferenceType.CHAPTER)
		{
			buffer.append(this.authors).append(". ").append(this.year).append(". ").append(this.title).append(". ");
			buffer.append(this.publisher).append(", ").append(this.city).append(". ").append(this.pages).append(".");
		}
		return buffer.toString();
	}
	
	/*
	public void dump()
	{
		System.out.println("ID: "+this.pmid);
        System.out.println("Journal: "+this.journal);
    	System.out.println("Volume: "+this.volume);
    	System.out.println("Pages: "+this.pages);
    	System.out.println("Year: "+this.year);
    	System.out.println("Title: "+this.title);
    	System.out.println("Authors: "+this.authors);
        System.out.println("Abstract: "+this.abstrct);
        System.out.println("--------------------------\n");
	}


	public String getHtml()
	{
		StringBuilder buffer=new StringBuilder();
		buffer.append("(<a href=\"javascript:void(0)\" onclick=\"vardb.VarDB.refPopup('").append(this.identifier).append("')\">");
		buffer.append(this.name);
		buffer.append("</a>)");
		return buffer.toString();
	}
	*/
}