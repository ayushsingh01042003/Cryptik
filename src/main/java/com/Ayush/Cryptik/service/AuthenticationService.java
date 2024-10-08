package com.Ayush.Cryptik.service;

import com.Ayush.Cryptik.dto.LoginUserDTO;
import com.Ayush.Cryptik.dto.UserRegistrationDTO;
import com.Ayush.Cryptik.entity.User;

public interface AuthenticationService {
    User authenticate(LoginUserDTO input);
    User signup(UserRegistrationDTO input);
}
