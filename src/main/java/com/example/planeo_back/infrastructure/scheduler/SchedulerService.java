package com.example.planeo_back.infrastructure.scheduler;

import com.example.planeo_back.domain.entity.Expense;
import com.example.planeo_back.infrastructure.job.DeductExpenseAmountJobs;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SchedulerService {

    private final Scheduler scheduler;
    private final QuartzJobContextFactory quartzJobContextFactory;

    public SchedulerService(Scheduler scheduler, QuartzJobContextFactory quartzJobContextFactory) {
        this.scheduler = scheduler;
        this.quartzJobContextFactory = quartzJobContextFactory;
    }

    public void sheduleJobs(Expense expense, String username) throws SchedulerException {
        JobDataMap dataMap = quartzJobContextFactory.createJobDataMapWithUserContextAndExpenseId(expense.getId(), username);

        JobDetail jobDetail = JobBuilder.newJob(DeductExpenseAmountJobs.class)
                .withIdentity("depenseJob_" + expense.getId(), "depense-jobs")
                .usingJobData(dataMap)
                .build();

        Instant scheduledTime = expense.getDate()
                .toInstant()
                .atZone(ZoneId.of("Europe/Paris"))
                .toLocalDate()
                .atStartOfDay(ZoneId.of("Europe/Paris"))
                .toInstant();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("depenseTrigger_" + expense.getId(), "depense-triggers")
                .startAt(Date.from(scheduledTime))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println(">>> Job registered in Quartz, next fire: " + trigger.getNextFireTime());
    }
}
