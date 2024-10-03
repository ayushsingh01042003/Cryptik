package com.Ayush.Cryptik.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "price_history")
public class PriceHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_history_id")
    private Long priceHistoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crytocurrency_id", nullable = false)
    private Cryptocurrency cryptocurrency;

    @Column(name = "price_usd")
    private BigDecimal priceUsd;

    @CreationTimestamp
    @Column(name = "recorder_at")
    private Date recorderAt;

}
