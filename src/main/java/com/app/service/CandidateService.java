package com.app.service;

import com.app.dto.CandidateDto;
import com.app.dto.MyModelMapper;
import com.app.dto.VoteDto;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Candidate;
import com.app.model.Vote;
import com.app.repository.CandidateRepository;
import com.app.repository.VoteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidateService {
    private CandidateRepository candidateRepository;
    private VoteRepository voteRepository;
    private MyModelMapper modelMapper;

    public CandidateService(CandidateRepository candidateRepository, VoteRepository voteRepository, MyModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
    }

    public List<CandidateDto> getAllCandidatesByPoliticalPartyId(Long id) {
        try {
            return candidateRepository
                    .findAll()
                    .stream()
                    .filter(c -> c.getPoliticalParty().getId().equals(id))
                    .map(modelMapper::fromCandidateToCandidateDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET ALL CANDIDATES BY POLITICAL PARTY: " + e);
        }
    }

    public void addVoteToCandidate(Long id) {
        try {
            Optional<Vote> voteOptional = voteRepository
                    .findAll()
                    .stream()
                    .filter(v -> v.getCandidate().getId().equals(id))
                    .findFirst();
            if (voteOptional.isPresent()) {
                Vote vote = voteOptional.get();
                vote.setVotes(vote.getVotes() + 1);
                voteRepository.save(vote);
            } else {
                Candidate candidate = candidateRepository.findById(id).orElseThrow(NullPointerException::new);
                voteRepository.save(Vote.builder().candidate(candidate).votes(1).build());
            }

        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "ADD VOTE TO CANDIDATE: " + e);
        }
    }

    public Map<CandidateDto, VoteDto> getAllCandidatesByVotes() {
        try {
            Map<Candidate, Vote> map = voteRepository
                    .findAll()
                    .stream()
                    .collect(Collectors.toMap(
                            e -> candidateRepository.findById(e.getCandidate().getId())
                                    .orElseThrow(NullPointerException::new),
                            Function.identity(),
                            (v1, v2) -> v1,
                            () -> new HashMap<>()
                    ));
            return map
                    .entrySet()
                    .stream()
                    .sorted(Comparator.comparing(e -> e.getValue().getVotes(), Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            e -> modelMapper.fromCandidateToCandidateDto(e.getKey()),
                            e -> modelMapper.fromVoteToVoteDto(e.getValue()),
                            (v1, v2) -> v1,
                            () -> new LinkedHashMap<>()
                    ));

        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET ALL CANDIDATES BY VOTES: " + e);
        }
    }
}
