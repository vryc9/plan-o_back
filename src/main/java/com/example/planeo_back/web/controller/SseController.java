package com.example.planeo_back.web.controller;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.infrastructure.sse.SseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

    private final SseService sseService;
    private final AuthService authService;

    public SseController(SseService sseService, AuthService authService) {
        this.sseService = sseService;
        this.authService = authService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe() {
        return ResponseEntity.status(HttpStatus.OK).body(sseService.subscribe(authService.getUsername()));
    }
}
