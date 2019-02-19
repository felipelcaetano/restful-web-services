package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserDaoService service;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @PostMapping
    public User createUser(User user) {
        return service.save(user);
    }

    @GetMapping("/{userId}")
    public User retrieveUser(@PathVariable(name = "userId") int id) {
        return service.findOne(id);
    }
}
