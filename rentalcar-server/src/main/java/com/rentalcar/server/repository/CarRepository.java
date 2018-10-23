package com.rentalcar.server.repository;

import com.rentalcar.server.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author faber
 */

public interface CarRepository extends JpaRepository<Car,Integer> {
}
