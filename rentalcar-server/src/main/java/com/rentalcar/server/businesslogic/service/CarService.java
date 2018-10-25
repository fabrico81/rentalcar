package com.rentalcar.server.businesslogic.service;

import com.rentalcar.server.model.Car;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author faber
 */
@Service
public interface CarService {

    public Car updateCarLocation(Integer carId, String locationName);

    public List<Car> checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, String startDate, String endDate) throws ParseException;
}
