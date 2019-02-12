package com.app.controllers;

import com.app.service.CandidateService;
import com.app.service.ConstituencyService;
import com.app.service.PoliticalPartyService;
import com.app.service.ResultsService;
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
    private ResultsService resultsService;

    public ResultsController(CandidateService candidateService, PoliticalPartyService politicalPartyService, ConstituencyService constituencyService, ResultsService resultsService) {
        this.candidateService = candidateService;
        this.politicalPartyService = politicalPartyService;
        this.constituencyService = constituencyService;
        this.resultsService = resultsService;
    }

    @GetMapping
    public String getResults(Model model) {
        model.addAttribute("candidatesByVotes", candidateService.getAllCandidatesByVotes());
        model.addAttribute("politicalPartiesByVotes", politicalPartyService.getAllPoliticalPartiesByVotes());
        model.addAttribute("constituenciesByVotes", constituencyService.getAllConstituenciesByVotes());
        model.addAttribute("genderStatistics", resultsService.getGenderStatistics());
        model.addAttribute("educationStatistics", resultsService.getEducationStatistics());
        model.addAttribute("ageStatistics", resultsService.getAgeStatistics());
        return "results/all";
    }

}
