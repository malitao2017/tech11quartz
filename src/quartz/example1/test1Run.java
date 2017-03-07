package quartz.example1;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class test1Run {
	public static void main(String[] args) throws Exception {
		new test1Run().run();
	}
	public void run() throws Exception{
		//1. SchedulerFactory 获取调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler scheduler = sf.getScheduler();

		//2. JobDetail 封装 Job，同时指定Job在Scheduler中所属组及名称
		JobDetail job =  newJob(test1Job.class).withIdentity("job1","job-group1").build();
		
		//3.创建 trigger ，指定该Trigger在Scheduler中所属组及名称。  
		// 接着设置调度的时间规则.当前时间运行  
		Date runTime = null;
		runTime = evenMinuteDate(new Date()); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("evenMinuteDate时间"+sdf.format(runTime));
//		Calendar c = new GregorianCalendar();
//		c = Calendar.getInstance();
//		runTime = c.getTime();
//		System.out.println("当前时间"+sdf.format(runTime));
//		c.add(Calendar.SECOND, 5);
//		runTime = c.getTime();
//		System.out.println("任务运行时间"+sdf.format(runTime));
		Trigger trigger = newTrigger().withIdentity("trigger1","trigger-group1").startAt(runTime).build();
		//注册并进行调度
		scheduler.scheduleJob(job,trigger);
		try{
			//当前线程等待
			Long s = 5L;
			System.out.println("睡眠"+s+"秒");
			Thread.sleep(s*1000L);
		}catch(Exception e){}
//		scheduler.shutdown(true);
		System.out.println("结束运行");
//		c = Calendar.getInstance();
//		runTime = c.getTime();
//		System.out.println("结束运行时间"+sdf.format(runTime));
		
	}
}
