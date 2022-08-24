package com.nisum.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

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
    public @ResponseBody ErrorResponse handleUserNotFoundException(LoggedUserException loggedUserException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setKey(loggedUserException.getErrorKey());
        errorResponse.setMessage(loggedUserException.getMessage());
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
}

