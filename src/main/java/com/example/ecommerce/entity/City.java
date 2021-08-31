package com.example.ecommerce.entity;

import javax.persistence.*;

import org.springframework.lang.Nullable;

@Entity(name = "Cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Nullable
    private int provincia_id;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
