package com.nisum.authenticationservice.exception;


public class UserNotFoundException extends NisumException {

    public UserNotFoundException(final String message) {
        super("USER_NOT_FOUND", message);
    }
}
