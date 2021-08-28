package com.example.ecommerce.dto;
import com.example.ecommerce.entity.User;

public class UserDto {
    public int id;
    public String name;
    public String address;
    public String startDate;
    public String email;
    public String role;
    public String city;

    public void mapFromEntity(User u, String city){
        id = u.getId();
        name = String.format("s% s%", u.getName(), u.getLastname());
        address = u.getAddress();
        this.city = city;
        this.email = u.getEmail();
        this.role = u.getRole().name();
    }

}
