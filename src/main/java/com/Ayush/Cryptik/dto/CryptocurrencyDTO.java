package com.Ayush.Cryptik.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class CryptocurrencyDTO {
    private String cryptocurrencyId;
    private String symbol;
    private String name;
    private BigDecimal currentPrice;
    private Long marketCap;
    private BigDecimal volume24hUsd;
    private Date lastUpdated;
}