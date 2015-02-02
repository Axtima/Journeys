package com.journeys.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.Comment;

@Repository
public class CommentDaoImpl implements CommentDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addComment(Comment comment) {
		this.sessionFactory.getCurrentSession().save(comment);
	}

    public Comment getCommentById(Integer commentId) {
        return (Comment)this.sessionFactory.getCurrentSession().createQuery("from Comment where id = :id").setInteger("id", commentId).uniqueResult();
    }
	
	@SuppressWarnings("unchecked")
	public List<Comment> getAllComments() {
		return this.sessionFactory.getCurrentSession().createQuery("from Comment").list();
	}

    public List<Comment> getCommentsByDayId(Integer dayId) {

    	List<Comment> comments = (List<Comment>)this.sessionFactory.getCurrentSession().createQuery(
                "select comment from Comment as comment inner join comment.day as day where day.id = :dayId order by comment.date desc").setInteger(
                        "dayId", dayId).list();
        return comments;
    }
    
    public List<Comment> getCommentsByJourneyId(Integer journeyId) {

    	List<Comment> comments = (List<Comment>)this.sessionFactory.getCurrentSession().createQuery(
                "select comment from Comment as comment inner join comment.journey as journey where journey.id = :journeyId order by comment.date desc").setInteger(
                        "journeyId", journeyId).list();
        return comments;
    }
    
    public void editComment(Comment comment) {
        this.sessionFactory.getCurrentSession().update(comment);
    }
    
	public void deleteComment(Integer commentId) {
	    Comment comment = getCommentById(commentId);
        if (null != comment) {
        	this.sessionFactory.getCurrentSession().delete(comment);
        }
	}
	
	

}
