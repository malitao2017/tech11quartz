package quartz.example2;

import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;

public class test2Trigger {
	public static void main(String[] args) throws Exception {
		new test2Trigger().run();
	}
	public void run() throws Exception{
		//1. 调度
		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		//2. job
		JobDetail jobDetail =JobBuilder.newJob(test2Job.class).withIdentity(new JobKey("job1", "job-group1")).build(); 
		JobDataMap newJobDataMap = new JobDataMap();
		newJobDataMap.put("key1", "value1");
		newJobDataMap.put("key2", "value2");
		//3. 触发器
		Trigger trigger = TriggerBuilder.newTrigger().
				withIdentity(new TriggerKey("trigger1", "trigger-group1")).
				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever()).
//				withSchedule(CronScheduleBuilder.cronSchedule(""))
				usingJobData(newJobDataMap).
				startAt(
						//futureDate是将来的某个时刻
						DateBuilder.futureDate(2, IntervalUnit.SECOND)).
				build();
		//3. 触发器2 第二种添加方式
		Trigger trigger2 = TriggerBuilder.newTrigger().
				withIdentity(new TriggerKey("trigger2", "trigger-group2")).
				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).
				startAt(
						//futureDate是将来的某个时刻
						DateBuilder.futureDate(5, IntervalUnit.SECOND)).
				forJob(jobDetail).build();
		//添加两种方式的触发器
		scheduler.scheduleJob(jobDetail,trigger);
		scheduler.scheduleJob(trigger2);
		scheduler.start();
	}
}
