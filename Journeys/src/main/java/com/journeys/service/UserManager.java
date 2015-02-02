package com.journeys.service;

import java.util.List;

import javax.mail.MessagingException;

import com.journeys.entity.User;

public interface UserManager {
    
	public void addUser(User user) throws ServiceException;
    public void editUser(User user) throws ServiceException;
    public void editUser(User user, boolean checkPassword) throws ServiceException;
    public void activateUser(User user);
    public void refreshLastConnectionDate(User user);
    public void subscribeUser(Integer subscribedUserId, Integer followedUserId);
    
	public User getUserById(Integer userId);
	public User getUserByEmail(String email);
    public List<User> getAllUsers();
    
    public void sendValidationLink(User user) throws MessagingException;
    public void deleteUser(Integer userId);
    
}
