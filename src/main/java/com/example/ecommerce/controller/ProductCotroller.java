package com.example.ecommerce.controller;

import java.util.Optional;

import com.example.ecommerce.entity.CategoryProduct;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;

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
public class ProductCotroller {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value = "/products")
    public @ResponseBody Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @PostMapping(value = "/products")
    public Product save(@RequestBody Product producto) {
        return productRepository.save(producto);
    }

    @GetMapping(value = "/products/{id}")
    public @ResponseBody ResponseEntity<Product> getById(@PathVariable int id) {
        var opt = productRepository.findById(id);

        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(opt.get());
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        Optional<Product> productTemp = productRepository.findById(id);

        if (productTemp.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.delete(productTemp.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody Product product){
        Optional<Product> productUpdate = productRepository.findById(id);

        if(productUpdate.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Product savedProduct = productUpdate.get();
        savedProduct.setName(product.getName());
        savedProduct.setUnitPrice(product.getUnitPrice());
        savedProduct.setCategory(product.getCategory());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setInventeryCode(product.getInventeryCode());
        productRepository.save(savedProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/products/search/{name}")
    public @ResponseBody Iterable<Product> getAllByName(@PathVariable String name){
        Iterable<Product> products = productRepository.findByNameLike(name);
        return products;
    }

    @GetMapping(value = "/products/category/{category}")
    public @ResponseBody Iterable<Product> getAllByCategory(@PathVariable int category){
        Iterable<Product> products = productRepository.findByCategory(CategoryProduct.values()[category]);
        return products;
    }
}
    

