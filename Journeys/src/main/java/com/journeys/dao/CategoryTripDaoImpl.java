package com.journeys.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.CategoryTrip;

@Repository
public class CategoryTripDaoImpl implements CategoryTripDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addCategoryTrip(CategoryTrip categoryTrip) {
		this.sessionFactory.getCurrentSession().save(categoryTrip);
	}

    public CategoryTrip getCategoryTripById(Integer categoryTripId) {
        return (CategoryTrip)this.sessionFactory.getCurrentSession().createQuery("from CategoryTrip where id = :id").setInteger("id", categoryTripId).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<CategoryTrip> getAllCategoryTrips() {
		return this.sessionFactory.getCurrentSession().createQuery("from CategoryTrip order by NAME_FR asc").list();
	}
    
    public void editCategoryTrip(CategoryTrip categoryTrip) {
        this.sessionFactory.getCurrentSession().update(categoryTrip);
    }
    
	public void deleteCategoryTrip(Integer categoryTripId) {
	    CategoryTrip categoryTrip = getCategoryTripById(categoryTripId);
        if (null != categoryTrip) {
        	this.sessionFactory.getCurrentSession().delete(categoryTrip);
        }
	}
	
	

}
