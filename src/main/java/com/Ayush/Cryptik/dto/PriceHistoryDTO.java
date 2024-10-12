package com.Ayush.Cryptik.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class PriceHistoryDTO {
    private Long priceHistoryId;
    private Long cryptocurrencyId;
    private Date timestamp;
    private BigDecimal price;
}