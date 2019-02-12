package com.app.service;

import com.app.dto.CandidateDto;
import com.app.dto.MyModelMapper;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Candidate;
import com.app.model.Vote;
import com.app.repository.CandidateRepository;
import com.app.repository.VoteRepository;
import com.app.repository.VoterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CandidateService {
    private CandidateRepository candidateRepository;
    private VoteRepository voteRepository;
    private VoterRepository voterRepository;
    private MyModelMapper modelMapper;

    public CandidateService(CandidateRepository candidateRepository, VoteRepository voteRepository, VoterRepository voterRepository, MyModelMapper modelMapper) {
        this.candidateRepository = candidateRepository;
        this.voteRepository = voteRepository;
        this.voterRepository = voterRepository;
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

    public Map<CandidateDto, Double> getAllCandidatesByVotes() {
        try {
            return voteRepository.findMaxVotes()
                    .stream()
                    .collect(Collectors.toMap(
                    v -> modelMapper.fromCandidateToCandidateDto(v.getCandidate()),
                    v -> (double) v.getVotes() / voterRepository.findAll().size() * 100,
                    (v1, v2) -> v1,
                    LinkedHashMap::new
            ));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "getAllCandidatesByVotes");
        }
    }
}
