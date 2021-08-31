package com.example.ecommerce.service;

import java.security.*;
import java.util.*;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.*;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<LoginResponseDto> tryLogin(String email, String password){
        var result = userRepository.findByUsername(email);

        if(result == null || result.getPassword().equals(password)){
            return Optional.ofNullable(null);
        }

        var resp = new LoginResponseDto();
        resp.mapFromEntity(result);

        return Optional.of(resp);
    }

    public Optional<List<UserResponseDto>> getUsersByCity(int cityId){

        return Optional.ofNullable(null);
    }

    public Optional<UserResponseDto> createUser(UserRequestDto dto){
        Role role = dto.role.isBlank() ? Role.GUEST : Role.valueOf(dto.role);
        var user = new User(dto.name, dto.lastname, dto.username, dto.password, role);

        var entity = userRepository.save(user);
        return Optional.ofNullable(new UserResponseDto().mapFromEntity(entity));
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
}
