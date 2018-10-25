package com.rentalcar.server.businesslogic;

import com.rentalcar.server.exception.CarNotFoundException;
import com.rentalcar.server.exception.UserNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.model.Rental;
import com.rentalcar.server.model.User;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import com.rentalcar.server.repository.RentalRepository;
import com.rentalcar.server.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @author faber
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RentalServiceTest {

    @Autowired
    RentalRepository rentalRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    LocationRepository locationRepository;

    @Test
    public void rentCar() throws ParseException {
        /*User user = new User();
        user.setId(300);
        user.setBirthDate(new Date());
        user.setEmail("user@user.com");
        user.setName("Caroline");
        user.setSurname("King");

        Location location = new Location();
        location.setId(200);
        location.setAddress("Auguststrasse 9, 12165");
        location.setCity(new City("Berlin"));

        Car car = new Car();
        car.setBrand("Opel");
        car.setColor("Grey");
        car.setLicencePlate("HH123AB");
        car.setMileage(12344);
        car.setId(100);
        car.setLocation(location);
        */

        Optional<User> user = userRepository.findById(1);
        Optional<Car> car = carRepository.findById(1);
        Optional<Location> location = locationRepository.findById(1);

        if(!user.isPresent())
            throw new UserNotFoundException("Impossible to rent a car, User not found");

        if(!car.isPresent())
            throw new CarNotFoundException("impossible to rent a car, Car not found");

        Rental rental = new Rental();
        rental.setId(400);
        rental.setCar(car.get());
        rental.setPickUpLocation(location.get());
        rental.setDropOffLocation(location.get());
        rental.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-10-22 10:00"));
        rental.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2018-10-22 18:00"));
        rental.setUser(user.get());
        rental.setDescription("");

        rental = rentalRepository.save(rental);
        Assert.assertNotNull(rental);
    }
}
