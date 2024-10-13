package com.Ayush.Cryptik.service;

import com.Ayush.Cryptik.entity.Cryptocurrency;

import java.util.List;

public interface CryptocurrencyService {
    Cryptocurrency addCryptocurrency(Cryptocurrency cryptocurrency);
    List<Cryptocurrency> getAllCryptocurrencies();
    Cryptocurrency getCryptocurrencyById(Long Id);
    Cryptocurrency updateCryptocurrency(Long id, Cryptocurrency cryptocurrency);
    void deleteCryptocurrency(Long id);
}
