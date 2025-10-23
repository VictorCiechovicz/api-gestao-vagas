package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.CandidateEntity;
import com.gestaovagas.modules.candidate.services.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {


    private final CandidateService candidateService;


    @PostMapping("/create")
    public void create(@Valid @RequestBody CandidateEntity candidateEntity) {
        candidateService.createCandidate(candidateEntity);

    }


}
