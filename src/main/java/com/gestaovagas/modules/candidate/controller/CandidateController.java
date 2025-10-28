package com.gestaovagas.modules.candidate.controller;

import com.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.gestaovagas.modules.candidate.services.CandidateService;
import com.gestaovagas.modules.candidate.services.ProfileCandidateService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;
    private final ProfileCandidateService profileCandidateService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCandidate(@Valid @RequestBody CandidateEntity candidateEntity) {
        return candidateService.createCandidate(candidateEntity);

    }


    @GetMapping("/get")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> getCandidate(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        return profileCandidateService.getCandidate(UUID.fromString(candidateId.toString()));
    }


}
