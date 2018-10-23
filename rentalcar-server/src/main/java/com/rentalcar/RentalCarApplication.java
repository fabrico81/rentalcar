package com.rentalcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author faber
 */
@SpringBootApplication
public class RentalCarApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application.properties");
        SpringApplication.run(RentalCarApplication.class, args);
    }
}
