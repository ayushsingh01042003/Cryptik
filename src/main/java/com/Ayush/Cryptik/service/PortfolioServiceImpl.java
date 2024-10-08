package com.Ayush.Cryptik.service;

import org.springframework.stereotype.Service;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.entity.Portfolio;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.repository.PortfolioRespository;

import jakarta.transaction.Transactional;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRespository respository;

    public PortfolioServiceImpl(PortfolioRespository respository) {
        this.respository = respository;
    }

    @Override
    @Transactional
    public Portfolio createPortfolio(PortfolioDTO portfolioDTO, User user) {
        var portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setUser(user);
        respository.save(portfolio);
        return portfolio;
    }
    
}