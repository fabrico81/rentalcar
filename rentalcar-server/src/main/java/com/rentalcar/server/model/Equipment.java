package com.rentalcar.server.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author faber
 */
@ApiModel(description="Entity Equipment. Equipment available for the car. ")
@Entity
public class Equipment {

    @GeneratedValue
    @Id
    private Integer id;
    @ApiModelProperty(notes="Equipment name")
    private String name;

    @ManyToMany(mappedBy = "equipments")
    private Set<Car> cars = new HashSet<>();

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
