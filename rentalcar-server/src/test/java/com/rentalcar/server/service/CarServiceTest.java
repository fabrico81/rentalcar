package com.rentalcar.server.service;

import com.rentalcar.server.exception.LocationNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author faber
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    CarRepository carRepository;

    @Test
    public void updateCarLocation(){

    }

    public Car updateCarLocation(Car car, String locationName){

        Location location = locationRepository.findLocationByName(locationName);
        if(location == null)
            throw new LocationNotFoundException("Location name:" + locationName);

        car.setLocation(location);
        Car entityCar = carRepository.save(car);
        return entityCar;
    }
}
