package com.nisum.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public @ResponseBody ErrorResponse handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setKey(userNotFoundException.getErrorKey());
        errorResponse.setMessage(userNotFoundException.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(value = NisumException.class)
    public @ResponseBody ErrorResponse handleNisumException(NisumException nisumException) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setKey(nisumException.getErrorKey());
        errorResponse.setMessage(nisumException.getMessage());

        return errorResponse;
    }

    @ExceptionHandler(value = Exception.class)
    public @ResponseBody ErrorResponse handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setKey("INTERNAL_SERVER_ERROR");
        errorResponse.setMessage(exception.getMessage());

        return errorResponse;
    }
}

