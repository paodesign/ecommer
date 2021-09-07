package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Product;

public class ProductDto {
    
    public int id;
    public String name;
    public String description;
    public float unitPrice;
    public String category;
    public String inventeryCode;

    public void mapFromEntity(Product p){
        this.id = p.getId();
        this.name = p.getName();
        this.description = p.getDescription();
        this.unitPrice = p.getUnitPrice();
        this.category = p.getCategory().name();
        this.inventeryCode = p.getInventeryCode();
    }

}


