package com.gestaovagas.modules.candidate.services;

import com.gestaovagas.exceptions.ItemFoundException;
import com.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<Object> createCandidate(CandidateEntity candidateEntity) {
        try {
            candidateRepository
                    .findByUsernameOrEmail(candidateEntity.getUsername(),
                            candidateEntity.getEmail())
                    .ifPresent((user) -> {
                        throw new ItemFoundException("Usuário ja existe!");
                    });

            //Encodando a senha antes de salvar no banco
            var passwordEncod = passwordEncoder.encode(candidateEntity.getPassword());
            candidateEntity.setPassword(passwordEncod);

            candidateRepository.save(candidateEntity);
            return ResponseEntity.ok().body("Candidato cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
