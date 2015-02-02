package com.journeys.dao;

import java.util.List;
import com.journeys.entity.User;

public interface UserDAO 
{
    
    public void addUser(User user);
    public User getUserById(Integer userId);
    public User getUserByEmail(String email);
    public List<User> getAllUsers();
    public void editUser(User user);
    public void deleteUser(Integer userId);
    
}