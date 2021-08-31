package com.example.ecommerce.controller;

import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;
import java.util.Optional;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // public UserController(UserService userService) {
    // this.userService = userService;
    // };

    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponseDto> Login(@RequestBody LoginRequestDto req) {
        var resp = userService.tryLogin(req.Email, req.Password);
        if (!resp.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(resp.get());
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto req) {
        var response = userService.createUser(req);
        
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.ok(response.get());
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<User> getById(@PathVariable int id) {
        var opt = userRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(opt.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Optional<User> userTemp = userRepository.findById(id);

        if (userTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(userTemp.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> userUpdate = userRepository.findById(id);

        if (userUpdate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User savedUser = userUpdate.get();
        savedUser.setName(user.getName());
        savedUser.setLastname(user.getLastname());
        savedUser.setStartDate(user.getStartDate());
        userRepository.save(savedUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
