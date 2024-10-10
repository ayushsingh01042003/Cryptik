package com.Ayush.Cryptik.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Ayush.Cryptik.entity.User;

import com.Ayush.Cryptik.entity.Portfolio;

public interface PortfolioRespository extends JpaRepository<Portfolio, Long> {
    Page<Portfolio> findByUser(User user, Pageable pageable);
}
