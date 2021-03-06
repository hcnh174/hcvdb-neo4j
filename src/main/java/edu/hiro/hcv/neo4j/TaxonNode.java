package edu.hiro.hcv.neo4j;


import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.common.collect.Sets;

import edu.hiro.hcv.bio.TaxonomicLevel;

/**
 */
@RooJavaBean
@RooToString(excludeFields={"parent","children"})
@NodeEntity
public class TaxonNode
{   
    @GraphId protected Long nodeId;
    @Indexed protected Integer id;
    protected String name="";
	protected TaxonomicLevel level=TaxonomicLevel.UNKNOWN;
	protected Integer parent_id;

	@RelatedTo(type = "LINEAGE")
    protected TaxonNode parent;
    
	@RelatedTo(type = "LINEAGE")
	protected Set<TaxonNode> children=Sets.newHashSet();

    public TaxonNode()
    {
    }
    
    public TaxonNode(Integer id)
    {
    	this.id=id;
    }   
    
    /////////////////////////////////////////////////////////////
    
    public void add(TaxonNode taxon)
    {
    	children.add(taxon);
    }

    public boolean hasChild(TaxonNode taxon)
    {
        return children.contains(taxon);
    }
    
    public boolean equals(Object obj) {
        if (!(obj instanceof TaxonNode)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        TaxonNode rhs = (TaxonNode) obj;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }
    
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }
    
   
}
