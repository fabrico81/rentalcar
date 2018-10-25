package com.rentalcar.server.controller;

import com.rentalcar.server.exception.UserNotFoundException;
import com.rentalcar.server.model.User;
import com.rentalcar.server.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author faber
 */

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Finds All User ", response = User.class, responseContainer = "List")
    @GetMapping(path = "/users")
    public List<User> getUsers()
    {

        return userRepository.findAll();
    }

    @ApiOperation(value = "Find User by id ", response = User.class, responseContainer = "Object and linked object")
    @GetMapping(path = "/user/{id}")
    public Resource<User> getUserById(@PathVariable Integer id){

        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User id-" +id);

        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).getUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @ApiOperation(value = "Add User ", response = User.class, responseContainer = "Object")
    @PostMapping("/users")
    public ResponseEntity<Object> addUser(@Valid @RequestBody User user){

        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @ApiOperation(value = "Delete User")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }


}
