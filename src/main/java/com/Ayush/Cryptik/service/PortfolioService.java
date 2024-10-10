package com.Ayush.Cryptik.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.dto.PortfolioResponseDTO;
import com.Ayush.Cryptik.entity.Portfolio;
import com.Ayush.Cryptik.entity.User;

public interface PortfolioService {
    Portfolio createPortfolio(PortfolioDTO portfolioDTO, User user);
    Portfolio getPortfolioById(Long Id);
    PortfolioResponseDTO toPortfolioResponseDTO(Portfolio portfolio);
    Page<PortfolioResponseDTO> getPortfoliosForUser(String userEmail, Pageable pageable);
    void deletePortfolioById(Long portfolioId, String userEmail);
}