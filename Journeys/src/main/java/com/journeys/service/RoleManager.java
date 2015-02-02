package com.journeys.service;

import java.util.List;

import com.journeys.entity.Role;

public interface RoleManager {
    
	public void addRole(Role role);
	public Role getRoleById(Integer roleId);
	public Role getRoleByName(String name);
    public List<Role> getAllRoles();
    public void editRole(Role role);
    public void deleteRole(Integer roleId);
    
}
