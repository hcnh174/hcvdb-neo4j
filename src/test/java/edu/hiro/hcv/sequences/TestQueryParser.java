package edu.hiro.hcv.sequences;

import org.junit.Test;
import edu.hiro.hcv.util.CFilter.Filter;

public class TestQueryParser
{	
	@Test
	public void testParse() throws Exception
	{
		String str1="family=\"var\" AND (pathogen=\"Plasmodium falciparum\" OR pathogen=\"plasmodium.falciparum\")";
		String str2="family=var AND (pathogen=\"Plasmodium falciparum\" OR pathogen=\"plasmodium.falciparum\")";
		
		Filter filter=HcvQueryParser.parse(str1);
		System.out.println("filter="+filter.getText());
	}
}