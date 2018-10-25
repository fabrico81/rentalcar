package com.rentalcar.server.businesslogic;

import com.rentalcar.server.exception.LocationNotFoundException;
import com.rentalcar.server.model.Car;
import com.rentalcar.server.model.Location;
import com.rentalcar.server.repository.CarRepository;
import com.rentalcar.server.repository.LocationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author faber
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    LocationRepository locationRepository;
    @Autowired
    CarRepository carRepository;

    @PersistenceContext
    private EntityManager em;


    @Test
    public void updateCarLocation() {

        if (em.isOpen()) {
            Assert.assertNotNull(em);
        }
    }

    public void updateCarLocation(Car car, String locationName) {

        Location location = locationRepository.findLocationByName(locationName);
        if (location == null)
            throw new LocationNotFoundException("Location name:" + locationName);

        car.setLocation(location);
        Car entityCar = carRepository.save(car);
        Assert.assertNotNull(entityCar);
    }

    @Test
    public void checkCarAvailable() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));


        Query query =   em.createNativeQuery("SELECT C.*\n" +
                "FROM CAR C\n" +
                "RIGHT  JOIN RENTAL R ON C.ID = R.CAR_ID\n" +
                "WHERE\n" +
                "R.END_DATE > TO_DATE('2018-10-25 19:29:30', 'yyyy-MM-DD HH24:MI:ss')\n" +
                "OR\n" +
                " R.START_DATE < TO_DATE('2018-10-25 18:31:00', 'yyyy-MM-DD HH24:MI:ss') \n" +
                "AND\n" +
                "R.DROP_OFF_LOCATION_ID = 1\n" +
                "AND \n" +
                "R.PICK_UP_LOCATION_ID=C.LOCATION_ID\n");

        List<Car> cars = query.getResultList();

        Assert.assertNotNull(cars);
    }

/*
JPA 2 TEST
    public void checkCarAvailable() {
        Integer pickUpLocationId = 1;
        Integer dropOffLocationId;
        Date startDate;
        Date endDate;

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery();
        Root<Car> car = cq.from(Car.class);
        cq.select(car);
        Query qry = em.createQuery(cq);
        List<Car> cars1 = qry.getResultList();
        Join<Car, Rental> carRentalJoin = car.join(Car_.rental);
        cq.select(carRentalJoin).where(cb.equal(carRentalJoin.get(Rental_.idLocation), pickUpLocationId));
        TypedQuery<Car> q = em.createQuery(cq);

        List<Car> cars = q.getResultList();
        */
}
