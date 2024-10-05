package com.Ayush.Cryptik.service;

import com.Ayush.Cryptik.entity.User;

public interface UserService {
    boolean existsByEmail(String email);
    String addUser(User user);    
}
