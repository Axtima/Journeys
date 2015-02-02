package com.journeys.dao;

import java.util.List;
import com.journeys.entity.Journey;

public interface JourneyDAO 
{
    public void addJourney(Journey journey);
    public Journey getJourneyById(Integer journeyId);
    public List<Journey> getAllJourneys();
    public void editJourney(Journey journey);
    public void deleteJourney(Integer journeyId);
}