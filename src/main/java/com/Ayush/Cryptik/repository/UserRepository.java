package com.Ayush.Cryptik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ayush.Cryptik.entity.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
