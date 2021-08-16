package com.example.ecommerce.controller;

import com.example.ecommerce.repository.UserRepository;

import java.util.Optional;

import com.example.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public @ResponseBody Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping(value = "/users")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping(value = "/users/{id}")
    public @ResponseBody ResponseEntity<User> getById(@PathVariable int id) {
        var opt = userRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(opt.get());
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Optional<User> userTemp = userRepository.findById(id);

        if (userTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userTemp.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user){
        Optional<User> userUpdate = userRepository.findById(id);

        if(userUpdate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User savedUser = userUpdate.get();
        savedUser.setAddress(user.getAddress());
        savedUser.setName(user.getName());
        savedUser.setLastname(user.getLastname());
        savedUser.setStartDate(user.getStartDate());
        userRepository.save(savedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
