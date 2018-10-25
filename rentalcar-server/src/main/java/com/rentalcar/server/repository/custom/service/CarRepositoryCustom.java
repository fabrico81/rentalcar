package com.rentalcar.server.repository.custom.service;

import com.rentalcar.server.model.Car;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author faber
 */

public interface CarRepositoryCustom {

    List<Car> checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, Date startDate, Date endDate);

}
