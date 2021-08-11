package com.example.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate creadLocalDate;
    private BasketStatus status;

    public Basket() {
        
    }

    public Basket(int id, User user, LocalDate creaLocalDate, BasketStatus status) {
        this.id = id;
        this.user = user;
        this.creadLocalDate = creaLocalDate;
        this.status = status;
    }

    public void setStatus(BasketStatus status) {
        this.status = status;
    }
    
    public BasketStatus getStatus() {
        return status;
    }
    
    @OneToMany(
        mappedBy = "basket",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private List<BasketDetail> details;
        
    @OneToOne(
            mappedBy = "basket", 
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, 
            optional = false)    
    private User user;

}