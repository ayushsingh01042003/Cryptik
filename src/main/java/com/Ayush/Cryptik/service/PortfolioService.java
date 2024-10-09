package com.Ayush.Cryptik.service;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.dto.PortfolioResponseDTO;
import com.Ayush.Cryptik.entity.Portfolio;
import com.Ayush.Cryptik.entity.User;

public interface PortfolioService {
    Portfolio createPortfolio(PortfolioDTO portfolioDTO, User user);
    Portfolio getPortfolioById(Long Id);
    PortfolioResponseDTO toPortfolioResponseDTO(Portfolio portfolio);
}