package com.example.planeo_back.infrastructure.sse;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter subscribe(Long userId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> emitters.remove(userId));
        emitter.onTimeout(() -> emitters.remove(userId));
        emitter.onError(throwable -> emitters.remove(userId));
        emitters.put(userId, emitter);
        return emitter;
    }

    public void send(Long userId, EventName eventName, Object data) {
        SseEmitter emitter = emitters.get(userId);
        if (emitter != null) {
            try {
                emitter.send( SseEmitter.event().name(eventName.label).data(data));
            }
            catch (Exception e) {
                emitters.remove((userId));
                emitter.completeWithError(e);
            }
        }
    }
}
