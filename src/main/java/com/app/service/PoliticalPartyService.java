package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.PoliticalPartyDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Candidate;
import com.app.model.PoliticalParty;
import com.app.model.Vote;
import com.app.repository.CandidateRepository;
import com.app.repository.PoliticalPartyRepository;
import com.app.repository.VoteRepository;
import com.app.repository.VoterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PoliticalPartyService {
    private PoliticalPartyRepository politicalPartyRepository;
    private VoteRepository voteRepository;
    private VoterRepository voterRepository;
    private MyModelMapper modelMapper;

    public PoliticalPartyService(PoliticalPartyRepository politicalPartyRepository, VoteRepository voteRepository, VoterRepository voterRepository, MyModelMapper modelMapper) {
        this.politicalPartyRepository = politicalPartyRepository;
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
        this.modelMapper = modelMapper;
    }

    public List<PoliticalPartyDto> getAllPoliticalParties() {
        try {
            return politicalPartyRepository
                    .findAll()
                    .stream()
                    .map(modelMapper::fromPoliticalPartyToPoliticalPartyDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET ALL POLITICAL PARTIES: " + e);
        }
    }

    public Map<PoliticalPartyDto, Double> getAllPoliticalPartiesByVotes() {
        try {
            return voteRepository.findMaxVotes()
                    .stream()
                    .collect(Collectors.toMap(
                            v -> modelMapper.fromPoliticalPartyToPoliticalPartyDto(v.getCandidate().getPoliticalParty()),
                            v -> (double) v.getVotes() / voterRepository.findAll().size() * 100,
                            (v1, v2) -> v1,
                            LinkedHashMap::new
                    ));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET ALL POLITICAL PARTIES BY VOTES: " + e);
        }
    }
}
