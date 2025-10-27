package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.gestaovagas.modules.candidate.services.AuthCandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthCandidateController {

    private final AuthCandidateService authCandidateService;

    @PostMapping("/candidate")
    public ResponseEntity<Object> create(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var result = authCandidateService.execute(authCandidateRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
}




