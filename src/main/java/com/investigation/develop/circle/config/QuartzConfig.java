package com.investigation.develop.circle.config;

import com.investigation.develop.circle.quartz.job.LeaveProposalNotificationJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob().ofType(LeaveProposalNotificationJob.class)
                .storeDurably()
                .withIdentity("Add_proposal_to_dashboard_job")
                .withDescription("Job to sending en email with advice to add at least on proposal to dashboard.")
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {
        final String cronTime = "0 0 9 ? * * *"; //each day at 9.00am
        return TriggerBuilder.newTrigger().forJob(job)
                .withIdentity("Add_proposal_to_dashboard_trigger")
                .withDescription("Trigger for 'Add_proposal_to_dashboard_job'")
                .withSchedule(cronSchedule(cronTime))
//                .withSchedule(simpleSchedule().withRepeatCount(0).withIntervalInSeconds(30))
                .build();
    }

}
