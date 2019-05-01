package com.pulbet.chechapp;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CheckerCaller {
	
	public static void main (String[ ]args) throws SchedulerException{
		
		JobDetail j = JobBuilder.newJob(Checker.class).build();
		
		Trigger t = TriggerBuilder.newTrigger().withIdentity("Trigger")
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(30)
						.repeatForever()).build();
		
		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		
		s.start();
		s.scheduleJob(j, t);
		
	}

}
