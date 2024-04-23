package com.serbianAPI.controller;

import com.serbianAPI.dao.entity.User;
import com.serbianAPI.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(value = "/new-user", produces = "application/json")
    public ResponseEntity<?> createUser(@RequestBody User user){
        User newUser = userRepository.save(user);
        return ResponseEntity.created(URI.create("/users")).build();
    }

}
