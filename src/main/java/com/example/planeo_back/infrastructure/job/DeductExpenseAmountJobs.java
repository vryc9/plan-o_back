package com.example.planeo_back.infrastructure.job;

import com.example.planeo_back.application.usecase.GetNextExpenseService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class DeductExpenseAmountJobs implements Job {

    private final GetNextExpenseService service;

    public DeductExpenseAmountJobs(GetNextExpenseService service) {
        this.service = service;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        long depenseId = jobExecutionContext.getMergedJobDataMap().getLong("depenseId");
        String username = jobExecutionContext.getMergedJobDataMap().getString("username");
        service.deductAmount(depenseId, username);
    }
}
