package com.Ayush.Cryptik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ayush.Cryptik.dto.JwtAuthenticationResponse;
import com.Ayush.Cryptik.dto.LoginRequest;
import com.Ayush.Cryptik.dto.UserDTO;
import com.Ayush.Cryptik.dto.UserRegistrationDTO;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.mappers.UserMapper;
import com.Ayush.Cryptik.service.CustomUserDetailsService;
import com.Ayush.Cryptik.service.JwtService;
import com.Ayush.Cryptik.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.Exception;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    CustomUserDetailsService userDetailsService;

    private UserService userService;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;
    private UserMapper userMapper;

    public UserController(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, UserMapper userMapper) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }
    
    @PostMapping("/Signup")
    public ResponseEntity<UserDTO> Signup(@RequestBody UserRegistrationDTO userDTO) throws Exception {
        if(userService.existsByEmail(userDTO.getEmail())) {
            throw new Exception("Email already in use");
        }
        User user = userService.addUser(userMapper.toEntity(userDTO));
        UserDTO createdUserDTO = userMapper.toDTO(user);
        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/Login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Attempting to authenticate user: " + loginRequest.getEmail());
            
            // Check if the user exists
            var userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            System.out.println("User found: " + userDetails.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            
            System.out.println("Authentication successful");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateToken(loginRequest.getEmail());
            
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        } catch (BadCredentialsException e) {
            System.out.println("Authentication failed: Bad credentials");
            return ResponseEntity.badRequest().body("Invalid email or password");
        } catch(Exception e) {
            System.out.println("Authentication failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body("An error occurred during authentication");
        }
    }
}