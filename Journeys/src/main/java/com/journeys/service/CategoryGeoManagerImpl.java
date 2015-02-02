package com.journeys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.CategoryGeoDAO;
import com.journeys.entity.CategoryGeo;

@Service
public class CategoryGeoManagerImpl implements CategoryGeoManager {
	
	@Autowired
    private CategoryGeoDAO categoryGeoDAO;

	@Transactional
	public void addCategoryGeo(CategoryGeo categoryGeo) {
		categoryGeoDAO.addCategoryGeo(categoryGeo);
	}
	
    @Transactional
    public CategoryGeo getCategoryGeoById(Integer categoryGeoId) {
        return categoryGeoDAO.getCategoryGeoById(categoryGeoId);
    }

	@Transactional
	public List<CategoryGeo> getAllCategoryGeos() {
		return categoryGeoDAO.getAllCategoryGeos();
	}
    
    @Transactional
    public void editCategoryGeo(CategoryGeo categoryGeo) {
        categoryGeoDAO.editCategoryGeo(categoryGeo);
    }
    
	@Transactional
	public void deleteCategoryGeo(Integer categoryGeoId) {
		categoryGeoDAO.deleteCategoryGeo(categoryGeoId);
	}

	public void setCategoryGeoDAO(CategoryGeoDAO categoryGeoDAO) {
		this.categoryGeoDAO = categoryGeoDAO;
	}
}
