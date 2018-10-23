package com.rentalcar.server.repository;

import com.rentalcar.server.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author faber
 */

public interface LocationRepository extends JpaRepository<Location,Integer> {

    @Query("SELECT new Location(l.id, l.name ,l.address, l.city) FROM #{#entityName} l where l.name = :locationName")
    Location findLocationByName(@Param("locationName") String locationName);


}
