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

    public JobDataMap createJobDataMapWithUserContext() {
        String username = authService.getUsername();
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("username", username);
        return dataMap;
    }

    public JobDataMap createJobDataMapWithUserContextAndExpenseId(Long expenseId) {
        JobDataMap dataMap = createJobDataMapWithUserContext();
        dataMap.put("depenseId", expenseId);
        return dataMap;
    }
}
