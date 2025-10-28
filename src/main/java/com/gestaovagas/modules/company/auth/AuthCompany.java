package com.gestaovagas.modules.company.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gestaovagas.modules.company.dto.AuthCompanyDTO;
import com.gestaovagas.modules.company.dto.AuthCompanyResponseDTO;
import com.gestaovagas.modules.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

import javax.naming.AuthenticationException;

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
public class AuthCompany {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO dto) {
        var company = companyRepository.findByUsername(dto.getUsername()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Username not found");
                }
        );

        //Verificar se senha é igual
        var match = passwordEncoder.matches(dto.getPassword(), company.getPassword());

        if (!match) {
            throw new BadCredentialsException("Senha inválida");
        }
//se for igual gera um JWT token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expirationIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create().withIssuer("Gestao Vagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(expirationIn)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .sign(algorithm);


        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expirationIn.toEpochMilli())
                .build();


        return authCompanyResponseDTO;

    }
}
