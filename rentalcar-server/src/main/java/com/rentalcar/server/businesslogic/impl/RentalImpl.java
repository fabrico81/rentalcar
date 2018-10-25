package com.rentalcar.server.businesslogic.impl;

import com.rentalcar.server.businesslogic.service.RentalService;
import com.rentalcar.server.exception.CarNotFoundException;
import com.rentalcar.server.exception.LocationNotFoundException;
import com.rentalcar.server.exception.UserNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.model.Rental;
import com.rentalcar.server.model.User;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import com.rentalcar.server.repository.RentalRepository;
import com.rentalcar.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @author faber
 */
@Component
public class RentalImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;

    /**
     * This method rents a car
     * @param userId
     * @param carId
     * @param pickUpLocationId
     * @param dropOffLocationId
     * @param startDateInString
     * @param endDateInString
     * @return
     * @throws ParseException
     */

    @Override
    public Rental rentCar(Integer userId, Integer carId, Integer pickUpLocationId, Integer dropOffLocationId, String startDateInString, String endDateInString) throws ParseException {

        Optional<User> user = userRepository.findById(userId);
        Optional<Car> car = carRepository.findById(carId);
        Optional<Location> pickUpLocation = locationRepository.findById(pickUpLocationId);
        Optional<Location> dropOffLocation = locationRepository.findById(dropOffLocationId);


        if(!user.isPresent())
            throw new UserNotFoundException("Impossible to rent a car, User not found");
        if(!car.isPresent())
            throw new CarNotFoundException("Impossible to rent a car, Car not found");
        if(!pickUpLocation.isPresent())
            throw new LocationNotFoundException("Impossible to pick-up a car for this Location");
        if(pickUpLocation.get().getId() == car.get().getLocation().getId())
            throw new LocationNotFoundException("Car not available for this location");
        if(!dropOffLocation.isPresent())
            throw new LocationNotFoundException("Impossible dropOff the car in this Location");
        if(dropOffLocation.get().getId() == car.get().getLocation().getId())
            throw new LocationNotFoundException("Car not available for this location");


        com.rentalcar.server.model.Rental rental = new com.rentalcar.server.model.Rental();
//        rental.setId(400);
        rental.setCar(car.get());
        rental.setPickUpLocation(pickUpLocation.get());
        rental.setDropOffLocation(dropOffLocation.get());
        rental.setStartDate(new SimpleDateFormat("dd-M-yyyy hh:mm:ss a").parse(startDateInString));
        rental.setEndDate(new SimpleDateFormat("dd-M-yyyy hh:mm:ss a").parse(endDateInString));
        rental.setUser(user.get());
        rental.setDescription("");
        rental = rentalRepository.save(rental);

        return rental;
    }
}
