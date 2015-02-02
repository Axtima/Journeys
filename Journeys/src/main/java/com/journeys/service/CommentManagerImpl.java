package com.journeys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.CommentDAO;
import com.journeys.entity.Comment;

@Service
public class CommentManagerImpl implements CommentManager {
	
	@Autowired
    private CommentDAO commentDAO;

	@Transactional
	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
	}
	
    @Transactional
    public Comment getCommentById(Integer commentId) {
        return commentDAO.getCommentById(commentId);
    }

	@Transactional
	public List<Comment> getAllComments() {
		return commentDAO.getAllComments();
	}

	@Transactional
    public List<Comment> getCommentsByDayId(Integer dayId) {
		return commentDAO.getCommentsByDayId(dayId);
	}
	
	@Transactional
    public List<Comment> getCommentsByJourneyId(Integer journeyId) {
		return commentDAO.getCommentsByJourneyId(journeyId);
	}
    
    @Transactional
    public void editComment(Comment comment) {
        commentDAO.editComment(comment);
    }
    
	@Transactional
	public void deleteComment(Integer commentId) {
		commentDAO.deleteComment(commentId);
	}

	public void setCommentDAO(CommentDAO commentDAO) {
		this.commentDAO = commentDAO;
	}
}
