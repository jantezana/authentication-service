package com.nisum.authenticationservice.exception;

import lombok.Getter;
import lombok.Setter;

public class NisumException extends RuntimeException {

    @Getter
    @Setter
    private final String errorKey;

    public NisumException(final String message) {
        super(message);
        this.errorKey = "INTERNAL_SERVER_ERROR";
    }

    public NisumException(final String errorKey, final String message) {
        super(message);
        this.errorKey = errorKey;
    }
}
