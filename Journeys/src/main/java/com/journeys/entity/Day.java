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
@Table(name="DAY")
public class Day {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
    
    @Column(name="DATE")
    private Date date;
    
    @Column(name="TITLE")
    private String title;
    
    @Column(name="PICTURE_URL")
    private String pictureUrl;

    @Column(name="CONTENT", length=10000)
    private String content;
    
    @Column(name="LATITUDE")
    private double latitude;
    
    @Column(name="LONGITUDE")
    private double longitude;
    
    @Column(name="ENABLED")
    private boolean enabled;

    /*
    @Column(name="CREATION_DATE")
    private String creationDate;
    */
    
    @Column(name="LAST_MODIFIED_DATE")
    private Date lastModificationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOURNEY_ID")
    private Journey journey;
    
    @OneToMany (mappedBy="day", cascade = CascadeType.ALL)
    @OrderBy("date")
    private Set<Comment> comments;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastModificationDate() {
		return lastModificationDate;
	}

	public void setLastModificationDate(Date lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}
	
	public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}