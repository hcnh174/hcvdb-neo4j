// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package edu.hiro.hcv.tags;

import java.lang.String;

privileged aspect AbstractTag_Roo_ToString {
    
    public String AbstractTag.toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Description: ").append(getDescription()).append(", ");
        sb.append("Id: ").append(getId()).append(", ");
        sb.append("Name: ").append(getName()).append(", ");
        sb.append("Sequences: ").append(getSequences() == null ? "null" : getSequences().size()).append(", ");
        sb.append("Type: ").append(getType());
        return sb.toString();
    }
    
}
