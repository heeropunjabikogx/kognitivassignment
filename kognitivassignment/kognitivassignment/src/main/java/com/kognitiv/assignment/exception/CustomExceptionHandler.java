package com.kognitiv.assignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;



@ControllerAdvice
public class CustomExceptionHandler extends DefaultHandlerExceptionResolver{
	private static final String URL_DOES_NOT_EXIST="This URL doesnot exist";
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	 ResponseEntity<String> handleMethodNotSupportedRequests(HttpServletRequest request,Exception e) {
		 return new ResponseEntity<>(URL_DOES_NOT_EXIST,HttpStatus.BAD_REQUEST);
	 }

}