package com.rentalcar.server;

import com.rentalcar.server.model.User;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author faber
 */

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    public List<User> findAll(){
        return null;
    }

    public User getUserById(Integer id){
        return null;
    }

    public User save(User user){
        return null;
    }

//    public User deleteById(int id){
//        Iterator<User> iterator = users.iterator;
//    }
}
