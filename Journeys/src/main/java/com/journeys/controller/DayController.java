package com.journeys.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.journeys.entity.Comment;
import com.journeys.entity.Day;
import com.journeys.service.CommentManager;
import com.journeys.service.DayManager;
import com.journeys.service.JourneyManager;
import com.journeys.service.ServiceException;
import com.journeys.service.UserManager;
import com.journeys.util.PermissionUtil;
import com.journeys.util.RequestUtil;
import com.journeys.util.SessionUtil;

@Controller
public class DayController {
	
    @Autowired
    private UserManager userManager;
    
    @Autowired
    private JourneyManager journeyManager;
    
    @Autowired
    private DayManager dayManager;
    
    @Autowired
    private CommentManager commentManager;

    @RequestMapping(value = "/day/{dayId}", method = RequestMethod.GET)
    public String displayDay(HttpServletRequest request, ModelMap map, @PathVariable(value="dayId") Integer dayId) 
    {
        SessionUtil.refreshAuthenticatedUser(request, userManager);
        
        Day day = dayManager.getDayById(dayId);

        if (!PermissionUtil.hasPermission(request, day, PermissionUtil.VIEW)) {
            return "403";
        }

        initMapAttributeFromDay(day, map);
        
        return "day";
    }
    
    @RequestMapping(value = "/admin/day/edit/{dayId}", method = RequestMethod.GET)
    public String displayEditDay(HttpServletRequest request, ModelMap map, @PathVariable(value="dayId") Integer dayId) 
    {
        Day day = dayManager.getDayById(dayId);
        
        if (!PermissionUtil.hasPermission(request, day, PermissionUtil.EDIT)) {
            return "403";
        }
        
        map.addAttribute("day", day);
        
        return "admin/day_edit";
    }
    
    @RequestMapping(value = "/admin/day/edit/{dayId}", method = RequestMethod.POST)
    public String editDay(HttpServletRequest request, ModelMap map, @ModelAttribute(value="day") Day newday, BindingResult result, @RequestParam(value = "valid", required=false) String valid, @RequestParam(value = "cancel", required=false) String cancel) 
    {
    	Day day = dayManager.getDayById(newday.getId());
    	
        if (!PermissionUtil.hasPermission(request, day, PermissionUtil.EDIT)) {
            return "403";
        }
        
        if ((valid != null) && (valid != "")) {
            
            day.setTitle(newday.getTitle());
            day.setPictureUrl(newday.getPictureUrl());
            day.setContent(newday.getContent());
            day.setLatitude(newday.getLatitude());
            day.setLongitude(newday.getLongitude());
            day.setEnabled(newday.getEnabled());
            
            try {
				dayManager.editDay(day);
				RequestUtil.setSuccessMessage(map, "Modification effectuée avec succès");
			} catch (ServiceException e) {
				RequestUtil.setErrorMessage(map, e.getMessage());
			}
            
            map.addAttribute("day", day);
            
            return "admin/day_edit";
        }
        
        return "redirect:/app/admin/journey/edit/" + day.getJourney().getId();
        
    }

	@RequestMapping(value = "/day/comments/add", method = RequestMethod.POST)
	public String addComment(HttpServletRequest request, ModelMap map, @RequestParam(value = "entityId") Integer entityId, @RequestParam(value = "content") String content)
	{
		Day day = dayManager.getDayById(entityId);
		
        if (!PermissionUtil.hasPermission(request, day, PermissionUtil.VIEW)) {
            return "403";
        }
        
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setDate(Calendar.getInstance().getTime());
		comment.setDay(day);
		comment.setUser(SessionUtil.getAuthenticatedUser(request));
		commentManager.addComment(comment);
		
		initMapAttributeFromDay(day, map);
		
		return "journey";
	}
	
	public void setDayManager(DayManager dayManager) {
		this.dayManager = dayManager;
	}
	
	private void initMapAttributeFromDay(Day day, ModelMap map) {
	    
        Day previousDay = dayManager.getPreviousDay(day.getJourney().getId(), day.getDate());
        Day nextDay = dayManager.getNextDay(day.getJourney().getId(), day.getDate());
        
        // Handle comments
        map.addAttribute("post_url_comments", "/Journeys/app/day/comments/add");
        map.addAttribute("entityId", day.getId());
        map.addAttribute("comments", commentManager.getCommentsByDayId(day.getId()));
        
        map.addAttribute("day", day);
        map.addAttribute("previousDay", previousDay);
        map.addAttribute("nextDay", nextDay);
        
	}
}
