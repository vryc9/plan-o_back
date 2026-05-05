package com.example.planeo_back.infrastructure.sse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(String username) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> emitters.remove(username));
        emitter.onTimeout(() -> emitters.remove(username));
        emitter.onError(throwable -> emitters.remove(username));
        emitters.put(username, emitter);
        return emitter;
    }

    public void send(String username, EventName eventName, Object data) {
        SseEmitter emitter = emitters.get(username);
        if (emitter != null) {
            try {
                emitter.send( SseEmitter.event().name(eventName.label).data(data));
            }
            catch (Exception e) {
                emitters.remove((username));
                emitter.completeWithError(e);
            }
        }
    }
}
