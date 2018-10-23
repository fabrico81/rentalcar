package com.rentalcar.server.repository;

import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.City;
import com.rentalcar.server.model.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.mockito.Mockito;


import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author faber
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void contextLoads()
    {
        assertThat(carRepository).isNotNull();
    }
    @Test
    public void updateCarLocationTest(){

        Location location = new Location();
        location.setId(1);
        location.setAddress("Auguststrasse 9, 12165");
        location.setCity(new City("Berlin"));
        Car car = new Car();
        car.setBrand("Opel");
        car.setColor("Grey");
        car.setLicencePlate("HH123AB");
        car.setMileage(12344);
        car.setId(1);
        car.setLocation(location);
        Car entityCar = carRepository.save(car);
        Assert.assertNotNull(entityCar);
    }
}
