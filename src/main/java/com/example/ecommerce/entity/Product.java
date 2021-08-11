package com.example.ecommerce.entity;

import java.util.List;
import javax.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private float unitPrice;
    private CategoryProduct category;

    public Product(int id, String name, float unitPrice, CategoryProduct category){
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
    public void setCategory(CategoryProduct category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public float getUnitPrice() {
        return unitPrice;
    }
    public CategoryProduct getCategory() {
        return category;
    }



    @Column(unique = true)
    private String inventeryCode;

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<BasketDetail> details;

}
