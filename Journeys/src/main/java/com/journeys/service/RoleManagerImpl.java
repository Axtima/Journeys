package com.journeys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journeys.dao.RoleDAO;
import com.journeys.entity.Role;

@Service
public class RoleManagerImpl implements RoleManager {
	
	@Autowired
    private RoleDAO roleDAO;

	@Transactional
	public void addRole(Role role) {
		roleDAO.addRole(role);
	}

    @Transactional
    public Role getRoleById(Integer roleId) {
        return roleDAO.getRoleById(roleId);
    }
    
    @Transactional
    public Role getRoleByName(String name) {
    	return roleDAO.getRoleByName(name);
    }
	   
	@Transactional
	public List<Role> getAllRoles() {
		return roleDAO.getAllRoles();
	}

	@Transactional
    public void editRole(Role role) {
        roleDAO.editRole(role);
    }
    
	@Transactional
	public void deleteRole(Integer roleId) {
		roleDAO.deleteRole(roleId);
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		this.roleDAO = roleDAO;
	}
}
