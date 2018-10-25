package com.rentalcar.server.businesslogic.service;

import com.rentalcar.server.model.Rental;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @author faber
 */
@Service
public interface RentalService {

    Rental rentCar(Integer userId, Integer carId, Integer pickUpLocationId, Integer dropOffLocationId) throws ParseException;
}
