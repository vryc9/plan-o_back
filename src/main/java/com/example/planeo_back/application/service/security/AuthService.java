package com.example.planeo_back.application.service.security;

import com.example.planeo_back.infrastructure.security.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager manager;
    private JwtProvider provider;

    public AuthService(AuthenticationManager manager, JwtProvider provider) {
        this.manager = manager;
        this.provider = provider;
    }

    public String authenticate(String username, String password) {
        Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return provider.generateToken(auth);
    }
}
