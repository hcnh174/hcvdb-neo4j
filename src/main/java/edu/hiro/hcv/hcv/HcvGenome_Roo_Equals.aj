// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.hiro.hcv.hcv;

import edu.hiro.hcv.hcv.HcvGenome;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

privileged aspect HcvGenome_Roo_Equals {
    
    public boolean HcvGenome.equals(Object obj) {
        if (!(obj instanceof HcvGenome)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        HcvGenome rhs = (HcvGenome) obj;
        return new EqualsBuilder().append(accession, rhs.accession).append(cds, rhs.cds).append(sequence, rhs.sequence).isEquals();
    }
    
    public int HcvGenome.hashCode() {
        return new HashCodeBuilder().append(accession).append(cds).append(sequence).toHashCode();
    }
    
}
