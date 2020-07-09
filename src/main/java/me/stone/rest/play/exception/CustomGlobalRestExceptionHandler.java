package me.stone.rest.play.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalRestExceptionHandler {
	
	// UsernameNotFoundException
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CustomErrorDetails handleUsernameNotFounddException(UsernameNotFoundException ex) {
		return new CustomErrorDetails(LocalDateTime.now(), "Username Not Found Exception in CCREH", ex.getMessage());
	}

}
