package com.Ayush.Cryptik.service;

import com.Ayush.Cryptik.entity.User;

public interface UserService {
    User findByEmail(String email);
}
