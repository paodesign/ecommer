package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EcommerceController {

    
    @GetMapping(value = "/hello")
    public String healthCheck() {
        return "Holaaa";
    }

   
    
}