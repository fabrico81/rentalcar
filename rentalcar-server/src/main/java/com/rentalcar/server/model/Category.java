package com.rentalcar.server.model;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author faber
 */

@ApiModel(description="Entity Category, type of car (Van, City-Car, Station-wagon etc.).")
@Entity
public class Category {

    @GeneratedValue
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
