package com.Ayush.Cryptik.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioResponseDTO {
    private Long id;
    private String name;
    private Date createdAt;
}
