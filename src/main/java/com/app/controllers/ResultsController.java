package com.app.controllers;

import com.app.service.CandidateService;
import com.app.service.PoliticalPartyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class ResultsController {
    private CandidateService candidateService;
    private PoliticalPartyService politicalPartyService;

    public ResultsController(CandidateService candidateService, PoliticalPartyService politicalPartyService) {
        this.candidateService = candidateService;
        this.politicalPartyService = politicalPartyService;
    }

    @GetMapping
    public String getResults(Model model) {
        model.addAttribute("candidatesByVotes", candidateService.getAllCandidatesByVotes());
        model.addAttribute("politicalPartiesByVotes", politicalPartyService.getAllPoliticalPartiesByVotes());
        return "results/all";
    }
}
