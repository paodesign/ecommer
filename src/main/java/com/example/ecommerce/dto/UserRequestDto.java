package com.example.ecommerce.dto;
import com.example.ecommerce.entity.User;

public class UserRequestDto {

    public int id;
    public String name;
    public String lastname;
    public String username;
    public String role;
    public String password;


    public void mapFromEntity(User u){
        this.id = u.getId();
        this.name = String.format("s% s%", u.getName(), u.getLastname());
        this.username = u.getUsername();

    }

}
