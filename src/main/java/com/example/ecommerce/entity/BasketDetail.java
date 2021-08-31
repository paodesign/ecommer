package com.example.ecommerce.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "BasketDetails")
public class BasketDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;
    private float total;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Basket basket;

    @Column(name = "created_on")
    private LocalDate createdOn;

    public BasketDetail() {

    }

    public BasketDetail(Product product, int amount) {
        this.product = product;
        this.amount = amount;
        this.createdOn = LocalDate.now();
        refreshTotal();
    }

    private void refreshTotal(){
        this.total = amount * product.getUnitPrice();
    }

    public void addAmountToOne() {
        this.amount ++;
        refreshTotal();
    }

    public void subsAmountToOne(){
        this.amount --;
        refreshTotal();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public Product getProduct() {
        return product;
    }

    public float getTotal() {
        return total;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
