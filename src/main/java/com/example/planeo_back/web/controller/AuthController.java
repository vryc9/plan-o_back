package com.example.planeo_back.web.controller;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.web.DTO.auth.JwtResponseDTO;
import com.example.planeo_back.web.DTO.auth.LoginRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody LoginRequestDTO login) {
        String token = authService.authenticate(login.getUsername(), login.getPassword());
        return ResponseEntity.ok(new JwtResponseDTO(token));
    }
}
