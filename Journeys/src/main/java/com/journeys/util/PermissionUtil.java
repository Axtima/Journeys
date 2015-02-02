/*
 * Creation : 30 janv. 2015
 */
package com.journeys.util;

import java.security.MessageDigest;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.journeys.entity.Day;
import com.journeys.entity.Journey;
import com.journeys.entity.User;

public class PermissionUtil {

    public final static int VIEW = 1;
    public final static int EDIT = 2;
    
    public static boolean hasPermission(HttpServletRequest request, Journey journey, int mode) {
                
        User currentUser = SessionUtil.getAuthenticatedUser(request);
        
        // Journey is belonging to current user
        if (currentUser != null && currentUser.getEmail().equals(journey.getUser().getEmail())) {
            return true;
        // Current user is the administrator
        } else if (currentUser != null && currentUser.isAdmin()) {
            return true;
        } else {
            
            if (mode == EDIT) {
                return false;
            }

            // mode VIEW
            
            if ((journey.getPassword() == null) || (journey.getPassword().equals(""))) {
                return true;
            }
            
            // Check in session
            Set<Integer> authenticatedJourneys = (Set<Integer>)request.getSession().getAttribute("authenticatedJourneys");
            if (authenticatedJourneys != null && authenticatedJourneys.contains(journey.getId())) {
                return true;
            }
            
        }
        
        return false;
    }
    
    public static boolean hasPermission(HttpServletRequest request, Day day, int mode) {
        
        boolean journeyPermission = hasPermission(request, day.getJourney(), mode);
        
        return journeyPermission;
    }
    
    public static String encodeSha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

        return hexString.toString();
    } catch(Exception ex){
       throw new RuntimeException(ex);
    }
}
}
