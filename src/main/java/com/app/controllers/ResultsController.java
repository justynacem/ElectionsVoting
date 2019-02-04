package com.app.controllers;

import com.app.service.CandidateService;
import com.app.service.ConstituencyService;
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
    private ConstituencyService constituencyService;

    public ResultsController(CandidateService candidateService, PoliticalPartyService politicalPartyService, ConstituencyService constituencyService) {
        this.candidateService = candidateService;
        this.politicalPartyService = politicalPartyService;
        this.constituencyService = constituencyService;
    }

    @GetMapping
    public String getResults(Model model) {
        model.addAttribute("candidatesByVotes", candidateService.getAllCandidatesByVotes());
        model.addAttribute("politicalPartiesByVotes", politicalPartyService.getAllPoliticalPartiesByVotes());
        model.addAttribute("constituenciesByVotes", constituencyService.getAllConstituenciesByVotes());
        return "results/all";
    }

}
