package com.rentalcar.server.repository;

import com.rentalcar.server.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author faber
 */

public interface CityRepository extends JpaRepository<City,Integer> {
}
