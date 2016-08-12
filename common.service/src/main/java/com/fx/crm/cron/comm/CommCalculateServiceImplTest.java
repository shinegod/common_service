package com.fx.crm.cron.comm;


import com.fx.crm.cron.comm.ICommCalculateService;
import com.fx.payment.exception.PayException;
import com.fx.util.SpringUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by bei2love@gmail.com on 15/6/10.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/applicationContext.xml")
public class CommCalculateServiceImplTest {



    private static final Logger logger = LoggerFactory.getLogger(CommCalculateServiceImplTest.class);


    @Test
    public void calCommissionTest(){

    	Calendar date=Calendar.getInstance();
		Calendar today=Calendar.getInstance();
		today.set(2015, 7,12);
		date.set(2015, 7, 7);
		while(date.before(today)){
			try {
				//trySettleIBCommission(date);
				Calendar today2 = (Calendar)date.clone();
				today2.add(Calendar.DAY_OF_MONTH, -1);
				ICommCalculateService commCalculateService = SpringUtils.getBean(ICommCalculateService.class);
	            commCalculateService.calCommission(today2.getTime(), date.getTime());
				try {
					commCalculateService.calculateMonthlyIbCommission(date);
				} catch (PayException e) {
					e.printStackTrace();
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			date.add(Calendar.DAY_OF_MONTH, 1);
		}
    }
}

