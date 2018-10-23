package com.rentalcar.server.service;

import com.rentalcar.server.repository.RentalRepository;
import com.rentalcar.server.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author faber
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RentalRepository rentalRepository;


    @Test
    public void deleteUserServiceTest(){

        rentalRepository.findRentalByUserId(new Integer(1));

        userRepository.deleteById(1);


    }
}
