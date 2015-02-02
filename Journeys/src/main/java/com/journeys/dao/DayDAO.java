package com.journeys.dao;

import java.util.Date;
import java.util.List;

import com.journeys.entity.Day;

public interface DayDAO 
{
    public void addDay(Day day);
    public Day getDayById(Integer dayId);
    public List<Day> getAllDays();
    public Day getPreviousDay(Integer journeyId, Date date);
    public Day getNextDay(Integer journeyId, Date date);
    public void editDay(Day day);
    public void deleteDay(Integer dayId);
}