package com.journeys.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.CategoryGeo;

@Repository
public class CategoryGeoDaoImpl implements CategoryGeoDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addCategoryGeo(CategoryGeo categoryGeo) {
		this.sessionFactory.getCurrentSession().save(categoryGeo);
	}

    public CategoryGeo getCategoryGeoById(Integer categoryGeoId) {
        return (CategoryGeo)this.sessionFactory.getCurrentSession().createQuery("from CategoryGeo where id = :id").setInteger("id", categoryGeoId).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<CategoryGeo> getAllCategoryGeos() {
		return this.sessionFactory.getCurrentSession().createQuery("from CategoryGeo order by type desc, NAME_FR asc").list();
	}
    
    public void editCategoryGeo(CategoryGeo categoryGeo) {
        this.sessionFactory.getCurrentSession().update(categoryGeo);
    }
    
	public void deleteCategoryGeo(Integer categoryGeoId) {
	    CategoryGeo categoryGeo = getCategoryGeoById(categoryGeoId);
        if (null != categoryGeo) {
        	this.sessionFactory.getCurrentSession().delete(categoryGeo);
        }
	}
	
	

}
