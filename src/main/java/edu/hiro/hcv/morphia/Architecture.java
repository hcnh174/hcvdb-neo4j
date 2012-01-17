package edu.hiro.hcv.morphia;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import edu.hiro.hcv.util.StringHelper;

public class Architecture extends Tag
{	
	protected String architecture;
	protected BigInteger numsequences;
		
	public Architecture(){}
	
	public Architecture(String architecture, int numsequences)
	{
		this.architecture=architecture;
		this.numsequences=new BigInteger(String.valueOf(numsequences));
	}
	
	public List<String> getDomains()
	{
		return StringHelper.splitAsList(this.architecture,";");
	}
	
	public static List<String> findUniqueDomains(List<Architecture> architectures)
	{
		List<String> domains=new ArrayList<String>();
		for (Architecture architecture : architectures)
		{
			for (String domain : architecture.getDomains())
			{
				if (!domains.contains(domain))
					domains.add(domain);
			}
		}
		return domains;
	}
	
	public static int findMaxDomains(List<Architecture> architectures)
	{
		int max=0;
		for (Architecture architecture : architectures)
		{
			if (architecture.getDomains().size()>max)
				max=architecture.getDomains().size();
		}
		return max;
	}
}