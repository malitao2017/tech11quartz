package quartz.example1;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

	public HelloJob() {

	}

	public void execute(JobExecutionContext context) throws JobExecutionException {

		_log.error(" 咫尺天涯: " + new Date());

	}
}