package com.Ayush.Cryptik.service;

import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Ayush.Cryptik.entity.User;

public class CustomUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = List.of(user.getRoles().split(","))
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    
}