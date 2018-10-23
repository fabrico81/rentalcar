package com.rentalcar.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.List;

/**
 * @author faber
 */

@ApiModel(description = "A city can have different location to pick-up and drop-off a car")
@Table(name = "location")
@Entity
public class Location {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;
    private String address;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonIgnore
    private City city;

    @OneToMany(mappedBy = "location")
    @JsonIgnore
    private List<Car> cars;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pickUpLocation")
    @JsonIgnore
    private Rental rentalPickUpLocation;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "dropOffLocation")
    @JsonIgnore
    private Rental rentalDropOffLocation;

    public Location(){}

    public Location(Integer id, String name, String address, City city) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public Location(Integer id, String address) {
        this.id = id;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Car> getCar() {
        return cars;
    }

    public void setCar(List<Car> cars) {
        this.cars = cars;
    }

    public String getName() { return name;}

    public void setName(String name) {this.name = name;}


    public Rental getRentalPickUpLocation() {
        return rentalPickUpLocation;
    }

    public void setRentalPickUpLocation(Rental rentalPickUpLocation) {
        this.rentalPickUpLocation = rentalPickUpLocation;
    }

    public Rental getRentalDropOffLocation() {
        return rentalDropOffLocation;
    }

    public void setRentalDropOffLocation(Rental rentalDropOffLocation) {
        this.rentalDropOffLocation = rentalDropOffLocation;
    }
}
