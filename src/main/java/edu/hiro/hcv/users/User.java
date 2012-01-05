package edu.hiro.hcv.users;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.roo.addon.equals.RooEquals;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.common.collect.Lists;

import edu.hiro.hcv.tags.Tag;
import edu.hiro.hcv.util.MathHelper;

/**
 */
@RooJavaBean
@RooToString
@RooEquals
@NodeEntity
public class User implements UserDetails 
{   
    @GraphId protected Long id;
    @Indexed protected String username;
    protected String password;
    protected Boolean enabled;
	protected Boolean administrator=false;
	protected String firstname;
	protected String lastname;
	protected String email;
	protected String affiliation;
	protected Date created;
	protected Date updated;

    @RelatedTo(type = "OWNS", elementClass = Tag.class, direction = Direction.BOTH)
    protected Set<Tag> tags = new HashSet<Tag>();

    public User()
    {
    }
    
    public User(String username)
    {
        this.username = username;
        this.created=new Date();
		this.updated=new Date();
    }   

    public void addTag( Tag tag )
    {
    	tags.add(tag);
    }

    public boolean hasTag( Tag tag )
    {
        return tags.contains( tag );
    }
    
    @Transient
    public String getName() {
        String name = this.firstname + " " + this.lastname;
        return name.trim();
    }

    private String createSalt() {
        String salt = String.valueOf(MathHelper.randomInteger(1000000));
        System.out.println("salt=" + salt);
        return salt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated = new Date();
        System.out.println("onUpdate called: " + this.updated.toString());
    }
    
    public boolean isEnabled(){return this.enabled;}
	
   	@Transient
   	public boolean isAccountNonExpired()
   	{
   		return true;
   	}
   	
   	@Transient
   	public boolean isAccountNonLocked()
   	{
   		return true;
   	}
   	
   	@Transient
   	public boolean isCredentialsNonExpired()
   	{
   		return true;
   	}

   	@Transient
   	public List<String> getRoles()
   	{
   		List<String> roles=Lists.newArrayList("ROLE_USER","ROLE_LOGIN_USER");
   		if (this.administrator)
   			roles.add("ROLE_ADMIN");
   		return roles;
   	}
   	
   	@Transient
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
