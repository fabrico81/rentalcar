package com.rentalcar.server.repository;

import com.rentalcar.server.model.Car;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author faber
 */
@Repository
public interface CarRepositoryCustom {

    List<Car> checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, Date startDate, Date endDate);

}
