package com.rentalcar.server.repository;

import com.rentalcar.server.model.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author faber
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    public void contextLoads()
    {
        assertThat(locationRepository).isNotNull();
    }
    @Test
    public void findByLocationName(){
        String locationName = "Branch1";
        Location location = locationRepository.findLocationByName(locationName);
        System.out.println("Location = " + location.getCity().getName()+", " +location.getName());
        Assert.assertNotNull("findByLocationName returned null", location);
    }
}
