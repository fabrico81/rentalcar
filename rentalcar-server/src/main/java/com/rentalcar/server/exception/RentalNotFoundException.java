package com.rentalcar.server.exception;

/**
 * @author faber
 */

public class RentalNotFoundException extends RuntimeException {
    public RentalNotFoundException(String s) {
        super(s);
    }
}
