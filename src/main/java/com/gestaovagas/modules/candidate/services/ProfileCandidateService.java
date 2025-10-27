package com.gestaovagas.modules.candidate.services;

import com.gestaovagas.modules.candidate.dto.ProfileCandidateDTO;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileCandidateService {


    private final CandidateRepository candidateRepository;

    public ResponseEntity<Object> getCandidate(UUID id) {
        try {
            var candidate = candidateRepository.findById(id)
                    .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));

            var candidateDTO = ProfileCandidateDTO.builder()
                    .email(candidate.getEmail())
                    .name(candidate.getName())
                    .description(candidate.getDescription())
                    .id(candidate.getId())
                    .username(candidate.getUsername())
                    .build();

            return ResponseEntity.ok(candidateDTO);

        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

}
