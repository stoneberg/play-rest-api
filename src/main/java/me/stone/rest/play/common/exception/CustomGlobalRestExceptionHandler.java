package me.stone.rest.play.common.exception;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CustomGlobalRestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
        return buildError(ErrorCode.USER_NOT_FOUND, ex.getMessage(), request);
    }
    
    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        return buildError(ErrorCode.USER_NOT_FOUND, ex.getMessage(), request);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return buildError(ErrorCode.RESOURCE_NOT_FOUND, request);
    }
    
    @ExceptionHandler(UserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUserExistsException(UserExistsException ex, WebRequest request) {
        return buildError(ErrorCode.USER_EXISTS, ex.getMessage(), request);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, request, fieldErrors);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleMethodArgumentTypeMismatchExceptio(MethodArgumentTypeMismatchException ex, WebRequest request) {
    	return buildError(ErrorCode.INPUT_VALUE_INVALID, ex.getMessage(), request);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleBindException(BindException ex, WebRequest request) {
        final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(ex.getBindingResult());
        return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, request, fieldErrors);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        final ErrorCode errorCode = ErrorCode.INPUT_VALUE_INVALID;
        final String message = getResultMessage(ex.getConstraintViolations().iterator());
        log.error(errorCode.getMessage(), ex.getConstraintViolations());
        return buildError(errorCode, message, request);
    }

//    @ExceptionHandler(EmailDuplicationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ErrorResponse handleConstraintViolationException(EmailDuplicationException ex, WebRequest request) {
//        final ErrorCode errorCode = ErrorCode.EMAIL_DUPLICATION;
//        log.error(errorCode.getMessage(), ex.getEmail() + ex.getField());
//        return buildError(errorCode, request);
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
    	log.error("@DataIntegrityViolationException::{}", ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.INPUT_VALUE_INVALID, request);
    }


//    @ExceptionHandler(PasswordFailedExceededException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ErrorResponse handlePasswordFailedExceededException(PasswordFailedExceededException ex, WebRequest request) {
//        log.error(ex.getMessage());
//        return buildError(ex.getErrorCode(), request);
//    }
    
    // HttpRequestMethodNotSupportedException(405)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
    	log.error("@HttpRequestMethodNotSupportedException::{}", ExceptionUtils.getMessage(ex)); 
    	return buildError(ErrorCode.METHOD_NOT_ALLOWED, request);
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleAnyException(Exception ex, WebRequest request) {
        log.error("@Exception::{}", ExceptionUtils.getMessage(ex));
        return buildError(ErrorCode.UNKNOWN_EXCEPTION, request);
    }


    /**
     * find HttpRequestMethodNotSupportedException message
     * @param bindingResult
     * @return
     */
    private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
        final List<FieldError> errors = bindingResult.getFieldErrors();
        return errors.parallelStream()
                .map(error -> ErrorResponse.FieldError.builder()
                        .reason(error.getDefaultMessage())
                        .field(error.getField())
                        .value((String) error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());
    }


    private ErrorResponse buildError(ErrorCode errorCode, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .isSuccess(Boolean.FALSE)
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(errorCode.getMessage())
                .build();
    }
    
    private ErrorResponse buildError(ErrorCode errorCode, String message, WebRequest request) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .isSuccess(Boolean.FALSE)
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(message)
                .build();
    }

    private ErrorResponse buildFieldErrors(ErrorCode errorCode, WebRequest request, List<ErrorResponse.FieldError> errors) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .isSuccess(Boolean.FALSE)
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .path(request.getDescription(false))
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }
    
    
    /**
     * find ConstraintViolationException message
     * @param violationIterator
     * @return
     */
    protected String getResultMessage(final Iterator<ConstraintViolation<?>> violationIterator) {
        final StringBuilder resultMessageBuilder = new StringBuilder();
        while (violationIterator.hasNext()) {
            final ConstraintViolation<?> constraintViolation = violationIterator.next();
            resultMessageBuilder
                .append("['")
                .append(getPopertyName(constraintViolation.getPropertyPath().toString())) // 유효성 검사가 실패한 속성
                .append("' is '")
                .append(constraintViolation.getInvalidValue()) // 유효하지 않은 값
                .append("'. ")
                .append(constraintViolation.getMessage()) // 유효성 검사 실패 시 메시지
                .append("]");

            if (violationIterator.hasNext()) {
                resultMessageBuilder.append(", ");
            }
        }

        return resultMessageBuilder.toString();
    }

    protected String getPopertyName(String propertyPath) {
        return propertyPath.substring(propertyPath.lastIndexOf('.') + 1); // 전체 속성 경로에서 속성 이름만 가져온다.
    }    
    
}
