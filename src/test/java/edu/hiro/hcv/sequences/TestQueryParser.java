package edu.hiro.hcv.sequences;

import org.junit.Test;
import edu.hiro.util.CFilter.Filter;

public class TestQueryParser
{	
	@Test
	public void testParse() throws Exception
	{
		String str1="family=\"var\" AND (pathogen=\"Plasmodium falciparum\" OR pathogen=\"plasmodium.falciparum\")";
		//String str2="family=var AND (pathogen=\"Plasmodium falciparum\" OR pathogen=\"plasmodium.falciparum\")";
		//String str2="(pathogen=\"Plasmodium falciparum\" OR pathogen=\"plasmodium.falciparum\") AND family=var";
		
		Filter filter;
		filter=HcvQueryParser.parse(str1);
		System.out.println("filter1="+filter.getText());
		
		//filter=HcvQueryParser.parse(str2);
		//System.out.println("filter2="+filter.getText());
	}
}