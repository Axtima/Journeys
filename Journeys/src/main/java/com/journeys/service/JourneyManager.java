package com.journeys.service;

import java.util.List;

import com.journeys.entity.Journey;

public interface JourneyManager {

    public Journey getJourneyById(Integer journeyId);
    public List<Journey> getAllJourneys();
    
	public void addJourney(Journey journey) throws ServiceException;
    public void editJourney(Journey journey) throws ServiceException;
    public void incrementCountView(Journey journey);
    public void deleteJourney(Integer journeyId);
    
}
