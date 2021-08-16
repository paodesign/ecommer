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

    public Product(int id, String name, float unitPrice, CategoryProduct category) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public Product() {

    }

    @Column(unique = true)
    private String inventeryCode;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketDetail> details;


    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
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
    public void setInventeryCode(String inventeryCode) {
        this.inventeryCode = inventeryCode;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
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
    public String getInventeryCode() {
        return inventeryCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Product p = (Product)obj;

        return this.inventeryCode == p.inventeryCode;        
    }

}
