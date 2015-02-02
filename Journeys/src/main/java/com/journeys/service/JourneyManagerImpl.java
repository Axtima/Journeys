package com.journeys.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.DayDAO;
import com.journeys.dao.JourneyDAO;
import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.entity.User;
import com.journeys.util.Validator;

@Service
public class JourneyManagerImpl implements JourneyManager {
	
	@Autowired
    private JourneyDAO journeyDAO;
	
	@Autowired
    private DayDAO dayDAO;

	@Transactional
	public void addJourney(Journey journey) throws ServiceException {

		validate(journey, true);
		journey.setCreationDate(Calendar.getInstance().getTime());
		
		// Encrypt password
        if ((journey.getPassword() != null) && (journey.getPassword() != "")) {
            journey.setPassword(BCrypt.hashpw(journey.getPassword(), BCrypt.gensalt()));
        }
        
		journeyDAO.addJourney(journey);
		generateDaysFromJourney(journey);
	}

	private Set<Day> generateDaysFromJourney(Journey journey) {

		Set<Day> days = new HashSet<Day>();

		int nbDays = getNbDaysFromJourney(journey);
		
		Date startDate = journey.getStartDate();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		
		for(int i=0; i<nbDays; i++) {
			
			Day day = new Day();
			day.setJourney(journey);
			day.setDate(startCal.getTime());
			dayDAO.addDay(day);
			days.add(day);
			
			startCal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return days;
		
	}
	
	private int getNbDaysFromJourney(Journey journey) {
		
		Date startDate = journey.getStartDate();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		
		Date endDate = journey.getEndDate();
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(endDate);
		endCal.add(Calendar.DAY_OF_YEAR, 1);
				
		int i=0;
		
		while(startCal.getTime().before(endCal.getTime())) {
			i++;
			startCal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return i;
		
	}
	
    @Transactional
    public Journey getJourneyById(Integer journeyId) {
        return journeyDAO.getJourneyById(journeyId);
    }
	   
	@Transactional
	public List<Journey> getAllJourneys() {
		return journeyDAO.getAllJourneys();
	}

    @Transactional
    public void editJourney(Journey journey) throws ServiceException {
        
    	validate(journey, false);
    	journey.setLastModificationDate(Calendar.getInstance().getTime());
    	
    	// Encrypt password
        if ((journey.getPassword() != null) && (journey.getPassword() != "")) {
            journey.setPassword(BCrypt.hashpw(journey.getPassword(), BCrypt.gensalt()));
        }
        
        int nbDays = getNbDaysFromJourney(journey);
        Journey oldJourney = journeyDAO.getJourneyById(journey.getId());
        int oldNbDays = getNbDaysFromJourney(oldJourney);
        
		Date startDate = journey.getStartDate();
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(startDate);
		
        if (oldNbDays > nbDays) {
        	throw new ServiceException("Il n'est pas possible de réduire la durée du séjour !");
        }
        
        int nbDaysToCreate = nbDays - oldNbDays;
        
        Set<Day> oldDays = oldJourney.getDays();
        for(Day oldDay:oldDays) {
			
			oldDay.setDate(startCal.getTime());
			dayDAO.editDay(oldDay);
			
			startCal.add(Calendar.DAY_OF_YEAR, 1);
		}
        
        for(int i=0; i<nbDaysToCreate; i++) {
			
			Day day = new Day();
			day.setJourney(oldJourney);
			day.setDate(startCal.getTime());
			dayDAO.addDay(day);
			
			startCal.add(Calendar.DAY_OF_YEAR, 1);
		}
        
        oldJourney.setPassword(journey.getPassword());
        oldJourney.setTitle(journey.getTitle());
        oldJourney.setStartDate(journey.getStartDate());
        oldJourney.setEndDate(journey.getEndDate());
        oldJourney.setPictureUrl(journey.getPictureUrl());
        
        oldJourney.setUser(journey.getUser());
        oldJourney.setCategoryGeo(journey.getCategoryGeo());
        oldJourney.setCategoryTrip(journey.getCategoryTrip());
        oldJourney.setComments(journey.getComments());
        
        journeyDAO.editJourney(oldJourney);
    }
	
    @Transactional
    public void incrementCountView(Journey journey) {
    	
    	Journey oldJourney = journeyDAO.getJourneyById(journey.getId());
    	oldJourney.setCountView(oldJourney.getCountView());
    	journeyDAO.editJourney(oldJourney);
    }
    
	@Transactional
	public void deleteJourney(Integer journeyId) {
		journeyDAO.deleteJourney(journeyId);
	}

	private boolean validate(Journey journey, boolean add) throws ServiceException {
		
		if (Validator.isNull(journey.getTitle())) {
			throw new ServiceException("Veuillez saisir un titre");
		}
		
		if (Validator.isNotNull(journey.getPictureUrl()) && !Validator.isUrl(journey.getPictureUrl())) {
			throw new ServiceException("Veuillez saisir une url valide pour l'illustration");
		}
		
		if (journey.getStartDate() == null) {
			throw new ServiceException("Veuillez saisir une date de début");
		}
		
		if (journey.getEndDate() == null) {
			throw new ServiceException("Veuillez saisir une date de fin");
		}
		
		return true;
	}
	
	public void setJourneyDAO(JourneyDAO journeyDAO) {
		this.journeyDAO = journeyDAO;
	}
}
