package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.TokenDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.ExceptionInfo;
import com.app.exceptions.MyException;
import com.app.model.Token;
import com.app.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class TokenService {

    private TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void checkDate() {
        try {
            tokenRepository.findTokensByExpirationDateBefore(LocalDateTime.now())
                    .forEach(tokenRepository::delete);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.TOKEN, "CHECK TOKEN: " + e);
        }
    }

    public void checkTocken(String tokenValue) {
        try {
            tokenRepository.findAll()
                    .stream()
                    .filter(t -> t.getTokenValue().equals(tokenValue))
                    .findFirst().orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.TOKEN, "CHECK TOKEN: " + e);
        }
    }

    public void deleteTocken(String tokenValue) {
        try {
            tokenRepository.delete(tokenRepository.findTokenByTokenValue(tokenValue)
                    .orElseThrow(NullPointerException::new));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.TOKEN, "CHECK TOKEN: " + e);
        }
    }
}
