package com.journeys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class Validator {

	private static final String PASSWORD_PATTERN = 
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,30})";
	
	public static boolean isNull(String str) {
		if (str == null) {
			return true;
		}
		if (str.equals("")) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}
	
	public static boolean isNull(Integer integer) {
		if (integer == null) {
			return true;
		}
		if (integer <= 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(Integer integer) {
		return !isNull(integer);
	}
	
	public static boolean isEmail(String str) {
		return EmailValidator.getInstance().isValid(str);
	}
	
	public static boolean isUrl(String str) {
		return UrlValidator.getInstance().isValid(str);
	}
	
	public static boolean checkPassword(String str) {
		
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
