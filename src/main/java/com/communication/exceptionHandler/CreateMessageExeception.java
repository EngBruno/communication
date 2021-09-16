package com.communication.exceptionHandler;

import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.http.HttpStatus;

public class CreateMessageExeception {
	
	public static ObjectMessageException createObjectMessageNotFoundExeception(String message,String field) {
		Locale ptLocale = new Locale("pt", "BR");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message",ptLocale);
		if(resourceBundle!=null) {
			String result = resourceBundle.getString(message);
			if(result!=null) {
				return new ObjectMessageException(result,HttpStatus.NOT_FOUND,field);
			}	
		}
		return new ObjectMessageException(message,HttpStatus.NOT_FOUND,field);
	}
	
	public static ObjectMessageException createObjectMessageBadRequestExeception(String message,String field) {
		Locale ptLocale = new Locale("pt", "BR");
		ResourceBundle resourceBundle = ResourceBundle.getBundle("message",ptLocale);
		if(resourceBundle!=null) {
			String result = resourceBundle.getString(message);
			if(result!=null) {
				return new ObjectMessageException(result,field);						
			}	
		}
		return new ObjectMessageException(message,field);
	}
	
	
	
	
}
