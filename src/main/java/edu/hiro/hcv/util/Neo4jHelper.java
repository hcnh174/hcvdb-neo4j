package edu.hiro.hcv.util;

import java.util.List;

import org.neo4j.helpers.collection.IteratorUtil;

import com.google.common.collect.Lists;

public final class Neo4jHelper
{
	private Neo4jHelper(){}
	
	public static <T> List<T> asList(Iterable<T> iter)
	{
		return Lists.newArrayList(IteratorUtil.asCollection(iter));
	}
}
	