package com.gestaovagas.module.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.UUID;

public class TestUtils {

    public static String objectToJson(Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateToken(UUID id) {
        Algorithm algorithm = Algorithm.HMAC256("javagas@123");
        var expiration = Instant.now().plus(Duration.ofMinutes(10));

        var token = JWT.create().withIssuer("Gestao Vagas")
                .withSubject(id.toString())
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withExpiresAt(expiration)
                .sign(algorithm);


        return token;
    }


}
