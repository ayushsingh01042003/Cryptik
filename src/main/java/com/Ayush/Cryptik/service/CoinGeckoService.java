package com.Ayush.Cryptik.service;

import java.util.*;
import java.math.BigDecimal;

import reactor.core.publisher.Mono;

public interface CoinGeckoService {
    Mono<Map<String, BigDecimal>> getRealTimePrices(List<String> symbols);
    Mono<Map<String, Object>> getHistoricalData(String symbol, String date);
}
