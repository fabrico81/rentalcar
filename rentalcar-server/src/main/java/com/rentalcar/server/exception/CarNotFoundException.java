package com.rentalcar.server.exception;

/**
 * @author faber
 */

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message) {
        super(message);
    }
}
