// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.hiro.hcv.hcv;

import java.lang.String;

privileged aspect HcvGenome_Roo_ToString {
    
    public String HcvGenome.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accession: ").append(getAccession()).append(", ");
        sb.append("Cds: ").append(getCds()).append(", ");
        sb.append("Refs: ").append(getRefs() == null ? "null" : getRefs().size()).append(", ");
        sb.append("Sequence: ").append(getSequence());
        return sb.toString();
    }
    
}
