package com.example.ecommerce.repository;

import java.util.List;

import com.example.ecommerce.entity.CategoryProduct;
import com.example.ecommerce.entity.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByNameLike(String name);
    List<Product> findByCategory(CategoryProduct category);
}
