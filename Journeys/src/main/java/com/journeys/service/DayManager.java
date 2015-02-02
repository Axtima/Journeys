package com.journeys.service;

import java.util.Date;
import java.util.List;

import com.journeys.entity.Day;

public interface DayManager {

	public void addDay(Day day) throws ServiceException;
    public void editDay(Day day) throws ServiceException;

	public Day getDayById(Integer dayId);
    public List<Day> getAllDays();
    public Day getPreviousDay(Integer journeyId, Date date);
    public Day getNextDay(Integer journeyId, Date date);
    public void deleteDay(Integer dayId);
}
