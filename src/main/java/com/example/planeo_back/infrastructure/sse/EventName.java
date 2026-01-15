package com.example.planeo_back.infrastructure.sse;

public enum EventName{
    UPDATED_EXPENSE("Update expense");

    public final String label;

    EventName(String label) {
        this.label = label;
    }
}
