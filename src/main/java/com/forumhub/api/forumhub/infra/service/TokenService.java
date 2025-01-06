package com.forumhub.api.forumhub.infra.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forumhub.api.forumhub.domain.usuario.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    //    TODO: replace secret from JWT token gen
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    public String gerarToken(String email) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer("ForumHub API")
                    .withSubject(email)
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("erro ao gerar token jwt", exception);
        }
    }

    public Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubjectFromJwtToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY)).withIssuer("ForumHub API").build().verify(token).getSubject();
    }

    public String extractJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }

    public boolean validateJwtToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(com.auth0.jwt.algorithms.Algorithm.HMAC256(SECRET_KEY))
                    .build();
            verifier.verify(token); // This will throw an exception if the token is invalid
            return true;
        } catch (JWTVerificationException e) {
            // Handle token verification failure (e.g., expired, invalid, etc.)
            return false;
        }
    }
}
