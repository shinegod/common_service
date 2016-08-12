package com.fx.MT4.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class MT4DepositUtil_backup {
	
	private static Logger logger = LoggerFactory.getLogger(MT4DepositUtil.class);
	/**
	 * JNA调用通信dll的使用封装方法
	 * @author Carter
	 */
	private interface MFCTest extends Library { 
		MFCTest INSTANCE = (MFCTest)Native.loadLibrary("c:\\dll\\MT4Deposit.dll", MFCTest.class);// 将这里的MT4Deposit.dll复制到c:\dll 下
        int hadwinsDeposit(int uid, double money);		
	}
	
	public int deposit(int uid, double money){
		logger.info("UID:{} Request Deposit:{} at {}", uid, money, new Date());
		int result = MFCTest.INSTANCE.hadwinsDeposit(uid, money);
		if(result != 10){
			logger.error("UID:{} Request Deposit:{} has error, return: {}", uid, money, result);
		}else{
			logger.info("UID:{} Request Deposit:{} finish Deposit, return: {}", uid, money, result);
		}
		return result ;
	}
	
	private static MT4DepositUtil instance = null;
	
	public static MT4DepositUtil getInstance(){
		if(null == instance){
			instance = new MT4DepositUtil();
		}
		return instance;
	}
	
}
