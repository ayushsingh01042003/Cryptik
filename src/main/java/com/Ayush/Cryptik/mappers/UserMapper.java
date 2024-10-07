package com.Ayush.Cryptik.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.Ayush.Cryptik.dto.UserDTO;
import com.Ayush.Cryptik.dto.UserRegistrationDTO;
import com.Ayush.Cryptik.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles());
        dto.setCreatedAt(user.getCreatedAt());

        return dto;
    }

    public User toEntity(UserRegistrationDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(userDTO.getRoles());

        return user;
    }

}
