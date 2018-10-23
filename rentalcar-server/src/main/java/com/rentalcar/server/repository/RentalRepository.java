package com.rentalcar.server.repository;

import com.rentalcar.server.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author faber
 */

public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query("SELECT new Rental(r.id, r.description, r.startDate, r.endDate, r.pickUpLocation, r.dropOffLocation, r.user) FROM #{#entityName} r where r.user = :userId")
    void findRentalByUserId(Integer userId);
}
