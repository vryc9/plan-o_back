package com.example.planeo_back.infrastructure.scheduler;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.infrastructure.job.DeductExpenseAmountJobs;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class SchedulerService {

    private final Scheduler scheduler;
    private final QuartzJobContextFactory quartzJobContextFactory;

    public SchedulerService(Scheduler scheduler, QuartzJobContextFactory quartzJobContextFactory) {
        this.scheduler = scheduler;
        this.quartzJobContextFactory = quartzJobContextFactory;
    }

    public void sheduleJobs(Expense expense, Long userId) throws SchedulerException {
        JobDataMap dataMap = quartzJobContextFactory.createJobDataMapWithUserContextAndExpenseId(expense.getId(), userId);

        JobDetail jobDetail = JobBuilder.newJob(DeductExpenseAmountJobs.class)
                .withIdentity("depenseJob_" + expense.getId(), "depense-jobs")
                .usingJobData(dataMap)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("depenseTrigger_" + expense.getId(), "depense-triggers")
                .startAt(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)))//TODO à remplacer par la date de la dépense
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
