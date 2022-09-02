package com.nisum.authenticationservice.exception;

/**
 * LoggedUserException class.
 *
 * @author jantezana
 */
public class LoggedUserException extends NisumException {

    public LoggedUserException(final String message) {
        super("LOGGED_USER_FAILED", message);
    }
}
