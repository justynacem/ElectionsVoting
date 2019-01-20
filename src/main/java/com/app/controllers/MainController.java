package com.app.controllers;

import com.app.service.CandidateService;
import com.app.service.PoliticalPartyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
