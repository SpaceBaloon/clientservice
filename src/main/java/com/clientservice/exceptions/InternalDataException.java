package com.clientservice.exceptions;

/**
 *
 * @author BelkinSergei
 */
public class InternalDataException extends RuntimeException {

    public InternalDataException() {
        super( "Unexpected problem with request handling." );
    }

    public InternalDataException(String message) {
        super(message);
    }

    public InternalDataException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
