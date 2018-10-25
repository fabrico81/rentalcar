package com.rentalcar.server.repository;

import com.rentalcar.server.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *  CarRepository
 *
 * @author faber
 */

public interface CarRepository extends JpaRepository<Car,Integer>, CarRepositoryCustom {

//    public Car checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, Date startDate, Date endDate);
    }