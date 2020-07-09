package me.stone.rest.play.exception;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// MethodArgumentNotValidException
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"request arguments not valid", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

	// HttpRequestMethodNotSupportedException
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(),
				"request method not support", ex.getMessage());

		return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	// ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	// UserNotFoundException
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	// UsernameNotFoundException
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
	
	// ConstraintViolationException
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleContraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}

}
