package com.rentalcar.server.repository;

import com.rentalcar.server.model.Car;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author faber
 */

public class CarRepositoryCustomImpl implements CarRepositoryCustom {

    @PersistenceContext
    private EntityManager em;



    @Override
    public List<Car> checkCarAvailable(Integer pickUpLocationId, Integer dropOffLocationId, Date startDate, Date endDate) {
        Query query =   em.createNativeQuery("SELECT C.id, C.MODEL, C.BRAND FROM CAR C RIGHT JOIN RENTAL R ON C.ID = R.CAR_ID WHERE R.START_DATE < :startDate or R.END_DATE > :endDate and R.DROP_OFF_LOCATION_ID = :dropOffLocationId AND R.PICK_UP_LOCATION_ID=C.LOCATION_ID")
                .setParameter("endDate", endDate)
                .setParameter("dropOffLocationId", dropOffLocationId)
                .setParameter("startDate", startDate);


        List<Car> cars = query.getResultList();
        return cars;
    }
}
