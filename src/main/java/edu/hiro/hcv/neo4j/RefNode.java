package edu.hiro.hcv.neo4j;

import java.util.List;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import edu.hiro.hcv.util.StringHelper;

@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public class RefNode
{
	public enum ReferenceType{JOURNAL,BOOK,CHAPTER};
	
	@GraphId protected Long nodeId;
	@Indexed protected Integer id;
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

	public RefNode() {}
	
	public RefNode(Integer id)
	{
		this.id=id;
	}

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
	

	public String createAbbreviation()
	{
		if (StringHelper.isEmpty(this.authors))
			return this.id.toString();
		List<String> authors=StringHelper.splitAsList(this.authors,",");
		String firstauthor=authors.get(0);
		if (!StringHelper.isEmpty(firstauthor) && firstauthor.indexOf(' ')!=-1)
			firstauthor=firstauthor.substring(0,firstauthor.indexOf(' '));
		return firstauthor+" et al., "+this.year;
	}
}