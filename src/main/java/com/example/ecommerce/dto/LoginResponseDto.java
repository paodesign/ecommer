package com.example.ecommerce.dto;

import com.example.ecommerce.entity.User;

public class LoginResponseDto {
    public int id;
    public String name;
    public String username;
    public String role;
    public int basket;

    public void mapFromEntity(User u) {
        this.id = u.getId();
        this.name = String.format("%s %s", u.getName(), u.getLastname());
        this.username = u.getUsername();
        this.role = u.getRole().name();
        this.basket = u.getActiveBasket().get().getId();
    }

}
