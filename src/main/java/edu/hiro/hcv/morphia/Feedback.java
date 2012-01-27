package edu.hiro.hcv.morphia;

import java.util.Date;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

import edu.hiro.util.StringHelper;

@RooJavaBean
@RooToString
@RooEquals
@Entity("feedback")
public class Feedback
{
	@Id private Long id;
	protected String name;
	protected String affiliation;
	protected String email;
	protected String purpose;
	protected String comments;
	protected Date date;
	
	public Feedback() {}
	
	public Feedback(String name, String affiliation, String email, String purpose, String comments)
	{
		this.name=name.trim();
		this.affiliation=affiliation.trim();
		this.email=email.trim();
		this.purpose=purpose.trim();
		this.comments=comments.trim();
		this.date=new Date();
		if (StringHelper.isEmpty(this.name))
			this.name="varDB user";
	}
	
	/*
	public Feedback(User user)
	{
		CBeanHelper beanhelper=new CBeanHelper();
		beanhelper.copyProperties(this,user);
		this.purpose="COMMENT";
		this.comments="";
	}
	*/
}