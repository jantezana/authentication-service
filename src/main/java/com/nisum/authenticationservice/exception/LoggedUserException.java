package com.nisum.authenticationservice.exception;

public class LoggedUserException extends NisumException {

    public LoggedUserException(final String message) {
        super("LOGGED_USER_FAILED", message);
    }
}
