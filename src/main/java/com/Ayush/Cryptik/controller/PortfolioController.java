package com.Ayush.Cryptik.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ayush.Cryptik.dto.PortfolioDTO;
import com.Ayush.Cryptik.dto.PortfolioResponseDTO;
import com.Ayush.Cryptik.entity.Portfolio;
import com.Ayush.Cryptik.entity.User;
import com.Ayush.Cryptik.service.PortfolioService;
import com.Ayush.Cryptik.service.UserService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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

    @GetMapping("/portfolio/{id}")
    public ResponseEntity<PortfolioResponseDTO> getPortfolio(@PathVariable Long id) {
        Portfolio portfolio =  portfolioService.getPortfolioById(id);
        var responseDTO = portfolioService.toPortfolioResponseDTO(portfolio);
        return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
    }

    @GetMapping("/portfolios")
    public ResponseEntity<Page<PortfolioResponseDTO>> getAllPortfolios(Authentication authentication, Pageable pageable) {
        String userEmail = authentication.getName();
        Page<PortfolioResponseDTO> portfolios = portfolioService.getPortfoliosForUser(userEmail, pageable);
        return new ResponseEntity<>(portfolios, HttpStatus.OK);
    }
    
    @DeleteMapping("/portfolio/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        portfolioService.deletePortfolioById(id, userEmail);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}