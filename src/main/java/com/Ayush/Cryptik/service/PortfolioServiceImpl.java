package com.Ayush.Cryptik.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.dto.PortfolioResponseDTO;
import com.Ayush.Cryptik.entity.Portfolio;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.repository.PortfolioRespository;
import com.Ayush.Cryptik.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    private PortfolioRespository portfolioRepository;
    private UserRepository userRepository;

    public PortfolioServiceImpl(PortfolioRespository portfolioRepository, UserRepository userRepository) {
        this.portfolioRepository = portfolioRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Portfolio createPortfolio(PortfolioDTO portfolioDTO, User user) {
        var portfolio = new Portfolio();
        portfolio.setName(portfolioDTO.getName());
        portfolio.setUser(user);
        portfolioRepository.save(portfolio);
        return portfolio;
    }

    @Override
    public Portfolio getPortfolioById(Long Id) {
        return portfolioRepository
        .findById(Id)
        .orElseThrow(() -> new RuntimeException("Portfolio Id does not exist"));
    }

    @Override
    public PortfolioResponseDTO toPortfolioResponseDTO(Portfolio portfolio) {
        return new PortfolioResponseDTO(
            portfolio.getPortfolioId(), 
            portfolio.getName(),
            portfolio.getCreatedAt()); 
    }

    @Override
    public Page<PortfolioResponseDTO> getPortfoliosForUser(String userEmail, Pageable pageable) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Page<Portfolio> portfolios = portfolioRepository.findByUser(user, pageable);
        return portfolios.map(this::toPortfolioResponseDTO);
    }

    @Override
    public void deletePortfolioById(Long portfolioId, String userEmail) {
        Portfolio portfolio = portfolioRepository.findById(portfolioId)
            .orElseThrow(() -> new RuntimeException("Portfolio not found"));

        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!portfolio.getUser().equals(user)) {
            throw new AccessDeniedException("You don't have permission to delete this portfolio");
        }

        portfolioRepository.delete(portfolio);
    }
    
}