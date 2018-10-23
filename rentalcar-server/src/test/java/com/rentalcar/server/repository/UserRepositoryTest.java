package com.rentalcar.server.repository;

import com.rentalcar.server.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author faber
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    public static final String EMAIL = "email@email.com";

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads()
    {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void findByNameTest(){
        Optional<User> user = userRepository.findById(1);
        user.ifPresent(user1 -> Assert.assertNotNull(userRepository.findByName(user.get().getName())));
    }

    @Test
    public void UserCrud(){
        User user = create();
        read();
        update(user);
        delete(user);

    }

    private User create(){
        User user = new User();
        user.setId(1000);
        user.setBirthDate(new Date());
        user.setEmail("user@user.com");
        user.setName("Caroline");
        user.setSurname("King");

        User entityUser = userRepository.save(user);
        Assert.assertNotNull("Creating Failed. User: ", user.getId());
        return entityUser;
    }

    private void delete(User user){

        Optional<User> userOptional = userRepository.findById(user.getId());
        userRepository.deleteById(userOptional.get().getId());
        userOptional = userRepository.findById(user.getId());
        Assert.assertNotNull("Record is not deleted.",userOptional);
    }

    private void update(User user){

        user.setEmail(EMAIL);
        userRepository.save(user);
        Assert.assertEquals("Record Updated", EMAIL, user.getEmail());

    }

    private void read(){
        List<User> users = userRepository.findAll();
        Assert.assertNotNull(users);

    }
}
