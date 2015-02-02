package com.journeys.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.journeys.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDAO  {

	@Autowired
    private SessionFactory sessionFactory;
	
	public void addRole(Role role) {
		this.sessionFactory.getCurrentSession().save(role);
	}

    public Role getRoleById(Integer roleId) {
        return (Role)this.sessionFactory.getCurrentSession().createQuery("from Role where id = :id").setInteger("id", roleId).uniqueResult();
    }
    
    public Role getRoleByName(String name) {
    	return (Role)this.sessionFactory.getCurrentSession().createQuery("from Role where name = :name").setString("name", name).uniqueResult();
    }
    
	@SuppressWarnings("unchecked")
	public List<Role> getAllRoles() {
		return this.sessionFactory.getCurrentSession().createQuery("from Role").list();
	}

    public void editRole(Role role) {
        this.sessionFactory.getCurrentSession().update(role);
    }
    
	public void deleteRole(Integer roleId) {
	    Role role = getRoleById(roleId);
        if (null != role) {
        	this.sessionFactory.getCurrentSession().delete(role);
        }
	}
	
}
