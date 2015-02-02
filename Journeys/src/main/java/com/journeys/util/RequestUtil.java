package com.journeys.util;

import org.springframework.ui.ModelMap;

public class RequestUtil {
	
	public static void setErrorMessage(ModelMap map, String message) {
		map.addAttribute("errorMessage", message);
		map.addAttribute("message", message);
	}
	
	public static void setSuccessMessage(ModelMap map, String message) {
	    map.addAttribute("successMessage", message);
	    map.addAttribute("message", message);
	}

}
