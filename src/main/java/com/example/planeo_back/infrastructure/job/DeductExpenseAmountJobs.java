package com.example.planeo_back.infrastructure.job;

import com.example.planeo_back.application.service.security.AuthService;
import com.example.planeo_back.application.usecase.GetNextExpenseService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class DeductExpenseAmountJobs implements Job {

    private final GetNextExpenseService service;
    private final AuthService authService;

    public DeductExpenseAmountJobs(GetNextExpenseService service, AuthService authService) {
        this.service = service;
        this.authService = authService;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long depenseId = jobExecutionContext.getMergedJobDataMap().getLong("depenseId");
        String username = jobExecutionContext.getMergedJobDataMap().getString("username");
        service.deductAmount(depenseId, username);
    }
}
