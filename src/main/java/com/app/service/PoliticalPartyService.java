package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.dto.PoliticalPartyDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Candidate;
import com.app.model.PoliticalParty;
import com.app.repository.CandidateRepository;
import com.app.repository.PoliticalPartyRepository;
import com.app.repository.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class PoliticalPartyService {
    private PoliticalPartyRepository politicalPartyRepository;
    private CandidateRepository candidateRepository;
    private VoteRepository voteRepository;
    private MyModelMapper modelMapper;

    public PoliticalPartyService(PoliticalPartyRepository politicalPartyRepository, CandidateRepository candidateRepository, VoteRepository voteRepository, MyModelMapper modelMapper) {
        this.politicalPartyRepository = politicalPartyRepository;
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
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

    public Map<PoliticalPartyDto, Integer> getAllPoliticalPartiesByVotes() {
        Map<PoliticalPartyDto, Integer> partiesWithVotes = new LinkedHashMap<>();

        try {
            Map<Candidate, Integer> map = voteRepository
                    .findAll()
                    .stream()
                    .collect(Collectors.toMap(
                            e -> candidateRepository.findById(e.getCandidate().getId()).get(),
                            e -> e.getVotes(),
                            (v1, v2) -> v1,
                            () -> new HashMap<>()
                    ));
            for (Map.Entry<Candidate, Integer> entry : map.entrySet()) {
                PoliticalPartyDto politicalPartyDto = modelMapper.fromPoliticalPartyToPoliticalPartyDto(
                        politicalPartyRepository.findById(entry.getKey().getPoliticalParty().getId()).get());
                if (partiesWithVotes.keySet().contains(politicalPartyDto)) {
                    partiesWithVotes.put(politicalPartyDto,
                            partiesWithVotes.get(politicalPartyDto) + entry.getValue());
                } else {
                    partiesWithVotes.put(politicalPartyDto,
                            entry.getValue());
                }
            }
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET ALL POLITICAL PARTIES SORTED BY VOTES: " + e);
        }
        return partiesWithVotes;
    }
}
