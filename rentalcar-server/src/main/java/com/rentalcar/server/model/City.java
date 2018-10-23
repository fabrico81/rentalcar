package com.rentalcar.server.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author faber
 */

@ApiModel(description = "City where drop-up or drop-off Car")
@Entity
public class City {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "city")
    private List<Location> location;

    public City(){}

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }
}
