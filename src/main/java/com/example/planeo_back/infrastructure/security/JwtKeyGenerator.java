package com.example.planeo_back.infrastructure.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class JwtKeyGenerator {
    private final SecretKey secretKey;

    public JwtKeyGenerator() {
        this.secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
