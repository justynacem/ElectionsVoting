package com.app.controllers;
import com.app.dto.TokenDto;
import com.app.service.TokenService;
import com.app.service.VoterService;
import com.app.validators.TokenValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/voters")
public class VoterController {
    private VoterService voterService;
    private TokenService tokenService;
    private TokenValidator tokenValidator;

    public VoterController(VoterService voterService, TokenService tokenService, TokenValidator tokenValidator) {
        this.voterService = voterService;
        this.tokenService = tokenService;
        this.tokenValidator = tokenValidator;
    }

    @InitBinder("/one")
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(tokenValidator);
    }

    @GetMapping("/token")
    public String votingGET(Model model) {
        model.addAttribute("token", new TokenDto());
        model.addAttribute("errors", new HashMap<>());
        return "voters/token";
    }

    @PostMapping("/one")
    public String votingPOST(@Valid @ModelAttribute TokenDto tokenDto,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));
            model.addAttribute("token", tokenDto);
            model.addAttribute("errors", errors);
            return "/voters/token";
        }
        tokenService.checkDate();
        tokenService.checkTocken(tokenDto.getTokenValue());
        model.addAttribute("voter", voterService.getVoterByToken(tokenDto));
        tokenService.deleteTocken(tokenDto.getTokenValue());
        return "voters/one";
    }

    @GetMapping("/wrongToken")
    public String wrongToken() {
        return "voters/wrongToken";
    }
}
