package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.TokenDto;
import com.app.dto.VoterDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.ExceptionInfo;
import com.app.exceptions.MyException;
import com.app.model.Constituency;
import com.app.model.Token;
import com.app.model.Voter;
import com.app.repository.ConstituencyRepository;
import com.app.repository.TokenRepository;
import com.app.repository.VoterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class VoterService {
    private VoterRepository voterRepository;
    private ConstituencyRepository constituencyRepository;
    private TokenRepository tokenRepository;
    private MyModelMapper modelMapper;

    public VoterService(VoterRepository voterRepository, ConstituencyRepository constituencyRepository, TokenRepository tokenRepository, MyModelMapper modelMapper) {
        this.voterRepository = voterRepository;
        this.constituencyRepository = constituencyRepository;
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    public VoterDto getVoterByToken(TokenDto tokenDto) {
        try {
            TokenDto token = tokenRepository.findAll()
                    .stream()
                    .map(modelMapper::fromTokenToTokenDto)
                    .filter(t -> t.getTokenValue().equals(tokenDto.getTokenValue()))
                    .findFirst()
                    .orElseThrow(NullPointerException::new);

            return voterRepository.findAll()
                    .stream()
                    .filter(voter -> voter.getId().equals(token.getVoterDto().getId()))
                    .map(modelMapper::fromVoterToVoterDto)
                    .findFirst()
                    .orElseThrow(NullPointerException::new);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET VOTER BY TOKEN: " + e);
        }
    }
}
