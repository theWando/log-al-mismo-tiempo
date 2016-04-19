package coder.eduardo.tests;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by Eduardo on 19-04-2016.
 */
public class MyJob implements org.quartz.Job {
    public static final String MESSAGE = "MESSAGE";
    private static Logger log = Logger.getLogger(MyJob.class);

    public void execute(JobExecutionContext context) throws JobExecutionException {
        long mills = (new Date()).getTime();
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        String msg = dataMap.getString(MESSAGE);
        log.info(mills + " " + msg);
    }
}
