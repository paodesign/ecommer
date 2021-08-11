package com.example.ecommerce.controller;

import java.util.Optional;

import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.repository.BasketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {
    @Autowired
    private  BasketRepository basketRepository;

    @GetMapping(value = "/basket")
    public @ResponseBody Iterable<Basket> getAll() {
        return basketRepository.findAll();
    }

    @PostMapping(value = "/basket")
    public Basket save(@RequestBody Basket basket) {
        return basketRepository.save(basket);
    }

    @GetMapping(value = "/basket/{id}")
    public @ResponseBody ResponseEntity<Basket> getById(@PathVariable int id) {
        var opt = basketRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(opt.get());
    }

    @DeleteMapping(value = "/basket/{id}")
    public ResponseEntity<?> deleteBasket(@PathVariable int id) {
        Optional<Basket> basketTemp = basketRepository.findById(id);

        if (basketTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basketRepository.delete(basketTemp.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/basket/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Basket basket){
        Optional<Basket> basketUpdate = basketRepository.findById(id);

        if(basketUpdate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Basket savedBasket = basketUpdate.get();
        savedBasket.setStatus(basket.getStatus());
        basketRepository.save(savedBasket);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
