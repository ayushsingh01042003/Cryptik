package com.Ayush.Cryptik.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ayush.Cryptik.service.CoinGeckoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/cryptocurrencies")
public class CryptocurrencyController {

    private CoinGeckoService coinGeckoService;

    public CryptocurrencyController(CoinGeckoService coinGeckoService) {
        this.coinGeckoService = coinGeckoService;
    }

    @GetMapping("/")
    public String helloString() {
        return new String("Hello");
    }
    

    @GetMapping("/prices")
    public Mono<ResponseEntity<Map<String, Integer>>> getRealTimePrices(@RequestParam List<String> symbols) {
        return coinGeckoService.getRealTimePrices(symbols)
                .map(prices -> new ResponseEntity<>(prices, HttpStatus.OK));
    }
    

}
