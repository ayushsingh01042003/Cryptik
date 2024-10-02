package com.Ayush.Cryptik.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cryptocurrencies")
public class Cryptocurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cryptocurrency_id")
    private Long cryptocurrencyId;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String name;

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "market_cap", nullable = false)
    private Long marketCap;

    @Column(name = "volume_24h_usd")
    private BigDecimal volume24hUsd;

    @CreationTimestamp
    @Column(nullable = false, name = "last_updated")
    private Date lastUpdated;

    //relationships

}
