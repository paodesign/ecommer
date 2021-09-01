package com.example.ecommerce.service;

import java.util.*;
import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.*;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<LoginResponseDto> tryLogin(String username, String password){
        var result = userRepository.findByUsername(username);

        if(result == null || !result.isMatch(password)){
            return Optional.ofNullable(null);
        }

        var resp = new LoginResponseDto();
        resp.mapFromEntity(result);

        return Optional.of(resp);
    }

    public Optional<List<UserResponseDto>> getUsersByCity(int cityId){

        return Optional.ofNullable(null);
    }

    public Optional<UserResponseDto> registerUser(UserRequestDto dto){
        Role role = dto.role.isBlank() ? Role.GUEST : Role.valueOf(dto.role);
        var user = new User(dto.name, dto.lastname, dto.username, dto.password, role);

        var entity = userRepository.save(user);
        return Optional.ofNullable(new UserResponseDto().mapFromEntity(entity));
    }
}
