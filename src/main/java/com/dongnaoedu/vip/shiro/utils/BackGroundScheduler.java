package com.dongnaoedu.vip.shiro.utils;

import static org.quartz.CronScheduleBuilder.cronSchedule;

import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


public class BackGroundScheduler {
	static SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
	static Scheduler sched;
	public static void run()throws Exception{
	sched = schedFact.getScheduler();//获取 调度管理器
	JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1","group1").build();
	//定义触发器
	Trigger trigger = TriggerBuilder.newTrigger()
					  .withIdentity("simpleTrigger","simpleTriggerGroup")
//			          .withSchedule(scheduleBuilder)
//			          .withSchedule(cronSchedule("0 0 0,1,2,3,4,5,6,20,21,22,23 1/1 * ? *"))
					  .withSchedule(cronSchedule("0/3 2/1 9 * * ? *"))
					  .startNow().build();	
	sched.scheduleJob(jobDetail, trigger);//添加到调度管理器中
	sched.start();//启动调度管理器
	}
	public static void stop()throws Exception{
		sched.shutdown();
	}
}
