package com.Ayush.Cryptik.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.dto.PortfolioResponseDTO;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.service.PortfolioService;
import com.Ayush.Cryptik.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    
    private PortfolioService portfolioService;
    private UserService userService;

    public PortfolioController(PortfolioService portfolioService, UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    @PostMapping("/portfolio")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PortfolioResponseDTO> createPortfolio(@Valid @RequestBody PortfolioDTO portfolioDTO, Authentication authentication) {
        String userEmail = authentication.getName();
        User currUser = userService.findByEmail(userEmail);
        
        var portfolio = portfolioService.createPortfolio(portfolioDTO, currUser);
        PortfolioResponseDTO dto = new PortfolioResponseDTO(
            portfolio.getPortfolioId(), 
            portfolioDTO.getName(),
            portfolio.getCreatedAt());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}