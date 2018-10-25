package com.rentalcar.server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *  DAO Car Entity
 *
 * @author faber
 */
@ApiModel(description="Entity Car. ")
@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue
    private Integer id;
    private String brand;
    private String model;
    private Integer mileage;
    private String color;
    private String licencePlate;

    public Car(){
        super();
    }
    @JsonCreator
    public Car(Integer id, String brand, String model, Integer mileage, String color, String licencePlate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
        this.color = color;
        this.licencePlate = licencePlate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Location location;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "Car_Equipment",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
    @JsonIgnore
    private Set<Rental> rental;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Set<Equipment> getEquipment() {
        return equipments;
    }

    public void setEquipment(Set<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Set<Rental> getRental() {
        return rental;
    }

    public void setRental(Set<Rental> rental) {
        this.rental = rental;
    }
}
