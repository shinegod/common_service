package com.fx.crm.cron.comm;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

/**
 * Created by bei2love@gmail.com on 15/6/1.
 */
public class DemoQuartz implements Job{

    private static final Logger logger = LoggerFactory.getLogger(DemoQuartz.class);

    public void runTask() {
        Calendar cal = Calendar.getInstance();
        logger.info("{}:{}:{}.{} ", new Object[]{cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)});
    }


    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calendar cal = Calendar.getInstance();
        logger.info("{}:{}:{}.{} ", new Object[]{cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND)});

        System.out.println("--------->" + System.currentTimeMillis());
    }

    public static void main(String[] args) {
        DemoQuartz demo = new DemoQuartz();
        while (true) {
            demo.runTask();
        }
    }
}
