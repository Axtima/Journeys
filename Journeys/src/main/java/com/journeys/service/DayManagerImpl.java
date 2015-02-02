package com.journeys.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.DayDAO;
import com.journeys.entity.Day;
import com.journeys.util.Validator;

@Service
public class DayManagerImpl implements DayManager {
	
	@Autowired
    private DayDAO dayDAO;

	@Transactional
	public void addDay(Day day) throws ServiceException {
		
		validate(day, true);
		dayDAO.addDay(day);
	}
	
    @Transactional
    public Day getDayById(Integer dayId) {
        return dayDAO.getDayById(dayId);
    }

	@Transactional
	public List<Day> getAllDays() {
		return dayDAO.getAllDays();
	}

	@Transactional
    public Day getPreviousDay(Integer journeyId, Date date) {
	    return dayDAO.getPreviousDay(journeyId, date);
	}
	
	@Transactional
    public Day getNextDay(Integer journeyId, Date date) {
	    return dayDAO.getNextDay(journeyId, date);
	}
    
    @Transactional
    public void editDay(Day day) throws ServiceException {
    	
    	validate(day, false);
    	day.setLastModificationDate(Calendar.getInstance().getTime());
        dayDAO.editDay(day);
    }
    
	@Transactional
	public void deleteDay(Integer dayId) {
		dayDAO.deleteDay(dayId);
	}
	
	private boolean validate(Day day, boolean add) throws ServiceException {
		
		if (Validator.isNull(day.getTitle())) {
			throw new ServiceException("Veuillez saisir un titre");
		}
		
		if (Validator.isNotNull(day.getPictureUrl()) && !Validator.isUrl(day.getPictureUrl())) {
			throw new ServiceException("Veuillez saisir une url valide pour l'illustration");
		}
		
		return true;
	}

	public void setDayDAO(DayDAO dayDAO) {
		this.dayDAO = dayDAO;
	}
}
