package com.journeys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.CategoryTripDAO;
import com.journeys.entity.CategoryTrip;

@Service
public class CategoryTripManagerImpl implements CategoryTripManager {
	
	@Autowired
    private CategoryTripDAO categoryTripDAO;

	@Transactional
	public void addCategoryTrip(CategoryTrip categoryTrip) {
		categoryTripDAO.addCategoryTrip(categoryTrip);
	}
	
    @Transactional
    public CategoryTrip getCategoryTripById(Integer categoryTripId) {
        return categoryTripDAO.getCategoryTripById(categoryTripId);
    }

	@Transactional
	public List<CategoryTrip> getAllCategoryTrips() {
		return categoryTripDAO.getAllCategoryTrips();
	}
    
    @Transactional
    public void editCategoryTrip(CategoryTrip categoryTrip) {
        categoryTripDAO.editCategoryTrip(categoryTrip);
    }
    
	@Transactional
	public void deleteCategoryTrip(Integer categoryTripId) {
		categoryTripDAO.deleteCategoryTrip(categoryTripId);
	}

	public void setCategoryTripDAO(CategoryTripDAO categoryTripDAO) {
		this.categoryTripDAO = categoryTripDAO;
	}
}
