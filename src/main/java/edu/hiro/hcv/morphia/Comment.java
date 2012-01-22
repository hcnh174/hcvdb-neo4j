package edu.hiro.hcv.morphia;

import java.util.Date;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

@RooJavaBean
@RooToString
@RooEquals
@Entity("tags")
public class Comment
{
	@Id private Long id;
	private String username;
	private String type;
	private String identifier;
	private String text;
	private Date date;
	
	public Comment() {}
	
	public Comment(String username, String type, String identifier, String text)
	{
		this.username=username;
		this.type=type;
		this.identifier=identifier;
		this.text=text;
		this.date=new Date();
	}
}