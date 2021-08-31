package com.example.ecommerce.dto;
import com.example.ecommerce.entity.*;

public class UserResponseDto {

    public int Id;
    public String Name;
    public String Address;
    public String Username;
    public String Role;
    public String Password;
    public String City;


    public UserResponseDto mapFromEntity(User u){
        this.Id = u.getId();
        this.Name = String.format("s% s%", u.getName(), u.getLastname());
        this.Username = u.getUsername();
        this.Role = u.getRole().name();
        if (u.getContact() != null) {
            this.Address = u.getContact().getAddress();
            this.City = u.getContact().getCity();            
        }
        return this;
    }

}
