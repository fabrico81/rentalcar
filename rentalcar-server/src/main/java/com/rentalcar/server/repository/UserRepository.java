package com.rentalcar.server.repository;

import com.rentalcar.server.model.Location;
import com.rentalcar.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author faber
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT new User( u.id, u.name, u.surname, u.birthDate, u.email) FROM #{#entityName} u where u.name = :nameUser")
    User findByName(@Param("nameUser") String nameUser);

}
