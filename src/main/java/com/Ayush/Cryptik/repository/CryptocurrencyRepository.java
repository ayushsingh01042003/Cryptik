package com.Ayush.Cryptik.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ayush.Cryptik.entity.Cryptocurrency;

public interface CryptocurrencyRepository extends JpaRepository<Cryptocurrency, Long> {
    Optional<Cryptocurrency> findBySymbol(String symbol);
}
