package com.example.planeo_back.infrastructure.scheduler;

import org.quartz.JobDataMap;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobContextFactory {

    public QuartzJobContextFactory() {
    }

    public JobDataMap createJobDataMapWithUserContextAndExpenseId(Long expenseId, String username) {
        JobDataMap dataMap = new JobDataMap();
        dataMap.put("depenseId", expenseId);
        dataMap.put("username", username);
        return dataMap;
    }
}
