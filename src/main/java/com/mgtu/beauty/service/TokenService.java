package com.mgtu.beauty.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    @Value("${auth.jwt.secret}")
    private String secretKey = "oIl6/4nURBUCp7sR40hCUEO82z0VN4CAFbYix6RiReE=";

    public String generateToken(String phone) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        Instant now = Instant.now();
        Instant exp = now.plus(100, ChronoUnit.DAYS);

        return JWT.create()
                .withIssuer("auth-service")
                .withAudience("bookstore")
                .withSubject(phone)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(exp))
                .sign(algorithm);
    }

    public String getPhoneFromToken(String token) {
        return JWT.require(Algorithm.HMAC256(secretKey)).build().verify(token).getSubject();
    }

    public boolean checkToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            if (!decodedJWT.getIssuer().equals("auth-service")) {
                return false;
            }

            if (!decodedJWT.getAudience().contains("bookstore")) {
                return false;
            }
        } catch (JWTVerificationException e) {
            return false;
        }

        return true;
    }

}

