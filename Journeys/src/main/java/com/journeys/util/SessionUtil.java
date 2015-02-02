package com.journeys.util;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.journeys.entity.Journey;
import com.journeys.entity.Role;
import com.journeys.entity.User;
import com.journeys.service.JourneyManager;
import com.journeys.service.UserManager;

public class SessionUtil {
	
	private static String COOKIE_COUNTER_VIEW_NAME = "views";
    private static String COOKIE_VALUE_SEPARATOR = ";";
    
	public static User getAuthenticatedUser(HttpServletRequest request) {
		
	    User user = null;
	    
	    Object obj = request.getSession().getAttribute("authenticatedUser");
	    if (obj instanceof User) {
	        user = (User)obj;
	    }
	    
		return user;
	}
	
   public static boolean isAuthenticatedUser(HttpServletRequest request) {
        
        boolean result = false;
        
        Object obj = request.getSession().getAttribute("authenticatedUser");
        if (obj instanceof User) {
            result = true;
        }
        
        return result;
    }
   
   public static boolean isAdminUser(HttpServletRequest request) {
       
       boolean result = false;
       
       Object obj = request.getSession().getAttribute("authenticatedUser");
       if (obj instanceof User) {
           User user = (User)obj;
           for (Role role : user.getRoles()) {
               if (role.getName().equals("ROLE_ADMIN")) {
                   result = true;
               }
           }
       }
       
       return result;
   }
	
	public static User refreshAuthenticatedUser(HttpServletRequest request, UserManager userManager) {
	        
	    User user = null;
	        
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (obj instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            user = userManager.getUserByEmail(userDetails.getUsername());
            userManager.refreshLastConnectionDate(user);
        }
        
        request.getSession().setAttribute("authenticatedUser", user);
        
        return user;
	}

    public static boolean refreshCountViewsCookieOnJourney(HttpServletRequest request, HttpServletResponse response, JourneyManager journeyManager, Journey journey) {
       
        boolean count = false;
       
        Cookie cookie = null;
       
        String cookieValue = journey.getId().toString();
       
        Cookie[] existingCookies = request.getCookies();
        for (int i = 0; i < existingCookies.length; i++){
            Cookie tmp = existingCookies[i];
            if (COOKIE_COUNTER_VIEW_NAME.equals(tmp.getName())) {
                cookie = tmp;
            }
        }
          
        if (cookie == null) {
            cookie = new Cookie(COOKIE_COUNTER_VIEW_NAME,cookieValue);
            count = true;
            journeyManager.incrementCountView(journey);
            
        } else {
           
            String cookieOldValue = cookie.getValue();
            String[] visitedJourneyIds = cookieOldValue.split(COOKIE_VALUE_SEPARATOR);
           
            if (!Arrays.asList(visitedJourneyIds).contains(journey.getId().toString())) {
                cookieValue = cookieOldValue + COOKIE_VALUE_SEPARATOR + journey.getId().toString();
                count = true;
                journeyManager.incrementCountView(journey);
            }
        }
       
        // Expire after 1 year
        cookie.setMaxAge(60*60*24*365);
       
        response.addCookie(cookie);
        
        return count;
    }
}
