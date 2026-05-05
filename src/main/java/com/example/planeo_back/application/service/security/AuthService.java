package com.example.planeo_back.application.service.security;

import com.example.planeo_back.infrastructure.context.RequestContext;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final RequestContext requestContext;

    public AuthService(RequestContext requestContext) {
        this.requestContext = requestContext;
    }

    public String getUsername() {
        return requestContext.getUsername();
    }
}
