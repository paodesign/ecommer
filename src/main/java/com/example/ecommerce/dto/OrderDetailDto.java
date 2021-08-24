package com.example.ecommerce.dto;


public class OrderDetailDto {

    private int id;
    private int amount;
    private float total;
    private ProductDto  product;
    private String createdOn;
    
    public void setId(int id) {
        this.id = id;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    public void setProduct(ProductDto product) {
        this.product = product;
    }
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    public int getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }
    public float getTotal() {
        return total;
    }
    public ProductDto getProduct() {
        return product;
    }
    public String getCreatedOn() {
        return createdOn;
    }
}
