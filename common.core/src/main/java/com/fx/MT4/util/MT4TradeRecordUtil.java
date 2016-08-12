package com.fx.MT4.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.mail.MessagingException;

import mybatis.framework.core.service.BaseVOService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.vo.MT4TradeRecord;


public class MT4TradeRecordUtil extends BaseVOService<MT4TradeRecord>{
	
	private static Logger logger = LoggerFactory.getLogger(MT4TradeRecordUtil.class);
	private static int timezone;
	static{
		TimeZone tz = TimeZone.getDefault();
		timezone = tz.getRawOffset();
	}
	
	public static ArrayList<MT4TradeRecord> getHistoryByUser(int login, String startDate, String endDate, QueryMtT4GroupIdEnum groupIdEnum) {
		 String ip;
		 if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			 ip=MT4Configuration.ipLive;
		 else
			 ip=MT4Configuration.ipDemo;
		ArrayList<MT4TradeRecord> res=new ArrayList<MT4TradeRecord>();
		int d1[]=String2IntArray(startDate);
		int d2[]=String2IntArray(endDate);
		res=DLLAgent.getRecord(res, login, d1, d2, MT4Configuration.lgn, MT4Configuration.pwd, ip,timezone/3600000);
		logger.info("######Retrieve Trading Record for {},return mt4TradeRecord:{}",login,res.size());
		return res;
	}
	
	public static ArrayList<MT4TradeRecord> getOpenOrders(boolean isDemo){
		return DLLAgent.getOpenOrders(isDemo);
	}
	
	public static ArrayList<MT4TradeRecord> getHistoryByGroup(ArrayList<Integer> clients,String startDate, String endDate,QueryMtT4GroupIdEnum groupIdEnum){
		 String ip;
		 if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.LIVE.getValue())
			 ip=MT4Configuration.ipLive;
		 else
			 ip=MT4Configuration.ipDemo;
		ArrayList<MT4TradeRecord> res=new ArrayList<MT4TradeRecord>();
		int d1[]=String2IntArray(startDate);
		int d2[]=String2IntArray(endDate);
		res=DLLAgent.getGroupRecord(res, clients, d1, d2, MT4Configuration.lgn, MT4Configuration.pwd, ip,timezone/3600000);
		logger.info("######Retrieve Trading Record for group, return mt4TradeRecord:{}",res.size());
		return res;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		String date1="2015-01-25";
		String date2="2015-02-13";
		ArrayList<Integer> clients=new ArrayList<Integer>();
		clients.add(2001105);
		ArrayList<MT4TradeRecord> TRS=getHistoryByUser(2001105,date1,date2,QueryMtT4GroupIdEnum.DEMO);
	
		for(int i=0;i<TRS.size();i++)
			System.out.println(TRS.get(i).order);
	}
	
	public static int[] String2IntArray(String date){
		String s[]=date.split("-");
		// s[0]=day,s[1]=month,s[2]=year
		int d[]=new int[3];
		// d[2]=day,d[1]=month,d[0]=year
		d[2]=Integer.parseInt(s[0]);
		d[1]=Integer.parseInt(s[1]);
		d[0]=Integer.parseInt(s[2]);
		return d;
		
	}

	public static List<MT4TradeRecord> getOpenOrders(int mt4Id,QueryMtT4GroupIdEnum groupIdEnum) {
		boolean isDemo=false;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			isDemo=true;
		List<MT4TradeRecord> openOrderList=DLLAgent.getOpenOrders(isDemo);
		List<MT4TradeRecord> res=new ArrayList<MT4TradeRecord>();
		for (int i=0;i<openOrderList.size();i++){
			MT4TradeRecord record=openOrderList.get(i);
			if (record.login==mt4Id)
				res.add(record);
		}
		return res;
	}
}
