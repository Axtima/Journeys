package com.journeys.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY_GEO")
public class CategoryGeo {

    public static final int TYPE_COUNTRY = 1;
    public static final int TYPE_AREA = 2;
    public static final int TYPE_CONTINENT = 3;
    public static final int TYPE_WORLD = 4;
    
    @Id
    @Column(name="ID")
    @GeneratedValue
    private Integer id;
    
    @Column(name="NAME_EN")
    private String englishName;

    @Column(name="NAME_FR")
    private String frenchName;
    
    @Column(name="CONTINENT_NAME_EN")
    private String englishContinentName;
    
    @Column(name="CONTINENT_NAME_FR")
    private String frenchContinentName;
    
    @Column(name="TYPE")
    private Integer type;
    
    @OneToMany (mappedBy="categoryGeo")
    private Set<Journey> journeys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getFrenchName() {
        return frenchName;
    }

    public void setFrenchName(String frenchName) {
        this.frenchName = frenchName;
    }

    public String getEnglishContinentName() {
        return englishContinentName;
    }

    public void setEnglishContinentName(String englishContinentName) {
        this.englishContinentName = englishContinentName;
    }

    public String getFrenchContinentName() {
        return frenchContinentName;
    }

    public void setFrenchContinentName(String frenchContinentName) {
        this.frenchContinentName = frenchContinentName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Set<Journey> getJourneys() {
        return journeys;
    }

    public void setJourneys(Set<Journey> journeys) {
        this.journeys = journeys;
    }
    
}