package com.journeys.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.journeys.util.Validator;

@Entity
@Table(name="USERS")
public class User {
     
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
     
    @Column(name="EMAIL")
    private String email;
 
    @Column(name="FIRST_NAME")
    private String firstName;
    
    @Column(name="LAST_NAME")
    private String lastName;
    
    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="ENABLED")
    private Boolean enabled;

    @Column(name="VALIDATION_KEY")
    private String validationKey;
    
    @Column(name="CREATION_DATE")
    private Date creationDate;
    
    @Column(name="LAST_MODIFIED_DATE")
    private Date lastModificationDate;
    
    @Column(name="LAST_CONNECTION_DATE")
    private Date lastConnectionDate;
    
    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("startDate")
    private Set<Journey> journeys;

    @OneToMany(mappedBy="user")
    @OrderBy("date")
    private Set<Comment> comments;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")}
    )
    private Set<Role> roles;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SUBSCRIPTION",
     joinColumns=@JoinColumn(name="SUBSCRIBED_ID"),
     inverseJoinColumns=@JoinColumn(name="FOLLOWED_ID")
    )
    private Set<User> followedUsers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="SUBSCRIPTION",
     joinColumns=@JoinColumn(name="FOLLOWED_ID"),
     inverseJoinColumns=@JoinColumn(name="SUBSCRIBED_ID")
    )
    private Set<User> subscribedUsers;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    
    public String getValidationKey() {
		return validationKey;
	}

	public void setValidationKey(String validationKey) {
		this.validationKey = validationKey;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }
    
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    

	public Set<User> getSubscribedUsers() {
		return subscribedUsers;
	}

	public void setSubscribedUsers(Set<User> subscribedUsers) {
		this.subscribedUsers = subscribedUsers;
	}

	public Set<User> getFollowedUsers() {
		return followedUsers;
	}

	public void setFollowedUsers(Set<User> followedUsers) {
		this.followedUsers = followedUsers;
	}
	
    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }
    
    public String getFullName() {
        String result = this.lastName;
        if (Validator.isNotNull(this.firstName)) {
            result = this.firstName + " " + result;
        }
        return result;
    }
    
    public boolean hasRole(String roleName) {
        
        boolean result = false;
        for(Role role:roles) {
            if (role.getName().equals(roleName)) {
                result = true;
            }
        }
        return result;
    }  

}