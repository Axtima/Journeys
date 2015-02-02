package com.journeys.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.journeys.entity.CategoryGeo;
import com.journeys.entity.CategoryTrip;
import com.journeys.entity.Comment;
import com.journeys.entity.Journey;
import com.journeys.entity.User;
import com.journeys.service.CategoryGeoManager;
import com.journeys.service.CategoryTripManager;
import com.journeys.service.CommentManager;
import com.journeys.service.DayManager;
import com.journeys.service.JourneyManager;
import com.journeys.service.ServiceException;
import com.journeys.service.UserManager;
import com.journeys.util.IndexerUtil;
import com.journeys.util.PermissionUtil;
import com.journeys.util.RequestUtil;
import com.journeys.util.SessionUtil;
import com.journeys.util.Validator;

@Controller
public class JourneyController {
	
    @Autowired
    private CategoryGeoManager categoryGeoManager;

    @Autowired
    private CategoryTripManager categoryTripManager;
    
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private JourneyManager journeyManager;
    
    @Autowired
    private DayManager dayManager;

    @Autowired
    private CommentManager commentManager;
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
	@RequestMapping(value = "/journey/{journeyId}", method = RequestMethod.GET)
	public String displayJourney(HttpServletRequest request, HttpServletResponse response, ModelMap map, @PathVariable("journeyId") Integer journeyId) 
	{

	    SessionUtil.refreshAuthenticatedUser(request, userManager);
	    
	    Journey journey = journeyManager.getJourneyById(journeyId);
	    
	    String returnJsp = "password";
	    
	    if (PermissionUtil.hasPermission(request, journey, PermissionUtil.VIEW)) {
	        returnJsp = "journey";
	        SessionUtil.refreshCountViewsCookieOnJourney(request, response, journeyManager, journey);
	    }
	    
	    initMapAttributeFromJourney(journey, map);
        map.addAttribute("currentUser", SessionUtil.getAuthenticatedUser(request));

	    return returnJsp;
	}
	
	@RequestMapping(value = "/journey/{journeyId}", method = RequestMethod.POST)
	public String displayJourneyWithPassword(HttpServletRequest request, HttpServletResponse response, ModelMap map, @PathVariable("journeyId") Integer journeyId, @RequestParam(value = "password", required=false) String password, @RequestParam(value = "valid", required=false) String valid, @RequestParam(value = "cancel", required=false) String cancel) 
	{
	    
	    String returnJsp = "password";

	    Journey journey = journeyManager.getJourneyById(journeyId);
	    
        if ((valid != null) && (valid != "")) {
	        
	        if (BCrypt.checkpw(password, journey.getPassword())) {
	            returnJsp = "journey";
	            SessionUtil.refreshCountViewsCookieOnJourney(request, response, journeyManager, journey);
	            
	            // Add to session
	            Set<Integer> authenticatedJourneys = (Set<Integer>)request.getSession().getAttribute("authenticatedJourneys");
	            if (authenticatedJourneys == null) {
	                authenticatedJourneys = new HashSet<Integer>();
	            }
	            authenticatedJourneys.add(journey.getId());
	            request.getSession().setAttribute("authenticatedJourneys", authenticatedJourneys);
	        } else {
	            RequestUtil.setErrorMessage(map, "Mot de passe erroné");
	        }
	        
	        initMapAttributeFromJourney(journey, map);
	        map.addAttribute("currentUser", SessionUtil.getAuthenticatedUser(request));

        } else {
            returnJsp = "redirect:/app/" + journey.getUser().getId();
        }
        
        return returnJsp;
	 }
	
	/*
    @RequestMapping(value = {"/admin/journey", "/admin/journey/edit"}, method = RequestMethod.GET)
    public String listJourneys(ModelMap map) 
    {
        map.addAttribute("journey", new Journey());
        map.addAttribute("journeyList", journeyManager.getAllJourneys());
        
        return "admin/journeys";
    }
	*/
    /*
	@RequestMapping(value = {"/admin/journey/{journeyId}"}, method = RequestMethod.GET)
	public String listDaysOfJourney(ModelMap map, @PathVariable(value="journeyId") Integer journeyId) 
	{
		Journey journey = journeyManager.getJourneyById(journeyId);
		
		map.addAttribute("day", new Day());
		map.addAttribute("dayList", journey.getDays());
		map.addAttribute("journey", journey);
		
		return "admin/journey";
	}
	*/
    
	@RequestMapping(value = "/admin/journey/add", method = RequestMethod.GET, params="userId")
	public String displayAddJourney(HttpServletRequest request, ModelMap map, @RequestParam(value = "userId") Integer userId) 
	{
		SessionUtil.refreshAuthenticatedUser(request, userManager);
		
        // Permissions
        if (!SessionUtil.isAuthenticatedUser(request)) {
            return "403";
        }
        
		Journey journey = new Journey();
		journey.setUser(userManager.getUserById(userId));
		map.addAttribute("journey", journey);
		map.addAttribute("user", userManager.getUserById(userId));
		map.addAttribute("categoriesGeo", categoryGeoManager.getAllCategoryGeos());
		map.addAttribute("categoriesTrip", categoryTripManager.getAllCategoryTrips());
		
		return "admin/journey_edit";
	}

    @RequestMapping(value = "/admin/journey/edit/{journeyId}", method = RequestMethod.GET)
    public String displayEditJourney(HttpServletRequest request, ModelMap map, @PathVariable(value="journeyId") Integer journeyId) 
    {
    	SessionUtil.refreshAuthenticatedUser(request, userManager);
    	
        Journey journey = journeyManager.getJourneyById(journeyId);

        // Permissions
        if (!PermissionUtil.hasPermission(request, journey, PermissionUtil.EDIT)) {
            return "403";
        }

        map.addAttribute("journey", journey);
        map.addAttribute("user", journey.getUser());
        map.addAttribute("categoriesGeo", categoryGeoManager.getAllCategoryGeos());
        map.addAttribute("categoriesTrip", categoryTripManager.getAllCategoryTrips());
        
        return "admin/journey_edit";
    }
    
    @RequestMapping(value = {"/admin/journey/edit/", "/admin/journey/edit/{journeyId}"}, method = RequestMethod.POST)
    public String editJourney(HttpServletRequest request, ModelMap map, @ModelAttribute(value="journey") Journey newJourney, BindingResult result, @RequestParam(value = "valid", required=false) String valid, @RequestParam(value = "cancel", required=false) String cancel, @RequestParam(value = "userId", required=false) Integer userId, @RequestParam(value = "categoryGeoId") Integer categoryGeoId ,@RequestParam(value = "categoryTripId") Integer categoryTripId) 
    {
    	Journey journey = null;
    	User user = null;
    	
    	if (newJourney.getId() != null) {
    		journey = journeyManager.getJourneyById(newJourney.getId());
    		user = journey.getUser();
    	} else {
    		user = userManager.getUserById(userId);
    	}

    	// Permissions
    	if (!PermissionUtil.hasPermission(request, journey, PermissionUtil.EDIT)) {
    	    return "403";
    	}
    	
        if ((valid != null) && (valid != "")) {
        	
            String returnJsp = "admin/journey_edit";
            
            CategoryGeo categoryGeo = null;
            if(Validator.isNotNull(categoryGeoId)) {
            	categoryGeo = categoryGeoManager.getCategoryGeoById(categoryGeoId);
            }
            CategoryTrip categoryTrip = null;
            if(Validator.isNotNull(categoryGeoId)) {
            	categoryTrip = categoryTripManager.getCategoryTripById(categoryTripId);
            }
            
        	if (newJourney.getId() != null) {
        		
        		// Edit
        		journey.setTitle(newJourney.getTitle());
                journey.setPictureUrl(newJourney.getPictureUrl());
                journey.setStartDate(newJourney.getStartDate());
                journey.setEndDate(newJourney.getEndDate());
                journey.setPassword(newJourney.getPassword());
                journey.setCategoryGeo(categoryGeo);
                journey.setCategoryTrip(categoryTrip);
                
                try {
					journeyManager.editJourney(journey);
					RequestUtil.setSuccessMessage(map, "Modification effectuée avec succès");
					
				} catch (ServiceException e) {
					RequestUtil.setErrorMessage(map, e.getMessage());
				}
                
        	} else {
        		
        		// Add
        		journey = newJourney;
        		
        		journey.setPassword(newJourney.getPassword());
        		journey.setUser(user);
        		
                journey.setCategoryGeo(categoryGeo);
                journey.setCategoryTrip(categoryTrip);
                
        		try {
					journeyManager.addJourney(journey);
	        		RequestUtil.setSuccessMessage(map, "Ajout effectué avec succès");
	        		returnJsp = "redirect:/app/user/edit/" + user.getId();

				} catch (ServiceException e) {
					RequestUtil.setErrorMessage(map, e.getMessage());
				}
        		
        	}
            
            map.addAttribute("journey", journey);
            map.addAttribute("user", journey.getUser());
            map.addAttribute("categoriesGeo", categoryGeoManager.getAllCategoryGeos());
            map.addAttribute("categoriesTrip", categoryTripManager.getAllCategoryTrips());
            
            return returnJsp;
        }
        
        if (user != null) {
        	return "redirect:/app/admin/user/" + user.getId();
        }
        return "redirect:/app/admin/user";
        
        
    }
    
	@RequestMapping("/admin/journey/delete/{journeyId}")
	public String deleteJourney(HttpServletRequest request, ModelMap map, @PathVariable("journeyId") Integer journeyId)
	{
		Journey journey = journeyManager.getJourneyById(journeyId);
		
		if (PermissionUtil.hasPermission(request, journey, PermissionUtil.EDIT)) {
    		journeyManager.deleteJourney(journeyId);
    		
    		return "redirect:/app/admin/user/" + journey.getUser().getId();
		}
		
        return "403";
		
	}
	
	@RequestMapping(value = "/journey/comments/add", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request, ModelMap map, @RequestParam(value = "entityId") Integer entityId, @RequestParam(value = "content") String content)
	{
		Journey journey = journeyManager.getJourneyById(entityId);
		
        // Permissions
        if (!PermissionUtil.hasPermission(request, journey, PermissionUtil.VIEW)) {
            return "403";
        }
        		    
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setDate(Calendar.getInstance().getTime());
		comment.setJourney(journey);
		comment.setUser(SessionUtil.getAuthenticatedUser(request));
		commentManager.addComment(comment);
		
		initMapAttributeFromJourney(journey, map);
        
        return "journey";
		
	}

	public void setJourneyManager(JourneyManager journeyManager) {
		this.journeyManager = journeyManager;
	}
	
	private void initMapAttributeFromJourney(Journey journey, ModelMap map) {
	    
	    Date startDate = journey.getStartDate();
        Calendar calMondayBeforeStartDate = Calendar.getInstance();
        calMondayBeforeStartDate.setTime(startDate);
        int paddingStartDate = calMondayBeforeStartDate.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY - 1;

        if (paddingStartDate < 0) {
        	paddingStartDate = paddingStartDate + Calendar.DAY_OF_WEEK;
        }
        
        Date endDate = journey.getEndDate();
        Calendar calSundayAfterEndDate = Calendar.getInstance();
        calSundayAfterEndDate.setTime(endDate);
        int paddingEndDate = Calendar.SATURDAY - calSundayAfterEndDate.get(Calendar.DAY_OF_WEEK) + 1;
        
        map.addAttribute("journey", journey);
        map.addAttribute("paddingStartDate", paddingStartDate);
        map.addAttribute("paddingEndDate", paddingEndDate);
        
        // Handle comments
        map.addAttribute("post_url_comments", "/Journeys/app/journey/comments/add");
        map.addAttribute("entityId", journey.getId());
        map.addAttribute("comments", commentManager.getCommentsByJourneyId(journey.getId()));
	}
    
    /**
     * Handle request to download a PDF document
     */
    @RequestMapping(value = "/journey/pdf/{journeyId}", method = RequestMethod.GET)
    public ModelAndView downloadExcel(HttpServletRequest request, ModelMap map, @PathVariable("journeyId") Integer journeyId) {

    	Journey journey = journeyManager.getJourneyById(journeyId);
        
    	initMapAttributeFromJourney(journey, map);
    	
        if (!PermissionUtil.hasPermission(request, journey, PermissionUtil.VIEW)) {
            return new ModelAndView("403");
        }
            
        return new ModelAndView("pdfView", "journey", journey);
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displayEditJourney(HttpServletRequest request, ModelMap map, @RequestParam(value="searchTerms") String searchTerms) 
    {
    	SessionUtil.refreshAuthenticatedUser(request, userManager);
    	
        Set<Journey> journeys = IndexerUtil.searchJourneys(searchTerms);
        Set<User> users = IndexerUtil.searchUsers(searchTerms);

        map.addAttribute("searchJourneyResults", journeys);
        map.addAttribute("searchUserResults", users);
        map.addAttribute("searchTerms", searchTerms);
        
        return "search_results";
    }
}
