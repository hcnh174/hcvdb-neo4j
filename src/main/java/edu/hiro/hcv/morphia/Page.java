package edu.hiro.hcv.morphia;

import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@RooJavaBean
@RooToString
@RooEquals
@Entity("pages")
public class Page {
   
    @Id private String id;
    @NotNull private String title;
    @NotNull private String text;
    
    /*
    private List<Feature> features=new ArrayList<Feature>();
    
    public class Feature
    {
    	
    }
    */
}
