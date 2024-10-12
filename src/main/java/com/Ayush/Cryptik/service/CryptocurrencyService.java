package com.Ayush.Cryptik.service;

import org.springframework.web.reactive.function.client.WebClient;

import com.Ayush.Cryptik.repository.CryptocurrencyRepository;

public class CryptocurrencyService {
    
    private final CryptocurrencyRepository cryptocurrencyRepository;
    private final WebClient webClient;

    public CryptocurrencyService(CryptocurrencyRepository cryptocurrencyRepository, WebClient.Builder webClientBuilder) {
        this.cryptocurrencyRepository = cryptocurrencyRepository;
        this.webClient = webClientBuilder.baseUrl("https://api.coingecko.com/api/v3").build();
    }

}
