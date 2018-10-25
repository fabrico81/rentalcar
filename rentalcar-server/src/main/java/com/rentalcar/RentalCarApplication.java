package com.rentalcar;

import com.rentalcar.server.trace.MDC;
import com.rentalcar.server.trace.MDC.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author faber
 */
@SpringBootApplication
public class RentalCarApplication {

    public static void main(String[] args) {
        MDC.setServicename(SERVICE_NAME.RENTAL_SERVICE);
        SpringApplication.run(RentalCarApplication.class, args);
    }
}
