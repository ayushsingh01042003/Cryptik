package com.Ayush.Cryptik.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class CoinGeckoServiceImpl implements CoinGeckoService {

    private final WebClient webClient;

    @Value("${coingecko.api.baseurl}")
    private String coingeckoApiBaseUrl;

    public CoinGeckoServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(coingeckoApiBaseUrl).build();  // Set the base URL here
    }

    @Override
    public Mono<Map<String, BigDecimal>> getRealTimePrices(List<String> symbols) {
        String ids = String.join(",", symbols);
        System.out.println(coingeckoApiBaseUrl);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                    .path("/simple/price")  // Only provide the path, not the full URL
                    .queryParam("ids", ids)
                    .queryParam("vs_currencies", "usd")
                    .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .map(response -> {
                    Map<String, BigDecimal> prices = new HashMap<>();
                    for (String symbol : symbols) {
                        Map<String, BigDecimal> data = (Map<String, BigDecimal>) response.get(symbol.toLowerCase());

                        // Log response for each symbol
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
                    // Log the error if something goes wrong
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

