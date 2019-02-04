package com.app.service;

import com.app.dto.ConstituencyDto;
import com.app.dto.MyModelMapper;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Vote;
import com.app.repository.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConstituencyService {
    private VoteRepository voteRepository;
    private MyModelMapper modelMapper;

    public ConstituencyService(VoteRepository voteRepository, MyModelMapper modelMapper) {
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    public Map<ConstituencyDto, Integer> getAllConstituenciesByVotes() {
        try {

            return voteRepository.findMaxVotes()
                    .stream()
                    .collect(Collectors.toMap(
                            v -> modelMapper.fromConstituencyToConstituencyDto(v.getCandidate().getConstituency()),
                            Vote::getVotes,
                            (v1, v2) -> v1,
                            LinkedHashMap::new
                    ));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "getAllCandidatesByVotes");
        }
    }
}
