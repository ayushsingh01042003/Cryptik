package com.Ayush.Cryptik.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.Ayush.Cryptik.dto.CryptocurrencyDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/crytocurrencies")
public class CryptocurrencyController {

    private final WebClient webClient;
    
    public CryptocurrencyController( WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.coingecko.com/api/v3/").build();
    }

    // @GetMapping("/")
    // public ResponseEntity<Page<CryptocurrencyDTO>> getCryptocurrencies(Pageable pageable) {

    //     return new ResponseEntity<>();
    // }
    

}
