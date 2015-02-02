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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="JOURNEY")
public class Journey {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
     
    @Column(name="TITLE")
    private String title;
 
    @Column(name="START_DATE")
    private Date startDate;
 
    @Column(name="END_DATE")
    private Date endDate;
     
    @Column(name="DISPLAY_WEEK_END")
    private boolean displayWeekEnd;
    
    @Column(name="PICTURE_URL")
    private String pictureUrl;

    @Column(name="PASSWORD")
    private String password;
    
    @Column(name="CREATION_DATE")
    private Date creationDate;
    
    @Column(name="LAST_MODIFIED_DATE")
    private Date lastModificationDate;
    
    @Column(name="COUNT_VIEW")
    private Integer countView;
    
    @OneToMany (mappedBy="journey", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("date")
    private Set<Day> days;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @OneToMany (mappedBy="journey", cascade = CascadeType.ALL)
    @OrderBy("date")
    private Set<Comment> comments;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_GEO_ID")
    private CategoryGeo categoryGeo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_TRIP_ID")
    private CategoryTrip categoryTrip;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isDisplayWeekEnd() {
        return displayWeekEnd;
    }

    public void setDisplayWeekEnd(boolean displayWeekEnd) {
        this.displayWeekEnd = displayWeekEnd;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
	
    public Integer getCountView() {
		return countView;
	}

	public void setCountView(Integer countView) {
		this.countView = countView;
	}

	public Set<Day> getDays() {
        return days;
    }

    public void setDays(Set<Day> days) {
        this.days = days;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

    public CategoryGeo getCategoryGeo() {
        return categoryGeo;
    }

    public void setCategoryGeo(CategoryGeo categoryGeo) {
        this.categoryGeo = categoryGeo;
    }

    public CategoryTrip getCategoryTrip() {
        return categoryTrip;
    }

    public void setCategoryTrip(CategoryTrip categoryTrip) {
        this.categoryTrip = categoryTrip;
    }
    
}
