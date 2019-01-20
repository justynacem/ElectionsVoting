package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.TokenDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.ExceptionInfo;
import com.app.exceptions.MyException;
import com.app.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class TokenService {

    private TokenRepository tokenRepository;
    private MyModelMapper modelMapper;

    public TokenService(TokenRepository tokenRepository, MyModelMapper modelMapper) {
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    public String newToken() {
        Random rnd = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            token.append(String.valueOf(rnd.nextInt(9)));
        }
        return token.toString();
    }

    public TokenDto getTokenById(Long id) {
        try {
            if (id == null) {
                throw new NullPointerException("OBJECT IS NULL");
            }
            return tokenRepository
                    .findAll()
                    .stream()
                    .filter(t -> t.getVoter().getId().equals(id))
                    .map(modelMapper::fromTokenToTokenDto)
                    .findFirst()
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET TOKEN BY VOTER ID: " + e);
        }
    }

    public Optional<TokenDto> ckeckTocken(String tokenKey) {
        try {
             return tokenRepository.findAll()
                    .stream()
                    .filter(t -> t.getTokenValue().equals(tokenKey))
                    .map(modelMapper::fromTokenToTokenDto)
                    .findFirst();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "CHECK TOKEN: " + e);
        }
    }
}
