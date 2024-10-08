package com.Ayush.Cryptik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ayush.Cryptik.entity.Portfolio;

public interface PortfolioRespository extends JpaRepository<Portfolio, Long> {
}
