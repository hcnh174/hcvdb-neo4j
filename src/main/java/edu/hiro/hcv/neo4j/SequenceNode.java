package edu.hiro.hcv.neo4j;


import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.common.collect.Sets;

import edu.hiro.hcv.util.StringHelper;

/**
 */
@RooJavaBean
@RooToString
@NodeEntity
public class SequenceNode
{   
	private static final String COMMENT_DELIMITER="\n";
	private static final String REF_DELIMITER="\n";
	
    @GraphId protected Long nodeId;
    @Indexed protected String accession="";
    protected String sequence="";
	protected Integer length;
	protected Integer gi;
	protected String description="";
	protected Integer taxon_id;
	protected String comments;
	protected String ref_ids;
	//protected Taxon taxon;
	
	@RelatedTo(type="FEATURE") //, direction = Direction.BOTH
	protected Set<FeatureNode> features=Sets.newHashSet();

	//@RelatedTo(type = "TAG") //, direction = Direction.BOTH
	//protected Set<TagNode> tags=Sets.newHashSet();

    public SequenceNode()
    {
    }
    
//    public SequenceNode(String accession)
//    {
//        this.accession = accession;
//    }  
    
    public SequenceNode(String accession, String sequence)
    {
    	this.accession = accession;
        this.sequence = sequence;
        this.length=sequence.length();
    }   
    
    /////////////////////////////////////////////////////////////
    
    public void addFeature(FeatureNode feature)
    {
    	this.features.add(feature);
    }
    
    public void addComment(String comment)
    {
    	List<String> arr=StringHelper.splitAsList(this.comments,COMMENT_DELIMITER);
    	arr.add(comment);
    	this.comments=StringHelper.join(arr,COMMENT_DELIMITER);
    }
    
    public void addRef(Integer ref)
    {
    	List<Integer> arr=StringHelper.splitInts(this.ref_ids,REF_DELIMITER);
    	arr.add(ref);
    	this.ref_ids=StringHelper.join(arr,REF_DELIMITER);
    }
    
    /*
    public void addTag(TagNode tag)
    {
    	tags.add(tag);
    }

    public boolean hasTag(TagNode tag)
    {
        return tags.contains(tag);
    }
    */
}

