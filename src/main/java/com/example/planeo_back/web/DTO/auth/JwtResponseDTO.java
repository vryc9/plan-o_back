package com.example.planeo_back.web.DTO.auth;

public class JwtResponseDTO {
    private String token;
    public JwtResponseDTO(String token) { this.token = token; }

    public String getToken() {
        return token;
    }
}
