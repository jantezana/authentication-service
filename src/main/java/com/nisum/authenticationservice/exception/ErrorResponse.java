package com.nisum.authenticationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ErrorResponse class.
 *
 * @author jantezana
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int httpCode;
    private String key;
    private String message;

    public ErrorResponse(String message) {
        super();
        this.message = message;
    }
}

