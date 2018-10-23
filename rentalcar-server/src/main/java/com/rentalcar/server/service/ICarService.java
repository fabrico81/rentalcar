package com.rentalcar.server.service;

import com.rentalcar.server.model.Car;
import org.springframework.stereotype.Service;

/**
 * @author faber
 */
@Service
public interface ICarService {

    public Car updateCarLocation(Integer carId, String locationName);
}
