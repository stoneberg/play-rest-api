package me.stone.rest.play.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		CustomErrorDetails customErrorDetails 
			= new CustomErrorDetails(LocalDateTime.now(), "From MethodArgumentNotValid Exception in GEH", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	// HttpRequestMethodNotSupportedException
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails 
			= new CustomErrorDetails(LocalDateTime.now(), "From HttpRequestMethodNotSupported Exception in GEH", ex.getMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

}
