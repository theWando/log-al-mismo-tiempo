package coder.eduardo.tests;

import org.apache.log4j.xml.DOMConfigurator;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Random;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;

/**
 * Crea los pasos necesarios para logear al mismo tiempo
 * @author Eduardo
 * @since 18-04-2016.
 */
public class Main {

    private static final String LOG4J_XML = "/log4j.xml";

    private Scheduler scheduler;

    public Main() {
        DOMConfigurator.configure(getClass().getResource(LOG4J_XML));
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void logAtSameTme(long mills, int numberOfJobs, String message) throws Exception {

        if (scheduler.isStarted()) {
            for (int i = 0; i < numberOfJobs; i++) {
                int n = (new Random()).nextInt();

                JobDetail jobDetail = newJob(MyJob.class)
                    .withIdentity("job"+ n, "group1")
                        .usingJobData(MyJob.MESSAGE, message)
                    .build();
                Trigger trigger = newTrigger()
                        .withIdentity("trigger"+ n, "group1")
                        .startNow()
                        .build();

                // Tell quartz to schedule the job using our trigger
                scheduler.scheduleJob(jobDetail, trigger);
            }

        } else {
            throw new Exception("Scheduler not started");
        }
    }

    public void start() throws SchedulerException {
        if (scheduler != null &&
                (scheduler.isInStandbyMode() || scheduler.isShutdown())) {
            scheduler.start();
        }
    }

    public void shutdown() throws SchedulerException {
        if (scheduler.isStarted()) {
            scheduler.shutdown();
        }
    }
}
