package com.app.dto;

import com.app.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class MyModelMapper {

    public CandidateDto fromCandidateToCandidateDto(Candidate candidate) {
        return candidate == null ? null : CandidateDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .age(candidate.getAge())
                .constituencyDto(candidate.getConstituency() == null ? null :
                        fromConstituencyToConstituencyDto(candidate.getConstituency()))
                .photo(candidate.getPhoto())
                .politicalPartyDto(candidate.getPoliticalParty() == null ? null :
                        fromPoliticalPartyToPoliticalPartyDto(candidate.getPoliticalParty()))
                .build();
    }

    public Candidate fromCandidateDtoToCandidate(CandidateDto candidateDto) {
        return candidateDto == null ? null : Candidate.builder()
                .id(candidateDto.getId())
                .name(candidateDto.getName())
                .surname(candidateDto.getSurname())
                .age(candidateDto.getAge())
                .constituency(candidateDto.getConstituencyDto() == null ? null :
                        fromConstituencyDtoToConstituency(candidateDto.getConstituencyDto()))
                .photo(candidateDto.getPhoto())
                .politicalParty(candidateDto.getPoliticalPartyDto() == null ? null :
                        fromPoliticalPartyDtoToPoliticalParty(candidateDto.getPoliticalPartyDto()))
                .build();
    }

    public ConstituencyDto fromConstituencyToConstituencyDto(Constituency constituency) {
        return constituency == null ? null : ConstituencyDto.builder()
                .id(constituency.getId())
                .name(constituency.getName())
                .build();
    }

    public Constituency fromConstituencyDtoToConstituency(ConstituencyDto constituencyDto) {
        return constituencyDto == null ? null : Constituency.builder()
                .id(constituencyDto.getId())
                .name(constituencyDto.getName())
                .candidates(new HashSet<>())
                .build();
    }

    public PoliticalPartyDto fromPoliticalPartyToPoliticalPartyDto(PoliticalParty politicalParty) {
        return politicalParty == null ? null : PoliticalPartyDto.builder()
                .id(politicalParty.getId())
                .name(politicalParty.getName())
                .description(politicalParty.getDescription())
                .build();
    }

    public PoliticalParty fromPoliticalPartyDtoToPoliticalParty(PoliticalPartyDto politicalPartyDto) {
        return politicalPartyDto == null ? null : PoliticalParty.builder()
                .id(politicalPartyDto.getId())
                .name(politicalPartyDto.getName())
                .candidates(new HashSet<>())
                .description(politicalPartyDto.getDescription())
                .build();
    }

    public VoterDto fromVoterToVoterDto(Voter voter) {
        return voter == null ? null : VoterDto.builder()
                .id(voter.getId())
                .age(voter.getAge())
                .gender(voter.getGender())
                .education(voter.getEducation())
                .build();
    }

    public Voter fromVoterDtoToVoter(VoterDto voterDto) {
        return voterDto == null ? null : Voter.builder()
                .id(voterDto.getId())
                .age(voterDto.getAge())
                .gender(voterDto.getGender())
                .education(voterDto.getEducation())
                .build();
    }

    public TokenDto fromTokenToTokenDto(Token token) {
        return token == null ? null : TokenDto.builder()
                .id(token.getId())
                .expirationDate(token.getExpirationDate())
                .tokenValue(token.getTokenValue())
                .voterDto(token.getVoter() == null ? null :
                        fromVoterToVoterDto(token.getVoter()))
                .build();
    }

    public Token fromTokenDtoToToken(TokenDto tokenDto) {
        return tokenDto == null ? null : Token.builder()
                .id(tokenDto.getId())
                .expirationDate(tokenDto.getExpirationDate())
                .tokenValue(tokenDto.getTokenValue())
                .voter(tokenDto.getVoterDto() == null ? null :
                        fromVoterDtoToVoter(tokenDto.getVoterDto()))
                .build();
    }

    public VoteDto fromVoteToVoteDto(Vote vote) {
        return vote == null ? null : VoteDto.builder()
                .id(vote.getId())
                .candidateDto(vote.getCandidate() == null ? null :
                        fromCandidateToCandidateDto(vote.getCandidate()))
                .votes(vote.getVotes())
                .build();
    }

    public Vote fromVoteDtoToVote(VoteDto voteDto) {
        return voteDto == null ? null : Vote.builder()
                .id(voteDto.getId())
                .candidate(voteDto.getCandidateDto() == null ? null :
                        fromCandidateDtoToCandidate(voteDto.getCandidateDto()))
                .votes(voteDto.getVotes())
                .build();
    }
}
