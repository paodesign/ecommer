package com.example.ecommerce.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String address;
    private LocalDateTime startDate = LocalDateTime.now();
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    //@MapsId
    private Basket basket;

    public User(String name, String lastname, String address) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
    }
    public  User(){

    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
    public void setBasket(Basket basket) {
        this.basket = basket;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getLastname() {
        return lastname;
    }    
    public String getAddress() {
        return address;
    }
    public LocalDateTime getStartDate() {
        return startDate;
    }
    // public Basket getBasket() {
    //     return basket;
    // }


}
