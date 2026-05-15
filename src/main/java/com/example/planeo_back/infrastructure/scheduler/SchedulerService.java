package com.example.planeo_back.infrastructure.scheduler;

import com.example.planeo_back.domain.models.expense.ExpenseDomain;
import com.example.planeo_back.infrastructure.adapter.repository.entity.Expense;
import com.example.planeo_back.infrastructure.job.DeductExpenseAmountJobs;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
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

    public void sheduleJobs(ExpenseDomain expense, String username) throws SchedulerException {
        JobDataMap dataMap = quartzJobContextFactory.createJobDataMapWithUserContextAndExpenseId(expense.id(), username);

        JobDetail jobDetail = JobBuilder.newJob(DeductExpenseAmountJobs.class)
                .withIdentity("depenseJob_" + expense.id(), "depense-jobs")
                .usingJobData(dataMap)
                .build();

        Instant scheduledTime = expense.date()
                .toInstant()
                .atZone(ZoneId.of("Europe/Paris"))
                .toLocalDate()
                .atStartOfDay(ZoneId.of("Europe/Paris"))
                .toInstant();

        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("depenseTrigger_" + expense.id(), "depense-triggers")
                .startAt(Date.from(scheduledTime))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println(">>> Job registered in Quartz, next fire: " + trigger.getNextFireTime());
    }
}
