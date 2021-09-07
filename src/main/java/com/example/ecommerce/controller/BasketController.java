package com.example.ecommerce.controller;

import java.util.Optional;

import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.BasketRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private  BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping(value = "/user/{userId}")
    public ResponseEntity<Basket> save(@PathVariable int userId ) {
        Optional<User> userTemp = userRepository.findById(userId);
        if(userTemp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Basket basket = new Basket(userTemp.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(basketRepository.save(basket));
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Basket> getById(@PathVariable int id) {
        var opt = basketRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        var basket = opt.get();
        return ResponseEntity.status(HttpStatus.OK).body(basket);

        //return ResponseEntity.ok(basket);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteBasket(@PathVariable int id) {
        Optional<Basket> basketTemp = basketRepository.findById(id);

        if (basketTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        basketRepository.delete(basketTemp.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/{id}")
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

    @PostMapping(value = "/{id}/products/{prodId}")
    public ResponseEntity<Basket> addProductToBasket(@PathVariable int id, @PathVariable int prodId){
        Optional<Product> product = productRepository.findById(prodId);
        Optional<Basket> basket = basketRepository.findById(id);

        if(product.isEmpty() || basket.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Basket newBasket = basket.get();
        newBasket.addProduct(product.get());
        newBasket = basketRepository.save(newBasket);

        return ResponseEntity.status(HttpStatus.CREATED).body(newBasket);
    }

    @DeleteMapping("/{id}/products/{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id, @PathVariable int prodId) {
        Optional<Basket> basketTemp = basketRepository.findById(id);
        Boolean existProduct = basketTemp.get().existProduct(prodId);
        if(basketTemp.isEmpty() || existProduct == false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Basket newBasket = basketTemp.get();
        newBasket.deleteProduct(prodId);
        newBasket = basketRepository.save(newBasket);

        return new ResponseEntity<>(HttpStatus.OK);
        
    }

}
