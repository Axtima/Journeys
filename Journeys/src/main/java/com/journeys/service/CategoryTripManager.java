package com.journeys.service;

import java.util.List;

import com.journeys.entity.CategoryTrip;

public interface CategoryTripManager {
	public void addCategoryTrip(CategoryTrip categoryTrip);
	public CategoryTrip getCategoryTripById(Integer categoryTripId);
    public List<CategoryTrip> getAllCategoryTrips();
    public void editCategoryTrip(CategoryTrip categoryTrip);
    public void deleteCategoryTrip(Integer categoryTripId);
}
