package edu.hiro.hcv.sequences;

import org.antlr.runtime.tree.CommonTree;

import edu.hiro.hcv.util.AntlrHelper;
import edu.hiro.hcv.util.CException;
import edu.hiro.hcv.util.CFilter.CompositeFilter;
import edu.hiro.hcv.util.CFilter.ElementaryFilter;
import edu.hiro.hcv.util.CFilter.FieldOperator;
import edu.hiro.hcv.util.CFilter.Filter;
import edu.hiro.hcv.util.CFilter.LogicalOperator;
import edu.hiro.hcv.util.StringHelper;

public class HcvQueryParser
{	
	public static Filter parse(String str)
	{
		try
		{
			HcvLexer lexer=new HcvLexer(AntlrHelper.getStringStream(str));
			HcvParser parser=new HcvParser(AntlrHelper.getTokenStream(lexer));
			HcvParser.expr_return r=parser.expr();
			CommonTree tree = (CommonTree)r.getTree();		
			return parseTree(tree);
		}
		catch (Exception e)
		{
			throw new CException(e);
		}
	}
		
	private static Filter parseTree(CommonTree tree)
	{
		System.out.println(tree.toStringTree());
		Filter filter=handleNode(tree);
		return filter;
	}
	
	private static Filter handleNode(CommonTree node)
	{
		System.out.println("node text="+node.getText()+" type="+node.getType());
		if (node.getType()==HcvLexer.AND)
			return handleLogicalNode(node,LogicalOperator.AND);
		else if (node.getType()==HcvLexer.OR)
			return handleLogicalNode(node,LogicalOperator.OR);
		else if (node.getType()==HcvLexer.TERM)
			return handleTermNode(node);
		throw new CException("no handler for node type "+node.getType()+" ["+node.getText()+"]");	
	}
	
	private static Filter handleLogicalNode(CommonTree node, LogicalOperator operator)
	{
		System.out.println("handling "+operator+" node, children="+node.getChildCount());
		if (node.getChildCount()!=2)
			throw new CException(operator+" node should have two children");
		CommonTree left=AntlrHelper.getChild(node,0);
		CommonTree right=AntlrHelper.getChild(node,1);
		return new CompositeFilter(handleNode(left),operator,handleNode(right));
	}
	
	private static Filter handleTermNode(CommonTree node)
	{
		System.out.println("handling TERM node, children="+node.getChildCount());
		if (node.getChildCount()!=3)
			throw new CException("TERM node should have three children");
		String field=AntlrHelper.getChildText(node,0);
		FieldOperator operator=FieldOperator.find(AntlrHelper.getChildText(node,1));
		String value=StringHelper.unquote(AntlrHelper.getChildText(node,2));
		return new ElementaryFilter(field,operator,value);
	}
}