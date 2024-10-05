package com.Ayush.Cryptik.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ayush.Cryptik.dto.UserDTO;
import com.Ayush.Cryptik.dto.UserRegistrationDTO;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.service.JwtService;
import com.Ayush.Cryptik.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    
    @PostMapping("/Signup")
    public ResponseEntity<UserDTO> Signup(@RequestBody UserRegistrationDTO userDTO) throws Exception {
        if(userService.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email already in use");
        }
        User user = userService.addUser(userDTO);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> Login() {
    }

}