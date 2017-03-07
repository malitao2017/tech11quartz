package quartz.example2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class test2Job implements Job{
	private Calendar c = new GregorianCalendar();
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		Date date = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ds = sdf.format(date);
		System.out.println("当前的时间为："+ds);
//		System.out.println("触发器的内容："
//				+"trigger-Name"+	context.getTrigger().getKey().getName()
//				+"trigger-Group"+	context.getTrigger().getKey().getGroup()
//				+"job-name"+	context.getJobDetail().getKey().getName()
//				+"job-group"+	context.getJobDetail().getKey().getGroup()
//				+"job-Map"+	context.getTrigger().getJobDataMap().getWrappedMap()
//				);
		System.out.println("触发器的内容："
				+":"+	context.getTrigger().getKey().getName()
				+":"+	context.getTrigger().getKey().getGroup()
				+":"+	context.getJobDetail().getKey().getName()
				+":"+	context.getJobDetail().getKey().getGroup()
				+":"+	context.getTrigger().getJobDataMap().getWrappedMap()
				);
	}

}
