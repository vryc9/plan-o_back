package com.example.planeo_back.infrastructure.scheduler;

import com.example.planeo_back.application.service.security.AuthService;
import org.quartz.JobDataMap;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobContextFactory {
    private final AuthService authService;

    public QuartzJobContextFactory(AuthService authService) {
        this.authService = authService;
    }

    public JobDataMap createJobDataMapWithUserContext(Long userId) {
        String username = authService.getUsername();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("username", username);
        dataMap.put("userId", userId);
        return dataMap;
    }

    public JobDataMap createJobDataMapWithUserContextAndExpenseId(Long expenseId, Long userId) {
        JobDataMap dataMap = createJobDataMapWithUserContext(userId);
        dataMap.put("depenseId", expenseId);
        return dataMap;
    }
}
