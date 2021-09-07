package com.example.ecommerce.entity;

import java.lang.StackWalker.Option;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

@Entity(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastname;
    private LocalDateTime startDate;
    @Column(unique = true)
    private String username;
    private String password;
    private Role role;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contactInfo;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    //@MapsId
    private List<Basket> baskets;

    public User(){
        startDate = LocalDateTime.now();
        this.baskets = new ArrayList<Basket>();
    }

    public User(String name, String lastname, String username, String password, Role role) {
        this();
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.password = saltyPassword(password);
        this.role = role;
    }

    public Role getRole() {
        return role;
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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
  
    public boolean isMatch(String rawPassword) {
        return password.equals(saltyPassword(rawPassword));
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Contact getContact() {
        return contactInfo;
    }

    public void setContact(Contact contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Optional<Basket> getActiveBasket(){
        for (Basket basket : baskets) {
            if (basket.getStatus() == BasketStatus.INPROGRESS){
                return Optional.of(basket);
            }
        }
        return Optional.ofNullable(null);
    }

    private String saltyPassword(String pwd){
        try   
        {
            /* MessageDigest instance for MD5. */  
            MessageDigest m = MessageDigest.getInstance("MD5");  
              
            /* Add plain-text password bytes to digest using MD5 update() method. */  
            m.update(pwd.getBytes());  
              
            /* Convert the hash value into bytes */   
            byte[] bytes = m.digest();  
              
            /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */  
            StringBuilder s = new StringBuilder();  
            for(int i=0; i< bytes.length ;i++)  
            {  
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));  
            }  
              
            /* Complete hashed password in hexadecimal format */  
            pwd = s.toString();  
        }   
        catch (NoSuchAlgorithmException e)   
        {  
            e.printStackTrace();  
        }

        return pwd;
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
        return this.username == u.username;        
    }


    @Override
    public String toString() {
        return name + lastname;
    }

}
