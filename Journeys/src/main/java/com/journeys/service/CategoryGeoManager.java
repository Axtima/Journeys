package com.journeys.service;

import java.util.List;

import com.journeys.entity.CategoryGeo;

public interface CategoryGeoManager {
	public void addCategoryGeo(CategoryGeo categoryGeo);
	public CategoryGeo getCategoryGeoById(Integer categoryGeoId);
    public List<CategoryGeo> getAllCategoryGeos();
    public void editCategoryGeo(CategoryGeo categoryGeo);
    public void deleteCategoryGeo(Integer categoryGeoId);
}
