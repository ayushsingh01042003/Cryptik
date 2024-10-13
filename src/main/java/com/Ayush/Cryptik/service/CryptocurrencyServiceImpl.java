package com.Ayush.Cryptik.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Ayush.Cryptik.entity.Cryptocurrency;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    @Override
    public Cryptocurrency addCryptocurrency(Cryptocurrency cryptocurrency) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCryptocurrency'");
    }

    @Override
    public List<Cryptocurrency> getAllCryptocurrencies() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCryptocurrencies'");
    }

    @Override
    public Cryptocurrency getCryptocurrencyById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCryptocurrencyById'");
    }

    @Override
    public Cryptocurrency updateCryptocurrency(Long id, Cryptocurrency cryptocurrency) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCryptocurrency'");
    }

    @Override
    public void deleteCryptocurrency(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCryptocurrency'");
    }

}
