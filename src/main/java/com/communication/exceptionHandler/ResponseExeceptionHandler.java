package com.communication.exceptionHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;


@ControllerAdvice
public class ResponseExeceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	private String fields;
	
	Locale ptLocale = new Locale("pt", "BR");
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message",ptLocale);

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if(ex.getCause() instanceof JsonMappingException) {
			JsonMappingException result = (JsonMappingException) ex.getCause();							
			this.fields = result.getPath().stream().map(Reference::getFieldName).collect(Collectors.joining("."));
			String message = resourceBundle.getString("messagem.invalida");
			String messageForDeveloper = ex.getCause().toString();
			return handleExceptionInternal(ex, new Erro(""+status.value(),message, this.fields,messageForDeveloper), headers, status, request);
		}else {
			String message = resourceBundle.getString("messagem.invalida");
			String messageForDeveloper = ex.getCause().toString();
			return handleExceptionInternal(ex, new Erro(status.toString(),message, messageForDeveloper,null), headers, status, request);
		}
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Erro> erros = creatListErro(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,WebRequest request) {
		String messageForDeveloper = ex.getObjectName();
		return new ResponseEntity<>(messageForDeveloper, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	ResponseEntity<Set<String>> handleConstraintViolation(ConstraintViolationException e) {
	    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

		Set<String> messages = new HashSet<>(constraintViolations.size());
		messages.addAll(constraintViolations.stream()
		        .map(constraintViolation -> String.format("%s valor '%s' %s", constraintViolation.getPropertyPath(),constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
		        .collect(Collectors.toList()));
		
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler({ ObjectMessageException.class })
	public ResponseEntity<Object> handleObjectMessageException(ObjectMessageException ex,WebRequest request) {
		return handleExceptionInternal(ex, new Erro(""+ex.getStatus().value(),
													ex.getMessage(),
													ex.getField(),
													ex.toString()), new HttpHeaders(), ex.getStatus(), request);
	}
	
	
	private List<Erro> creatListErro(BindingResult bindingResult){
		List<Erro> erros =  new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String messageForUser = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(new Erro("400",messageForUser, fieldError.getField(),fieldError.toString()));
		}
		return erros;
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorDto processConstraintError(DataIntegrityViolationException ex) {
		logger.error(ex.getMessage());
		return new ErrorDto(ex.getMessage());
	}
	
	public static class ErrorDto{
		
		private String msg;
		
		public ErrorDto(String msg) {
			this.msg = msg;
		}
		
		public String getMsg() {
			return msg;
		}
		
	}
	
}
