package com.example.ecommerce.dto;

import com.example.ecommerce.entity.User;

public class LoginResponseDto {
    public int Id;
    public String Name;
    public String Email;
    public String Role;

    public void mapFromEntity(User u) {
        this.Id = u.getId();
        this.Name = String.format("s% s%", u.getName(), u.getLastname());
        this.Email = u.getEmail();
        this.Role = u.getRole().name();
    }

}
