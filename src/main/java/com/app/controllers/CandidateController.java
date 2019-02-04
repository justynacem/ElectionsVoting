package com.app.controllers;

import com.app.service.CandidateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/candidates")
public class CandidateController {
    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/{id}")
    public String getCandidatesFromPoliticalParty(@PathVariable Long id, Model model) {
        model.addAttribute("candidates", candidateService.getAllCandidatesByPoliticalPartyId(id));
        return "candidates/all";
    }

    @GetMapping("/vote/{id}")
    public String addVote(@PathVariable Long id) {
        candidateService.addVoteToCandidate(id);
        return "redirect:/results";
    }
}