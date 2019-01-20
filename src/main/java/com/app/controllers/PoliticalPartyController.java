package com.app.controllers;

import com.app.service.PoliticalPartyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/politicalParties")
public class PoliticalPartyController {
    private PoliticalPartyService politicalPartyService;

    public PoliticalPartyController(PoliticalPartyService politicalPartyService) {
        this.politicalPartyService = politicalPartyService;
    }

    @GetMapping
    public String getAllPoliticalParties(Model model) {
        model.addAttribute("politicalParties", politicalPartyService.getAllPoliticalParties());
        return "politicalParties/all";
    }
}