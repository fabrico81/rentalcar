package com.rentalcar.server.controller;

import com.rentalcar.server.exception.CarNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.businesslogic.service.CarService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author faber
 */
@RestController
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @GetMapping(path = "/cars")
    @ApiOperation(value = "Finds All car ",
            notes = "",
            response = Car.class,
            responseContainer = "List")
    public List<Car> getCars(){

        return carRepository.findAll();
    }

    @GetMapping(path = "/car/{id}")
    @ApiOperation(value = "Finds car by id ",
            notes = "",
            response = Car.class,
            responseContainer = "Car Resouce")
    public Resource<Car> getCarById(@PathVariable Integer id){
        Optional<Car> car = carRepository.findById(id);
        if(!car.isPresent())
            throw new CarNotFoundException("id-" +id);

        Resource<Car> resource = new Resource<Car>(car.get());
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getCars());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/car")
    @ApiOperation(value = "Add car ", response = Car.class, responseContainer = "Car")
    public ResponseEntity<Car> addCar(@Valid @RequestBody Car car){

        Car savedCar = carRepository.save(car);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(savedCar.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/car/{id}")
    @ApiOperation(value = "Delete car by id ", response = Car.class)
    public void deleteCar(@PathVariable int id){
        carRepository.deleteById(id);

    }

    @PutMapping("/car")
    @ApiOperation(value = "Update car ", response = Car.class, responseContainer = "Car")
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity<Car>(car, HttpStatus.OK);
    }


    @ApiOperation(value = "Update Location Car by id, locationName ",response = Car.class,
            responseContainer = "Car")
    @PutMapping("/car_update/{carId}/{locationName}")
    public ResponseEntity<Car>updateCarLocation(@PathVariable Integer carId, @PathVariable String locationName){
        return new ResponseEntity<Car>(carService.updateCarLocation(carId,locationName), HttpStatus.OK);
    }

    @ApiOperation(value = "Check car available ",
            notes = "Parameters: pick up location ID, drop off Location ID, start date, end date",
            response = Car.class,
            responseContainer = "Object")
    @GetMapping("/car_available/{pickUpLocationId}/{dropOffLocationId}/{startDate}/{endDate}")
    public List<Car> checkCarAvailable(@PathVariable Integer pickUpLocationId,
                                       @PathVariable Integer dropOffLocationId,
                                       @PathVariable String startDate, @PathVariable String endDate) throws ParseException {


        return carService.checkCarAvailable(pickUpLocationId, dropOffLocationId,startDate,endDate);
    }
}
