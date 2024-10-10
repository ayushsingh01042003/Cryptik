package com.Ayush.Cryptik.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioResponseDTO {
    private Long id;
    private String name;
    private Date createdAt;
}
