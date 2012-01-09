package edu.hiro.hcv.hcv;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.common.collect.Lists;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@Document
public class HcvGenome 
{
	@Id protected String accession="";
	protected String sequence="";

	protected List<String> refs=Lists.newArrayList();
	
	protected CDS cds=new CDS();
	
	public class CDS
	{
        protected int start;
		protected int end;
		protected String product;
		protected String protein_id;
		protected Integer gi;
		
		protected List<MaturePeptide> matpeptides=Lists.newArrayList();
	}
	
	public class MaturePeptide
	{
		protected int start;
		protected int end;
		protected String product;
	}
}
