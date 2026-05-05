package com.example.planeo_back.infrastructure.context;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestContext {
    public String getUsername() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        if (attributes == null) {
            throw new RuntimeException("No request context available");
        }

        HttpServletRequest request = attributes.getRequest();
        String username = request.getHeader("X-Auth-Username");

        if (username == null) {
            throw new RuntimeException("X-Auth-Username header missing");
        }
        return username;
    }
}
