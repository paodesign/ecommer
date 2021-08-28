package com.example.ecommerce.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import org.springframework.lang.Nullable;
import javax.persistence.*;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private String address;
    private LocalDateTime startDate;
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;
    @Nullable
    private int city_id;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@MapsId
    private List<Basket> baskets;

    public User(){
        startDate = LocalDateTime.now();
        this.baskets = new ArrayList<Basket>();
    }

    public User(String name, String lastname, String address) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.address = address;
    }

    public Role getRole() {
        return role;
    }
    public String getEmail() {
        return email;
    }
    public void setRole(Role role) {
        this.role = role;
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
  
    public String getPassword() {
        return password;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        User u = (User)obj;
        return this.email == u.email;        
    }


    @Override
    public String toString() {
        return name + lastname;
    }

}
