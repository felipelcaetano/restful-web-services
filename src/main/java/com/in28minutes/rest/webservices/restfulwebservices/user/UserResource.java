package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{userId}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{userId}")
    public User retrieveUser(@PathVariable(name = "userId") int id) {

        User user = service.findOne(id);

        if(Objects.isNull(user))
            throw new ResourceNotFoundException("id-" + id);

        Resource<User>

        return user;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable(name = "userId") int id) {

        if(Objects.isNull(service.deleteById(id)))
            throw new ResourceNotFoundException("id-" + id);

        return ResponseEntity.noContent().build();
    }
}
