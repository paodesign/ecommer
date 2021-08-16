package com.example.ecommerce.repository;

import java.util.Optional;

import com.example.ecommerce.entity.Basket;
import com.example.ecommerce.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer>{
    Optional<Basket> findByUser(User user);
}
