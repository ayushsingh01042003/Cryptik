package com.Ayush.Cryptik.service;

import java.util.*;

import reactor.core.publisher.Mono;

public interface CoinGeckoService {
    Mono<Map<String, Integer>> getRealTimePrices(List<String> symbols);
    Mono<Map<String, Object>> getHistoricalData(String symbol, String date);
}
