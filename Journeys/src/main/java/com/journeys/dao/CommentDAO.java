package com.journeys.dao;

import java.util.List;

import com.journeys.entity.Comment;

public interface CommentDAO 
{
    public void addComment(Comment comment);
    public Comment getCommentById(Integer commentId);
    public List<Comment> getAllComments();
    public List<Comment> getCommentsByDayId(Integer dayId);
    public List<Comment> getCommentsByJourneyId(Integer journeyId);
    public void editComment(Comment comment);
    public void deleteComment(Integer commentId);
}