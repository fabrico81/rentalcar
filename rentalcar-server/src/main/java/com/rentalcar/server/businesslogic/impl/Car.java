package com.rentalcar.server.businesslogic.impl;

import com.rentalcar.server.businesslogic.service.CarService;
import com.rentalcar.server.exception.LocationNotFoundException;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author faber
 */
@Component
public class Car implements CarService {

    @PersistenceContext
    private EntityManager em;

    private static final Log log = LogFactory.getLog(Car.class);

    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;



    public Car(CarRepository carRepository, LocationRepository locationRepository) {
        this.carRepository = carRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public com.rentalcar.server.model.Car updateCarLocation(Integer carId, String locationName){

        com.rentalcar.server.model.Car car = carRepository.findById(carId).get();

        Location location = locationRepository.findLocationByName(locationName);
        if(location == null)
            throw new LocationNotFoundException("Location name:" + locationName);

        car.setLocation(location);
        car = carRepository.save(car);
        return car;
    }

    @Override
    public List<com.rentalcar.server.model.Car> checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, String startDateInString, String endDateInString) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date startDate = formatter.parse(startDateInString);
        Date endDate = formatter.parse(endDateInString);
        List<com.rentalcar.server.model.Car> cars = carRepository.checkCarAvailable(pickUpLocationId, dropOffLocationId, startDate,endDate);
        return cars;
    }
}
