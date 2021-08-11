package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Basket;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Integer>{
    
}
