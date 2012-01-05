// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.hiro.hcv.tags;

import edu.hiro.hcv.tags.AbstractTag;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect AbstractTag_Roo_Equals {
    
    public boolean AbstractTag.equals(Object obj) {
        if (!(obj instanceof AbstractTag)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        AbstractTag rhs = (AbstractTag) obj;
        return new EqualsBuilder().append(description, rhs.description).append(id, rhs.id).append(name, rhs.name).append(type, rhs.type).isEquals();
    }
    
    public int AbstractTag.hashCode() {
        return new HashCodeBuilder().append(description).append(id).append(name).append(type).toHashCode();
    }
    
}