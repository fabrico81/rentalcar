package com.rentalcar.server.service;

import com.rentalcar.server.exception.LocationNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author faber
 */
@Component
public class CarService implements ICarService {

    private static final Log log = LogFactory.getLog(CarService.class);

    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;


    public CarService(CarRepository carRepository, LocationRepository locationRepository) {
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public Car updateCarLocation(Integer carId, String locationName){

        Car car = carRepository.findById(carId).get();

        Location location = locationRepository.findLocationByName(locationName);
        if(location == null)
            throw new LocationNotFoundException("Location name:" + locationName);

        car.setLocation(location);
        car = carRepository.save(car);
        return car;
    }
}
