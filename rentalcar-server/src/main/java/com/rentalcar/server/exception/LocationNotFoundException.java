package com.rentalcar.server.exception;

/**
 * @author faber
 */

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(String message) {
        super(message);
    }
}
