package com.gestaovagas.modules.candidate.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gestaovagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.gestaovagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.gestaovagas.modules.candidate.repository.CandidateRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthCandidateService {


    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${secury.token.secret.candidate}")
    private String secretKey;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO dto) {
        var candidate = candidateRepository.findByUsername(dto.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Username/password incorrect");
                });

        var passwordMatch = passwordEncoder.matches(dto.password(), candidate.getPassword());

        if (!passwordMatch) {
            throw new BadCredentialsException("Senha inválida");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create().withIssuer("Gestao Vagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofMinutes(10)))
                .sign(algorithm);


        var authCandidateReponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .build();


        return authCandidateReponse;


    }
}
