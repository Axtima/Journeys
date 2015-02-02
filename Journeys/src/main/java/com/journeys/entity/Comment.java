package com.journeys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment {

    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
    
    @Column(name="DATE")
    private Date date;
    
    @Column(name="CONTENT", length=5000)
    private String content;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "JOURNEY_ID")
    private Journey journey;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DAY_ID")
    private Day day;
    
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
    
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
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

	public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
    
	public User getUser() {
        return user;
    }
	
    public void setUser(User user) {
        this.user = user;
    }

}