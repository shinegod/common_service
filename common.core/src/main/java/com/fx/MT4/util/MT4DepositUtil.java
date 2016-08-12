package com.fx.MT4.util;

import java.util.Date;

import com.fx.dataSourceBean.model.DataSourceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.payment.exception.PayException;

public class MT4DepositUtil {
	//---- MT4 Server IP and manager account 
	// store it in MSSQL later
	private static Logger logger = LoggerFactory.getLogger(MT4DepositUtil.class);
	/**
	 * JNI调用DLL
	 * @author Dean
	 * @throws PayException 
	 */

	public static int deposit(int uid, double money,String comment,QueryMtT4GroupIdEnum groupIdEnum,DataSourceBean dataSourceBean) throws PayException{
		 String ip;
		 if (groupIdEnum==QueryMtT4GroupIdEnum.DEMO)
			 ip=dataSourceBean.getMt4DemoIp();
		 else
			 ip=dataSourceBean.getMt4LiveIp();
			 
		logger.info("UID:{} Request Deposit:{} at {}", uid, money, new Date());
		logger.info("######调用DLLAgent的changeBalance方法，执行成功提示后提示changeBalance successs.");
		int result =0;
		try{
			 result = DLLAgent.changeBalance(uid, money, comment, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip) ;

		} catch (Exception e){
			throw new PayException("dll changeBalance err, " + e.getMessage());
		} finally {
			if(result != 10){
				logger.error("UID:{} Request Deposit:{} has error, return: {}", uid, money, result);
				throw new PayException("deposit to MT4 failed.");
			}else{
				logger.info("######changeBalance successs.");
				logger.info("UID:{} Request Deposit:{} finish Deposit, return: {}", uid, money, result);
				return 10;
			}
		}

	}
}
