package com.journeys.dao;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.Day;
import com.journeys.util.IndexerUtil;

@Repository
public class DayDaoImpl implements DayDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addDay(Day day) {
		this.sessionFactory.getCurrentSession().save(day);
		try {
			IndexerUtil.reindex(day);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public Day getDayById(Integer dayId) {
        return (Day)this.sessionFactory.getCurrentSession().createQuery("from Day where id = :id").setInteger("id", dayId).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Day> getAllDays() {
		return this.sessionFactory.getCurrentSession().createQuery("from Day").list();
	}

    public Day getPreviousDay(Integer journeyId, Date date) {

        Day previousDay = (Day)this.sessionFactory.getCurrentSession().createQuery(
                "select day from Day as day inner join day.journey as journey where journey.id = :journeyId and day.date < :date order by day.date desc").setInteger(
                        "journeyId", journeyId).setDate("date",  date).setMaxResults(1).uniqueResult();
        return previousDay;
    }
    
    public Day getNextDay(Integer journeyId, Date date) {

        Day nextDay = (Day)this.sessionFactory.getCurrentSession().createQuery(
                "select day from Day as day inner join day.journey as journey where journey.id = :journeyId and day.date > :date order by day.date asc").setInteger(
                        "journeyId", journeyId).setDate("date",  date).setMaxResults(1).uniqueResult();
        return nextDay;
    }
    
    public void editDay(Day day) {
        this.sessionFactory.getCurrentSession().update(day);
    	try {
			IndexerUtil.reindex(day);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void deleteDay(Integer dayId) {
	    Day day = getDayById(dayId);
        if (null != day) {
        	this.sessionFactory.getCurrentSession().delete(day);
    		IndexerUtil.deleteIndex(day.getId());
        }
	}
	
	

}
