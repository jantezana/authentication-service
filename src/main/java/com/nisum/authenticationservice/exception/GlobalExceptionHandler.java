package com.nisum.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler class.
 *
 * @author jantezana
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponse handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setKey(userNotFoundException.getErrorKey());
        errorResponse.setMessage(userNotFoundException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = LoggedUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleLoggerUserException(LoggedUserException loggedUserException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setKey(loggedUserException.getErrorKey());
        errorResponse.setMessage(loggedUserException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setKey("UNAUTHORIZED");
        errorResponse.setMessage(usernameNotFoundException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ErrorResponse handleAuthenticationException(AuthenticationException AuthenticationException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setKey("UNAUTHORIZED");
        errorResponse.setMessage(AuthenticationException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setKey("CONSTRAINT_VIOLATION_EXCEPTION");
        errorResponse.setMessage(constraintViolationException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setKey("METHOD_ARGUMENT_NOT_VALID_EXCEPTION");

        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        errorResponse.setMessage(errors.toString());
        return errorResponse;
    }

    @ExceptionHandler(value = NisumException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleNisumException(NisumException nisumException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setKey(nisumException.getErrorKey());
        errorResponse.setMessage(nisumException.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setKey("INTERNAL_SERVER_ERROR");
        errorResponse.setMessage(exception.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponse handleRuntimeException(RuntimeException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setKey("INTERNAL_SERVER_ERROR");
        errorResponse.setMessage(exception.getMessage());

        return errorResponse;
    }
}

