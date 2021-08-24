package com.example.ecommerce.dto;


public class ProductDto {
    
    private int id;
    private String name;
    private String description;
    private float unitPrice;
    private String category;
    private String inventeryCode;

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setInventeryCode(String inventeryCode) {
        this.inventeryCode = inventeryCode;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public String getInventeryCode() {
        return inventeryCode;
    }

}


