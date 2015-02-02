package com.journeys.dao;

import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.User;
import com.journeys.util.IndexerUtil;

@Repository
public class UserDaoImpl implements UserDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addUser(User user) {
		this.sessionFactory.getCurrentSession().save(user);
		try {
			IndexerUtil.reindex(user);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public User getUserById(Integer userId) {
        User user = (User)this.sessionFactory.getCurrentSession().createQuery("from User where id = :id").setInteger("id", userId).uniqueResult();
        return user;
    }
    
    public User getUserByEmail(String email) {
        User user = (User)this.sessionFactory.getCurrentSession().createQuery("from User where email = :email").setString("email", email).uniqueResult();
        return user;
    }
    
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return this.sessionFactory.getCurrentSession().createQuery("from User").list();
	}

    public void editUser(User user) {
        this.sessionFactory.getCurrentSession().update(user);
        try {
			IndexerUtil.reindex(user);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void deleteUser(Integer userId) {
	    User user = getUserById(userId);
        if (null != user) {
        	this.sessionFactory.getCurrentSession().delete(user);
        	IndexerUtil.deleteIndex(user.getId());
        }
	}
	
}
