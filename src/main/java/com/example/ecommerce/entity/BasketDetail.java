package com.example.ecommerce.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class BasketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    private float total;

    public BasketDetail(int id,int amount, float total){
        this.id = id;
        this.amount = amount;
        this.total = total;
    }

   
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setTotal(float total) {
        this.total = total;
    }
    
    public int getAmount() {
        return amount;
    }
    public float getTotal() {
        return total;
    }
    

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    @Column(name = "created_on")
    private LocalDate createdOn = LocalDate.now();

    

}
