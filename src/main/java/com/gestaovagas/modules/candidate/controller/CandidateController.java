package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import com.gestaovagas.modules.exception.ItemFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            candidateRepository
                    .findByUsernameOrEmail(candidateEntity.getUsername(),
                            candidateEntity.getEmail())
                    .ifPresent((user) -> {
                        throw new ItemFoundException("Usuário ja existe!");
                    });
            candidateRepository.save(candidateEntity);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }


}
