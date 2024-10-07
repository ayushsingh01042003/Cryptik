package com.Ayush.Cryptik.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername called with email: " + email);
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        
        System.out.println("User found in loadUserByUsername: " + user.getEmail());
        System.out.println("User roles: " + user.getRoles());
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), 
            getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
    return Arrays.stream(roles.split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
}
}
