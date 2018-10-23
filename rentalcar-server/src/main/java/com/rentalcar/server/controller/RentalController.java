package com.rentalcar.server.controller;

import com.rentalcar.server.exception.RentalNotFoundException;
import com.rentalcar.server.model.Rental;
import com.rentalcar.server.repository.RentalRepository;
import com.rentalcar.server.service.IRentalService;
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
public class RentalController {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private IRentalService rentalService;


    @GetMapping(path = "/rentals")
    @ApiOperation(value = "Finds All rental ",
            notes = "",
            response = Rental.class,
            responseContainer = "List")
    public List<Rental> getRentals(){

        return rentalRepository.findAll();
    }

    @GetMapping(path = "/rental/{id}")
    public Resource<Rental> getRentalById(@PathVariable Integer id){

        Optional<Rental> rental = rentalRepository.findById(id);
        if(!rental.isPresent())
            throw new RentalNotFoundException("id-" +id);

        Resource<Rental> resource = new Resource<Rental>(rental.get());
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getRentals());
        resource.add(linkTo.withRel("all-rentals"));
        return resource;
    }

    @PostMapping("/rental")
    public ResponseEntity<Object> addRental(@Valid @RequestBody Rental rental){

        Rental savedRental = rentalRepository.save(rental);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(savedRental.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/rental/{id}")
    public void deleteRental(@PathVariable int id){
        rentalRepository.deleteById(id);
    }

    @PostMapping("/rent_car/{userId}/{carId}/{pickUpLocationId}/{dropOffLocationId}")
    public ResponseEntity<Rental> rentCar(@PathVariable Integer userId, @PathVariable Integer carId, @PathVariable Integer pickUpLocationId,
                                          @PathVariable Integer dropOffLocationId) throws ParseException {
        return new ResponseEntity<Rental>( rentalService.rentCar(userId, carId, pickUpLocationId, dropOffLocationId), HttpStatus.OK);


    }

}
