package com.journeys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.entity.User;
import com.journeys.service.DayManager;
import com.journeys.service.GeneratorManager;
import com.journeys.service.JourneyManager;
import com.journeys.service.UserManager;
import com.journeys.util.IndexerUtil;
import com.journeys.util.SessionUtil;

@Controller
public class HomeController {
	
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private JourneyManager journeyManager;
    
    @Autowired
    private DayManager dayManager;
    
    @Autowired
    private GeneratorManager generatorManager;
	
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(ModelMap map) 
    {
        return "403";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap map) 
    {
        return "login";
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, ModelMap map) 
    {
    	SessionUtil.refreshAuthenticatedUser(request, userManager);
    	
        List<User> users = (List<User>)userManager.getAllUsers();
        
        // TODO Refactoring
        if (users.size() == 0) {
            generatorManager.generate();
            users = (List<User>)userManager.getAllUsers();
        }
        
        map.addAttribute("userList", users);
        map.addAttribute("currentUser", SessionUtil.getAuthenticatedUser(request));

        return "home";
    }
    
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public String homeUser(ModelMap map, @PathVariable("userId") Integer userId) 
	{

        User user = userManager.getUserById(userId);

        // TODO Refactoring
        if (user == null) {
            generatorManager.generate();
            user = userManager.getUserById(userId);
        }
	    
	    if (user != null) {
            map.addAttribute("journeyList", user.getJourneys());
            map.addAttribute("user", user);
	    }
	    
		return "user";
	}
	
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(HttpServletRequest request, ModelMap map) 
    {
        SessionUtil.refreshAuthenticatedUser(request, userManager);
        return "admin";
    }
    
    @RequestMapping(value = "/admin/reindex", method = RequestMethod.GET)
    public String reindexAll(HttpServletRequest request, ModelMap map) throws SolrServerException 
    {
        if (SessionUtil.isAdminUser(request)) {
        	
        	List<User> users = userManager.getAllUsers();
        	List<Journey> journeys = journeyManager.getAllJourneys();
        	List<Day> days = dayManager.getAllDays();
        	
        	IndexerUtil.deleteAllIndex();
        	
        	for (User user:users) {
        		IndexerUtil.reindex(user);
        	}
        	for (Journey journey:journeys) {
        		IndexerUtil.reindex(journey);
        	}
        	for (Day day:days) {
        		IndexerUtil.reindex(day);
        	}
        	
        } else {
        	return "403";
        }
        return "admin";
    }

}
