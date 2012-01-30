package edu.hiro.hcv.users;


import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Lists;

import edu.hiro.util.RandomHelper;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
public class User implements UserDetails 
{   
    protected String username;
    protected String password;
    protected Boolean enabled;
	protected Boolean administrator=false;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String affiliation;
	protected Date created;
	protected Date updated;

    public User()
    {
    }
    
    public User(String username)
    {
        this.username = username;
        this.created=new Date();
		this.updated=new Date();
    }   

    //@Transient
    public String getName() {
        String name = this.firstname + " " + this.lastname;
        return name.trim();
    }

    private String createSalt() {
        String salt = String.valueOf(RandomHelper.randomInteger(1000000));
        System.out.println("salt=" + salt);
        return salt;
    }

    //@PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
        System.out.println("onUpdate called: " + this.updated.toString());
    }
    
    public boolean isEnabled(){return this.enabled;}
	
   	//@Transient
   	public boolean isAccountNonExpired()
   	{
   		return true;
   	}
   	
   	//@Transient
   	public boolean isAccountNonLocked()
   	{
   		return true;
   	}
   	
   	//@Transient
   	public boolean isCredentialsNonExpired()
   	{
   		return true;
   	}

   	//@Transient
   	public List<String> getRoles()
   	{
   		List<String> roles=Lists.newArrayList("ROLE_USER","ROLE_LOGIN_USER");
   		if (this.administrator)
   			roles.add("ROLE_ADMIN");
   		return roles;
   	}
   	
   	//@Transient
   	public Collection<GrantedAuthority> getAuthorities()
   	{
   		LoginService loginService=new LoginServiceImpl();
   		return loginService.getAuthorities(getRoles());
   	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
}
