package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import com.gestaovagas.modules.candidate.usecase.UseCase;
import com.gestaovagas.modules.exception.UserFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private UseCase candidateUseCase;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = candidateUseCase.createCandidate(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}
