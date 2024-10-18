package com.Ayush.Cryptik.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;


import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class CoinGeckoServiceImpl implements CoinGeckoService {

    private WebClient webClient;

    public CoinGeckoServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<Map<String, Integer>> getRealTimePrices(List<String> symbols) {
        String ids = String.join(",", symbols);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                    .path("/simple/price")
                    .queryParam("ids", ids)
                    .queryParam("vs_currencies", "usd")
                    .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .map(response -> {
                    Map<String, Integer> prices = new HashMap<>();
                    for (String symbol : symbols) {
                        Map<String, Integer> data = (Map<String, Integer>) response.get(symbol.toLowerCase());

                        if (data == null || data.get("usd") == null) {
                            System.out.println("No data for symbol: " + symbol);
                        } else {
                            System.out.println("Price for " + symbol + ": " + data.get("usd"));
                            prices.put(symbol.toUpperCase(), data.get("usd"));
                        }
                    }
                    return prices;
                })
                .doOnError(error -> {
                    System.err.println("Error fetching prices: " + error.getMessage());
                });
    }

    @Override
    public Mono<Map<String, Object>> getHistoricalData(String symbol, String date) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/coins/{symbol}/history")
                    .queryParam("date", date)
                    .build(symbol))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {});
    }
}

