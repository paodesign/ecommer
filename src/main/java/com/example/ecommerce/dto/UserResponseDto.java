package com.example.ecommerce.dto;
import com.example.ecommerce.entity.*;

public class UserResponseDto {

    public int id;
    public String Name;
    public String address;
    public String psername;
    public String role;
    public String password;
    public String city;


    public UserResponseDto mapFromEntity(User u){
        this.id = u.getId();
        this.Name = String.format("%s %s", u.getName(), u.getLastname());
        this.psername = u.getUsername();
        this.role = u.getRole().name();
        if (u.getContact() != null) {
            this.address = u.getContact().getAddress();
            this.city = u.getContact().getCity();            
        }
        return this;
    }

}
