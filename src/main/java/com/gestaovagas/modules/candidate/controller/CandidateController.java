package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @PostMapping("/create")
    public String create(@RequestBody CandidateEntity candidateEntity) {
        candidateRepository.save(candidateEntity);

        return "Candidate created";
    }
}
