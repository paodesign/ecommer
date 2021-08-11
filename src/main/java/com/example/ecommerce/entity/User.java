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
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }
    


}
