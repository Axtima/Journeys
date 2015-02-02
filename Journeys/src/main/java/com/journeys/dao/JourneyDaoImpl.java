package com.journeys.dao;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.Journey;
import com.journeys.util.IndexerUtil;

@Repository
public class JourneyDaoImpl implements JourneyDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addJourney(Journey journey) {
		this.sessionFactory.getCurrentSession().save(journey);
		try {
			IndexerUtil.reindex(journey);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public Journey getJourneyById(Integer journeyId) {
        return (Journey)this.sessionFactory.getCurrentSession().createQuery("from Journey where id = :id").setInteger("id", journeyId).uniqueResult();
    }
    
	@SuppressWarnings("unchecked")
	public List<Journey> getAllJourneys() {
		return this.sessionFactory.getCurrentSession().createQuery("from Journey order by startDate").list();
	}

    public void editJourney(Journey journey) {
        this.sessionFactory.getCurrentSession().update(journey);
        try {
			IndexerUtil.reindex(journey);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	   
	public void deleteJourney(Integer journeyId) {
		Journey journey = getJourneyById(journeyId);
        if (null != journey) {
        	this.sessionFactory.getCurrentSession().delete(journey);
        	IndexerUtil.deleteIndex(journey.getId());
        }
	}
	
	

}
