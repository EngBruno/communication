package com.communication.exceptionHandler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectMessageException extends RuntimeException {

	private HttpStatus status = HttpStatus.BAD_REQUEST;
	private String field;
	
	public ObjectMessageException(String message,String field) {		
		super(message);
		this.field = field;
	}
	
	public ObjectMessageException(String message,HttpStatus status,String field) {		
		super(message);
		this.status = status;
		this.field = field;
	}
	
	
	
}
