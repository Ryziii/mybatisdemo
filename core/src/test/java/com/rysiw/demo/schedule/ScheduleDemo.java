package com.rysiw.demo.schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Rysiw
 * @date 2022/9/2 14:39
 */
public class ScheduleDemo {

    public static void main(String[] args) throws SchedulerException {
        quartzDemo();
    }

    public static void timerDemo(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("this is Timer Schedule");
            }
        }, new Date(), 2000);
    }

    public static void scheduleExecutorServiceDemo(){
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10);
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            System.out.println("this is ScheduleExecutorService demo");
            System.out.println("fds");
        }, 2, 2, TimeUnit.SECONDS);
    }

    public static void quartzDemo() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobBuilder jobBuilder = JobBuilder.newJob(MyJob.class);
        jobBuilder.withIdentity("jobName","jobGroupName");
        JobDetail jobDetail = jobBuilder.build();
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("triggerName", "triggerGroupName")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
    }

    public static class MyJob implements Job{

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("this is Quartz schedule");
        }
    }
}
