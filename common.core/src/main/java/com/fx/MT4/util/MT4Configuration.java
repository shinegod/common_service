package com.fx.MT4.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MT4Configuration {

	private static final Logger logger = LoggerFactory.getLogger(MT4Configuration.class);

	//MT4 config key , for configs.properties
	public static final  String MT4_CONFIG_DEMOGROUPID = "mt4.demoGroupId";
	public static final  String MT4_CONFIG_DEMOGROUPNAME = "mt4.demoGroupName";
	public static final  String MT4_CONFIG_LGN = "mt4.lgn";
	public static final  String MT4_CONFIG_PWD = "mt4.pwd";
	public static final  String MT4_CONFIG_IPDEMO ="mt4.ipDemo";
	public static final  String MT4_CONFIG_IPLIVE = "mt4.ipLive";
	public static final  String MT4_CONFIG_UPDATE_INTERVAL =  "mt4.update_interval";
	public static final  String MT4_CONFIG_COMPANYID = "mt4.companyId";

	//TODO 已修改成枚举类
	public static  int demoGroupId ;
//	public static final int liveGroupId = 0;
//	public static final String demoGroupName="CRM_VFX_USD";
	public static String demoGroupName ;
	public static int lgn ;
	public static String pwd ;
//	public static final String ip="210.215.131.102:443";
	public static String ipDemo;
	public static String ipLive ;
	public static int update_interval ;   //Open order list update interval in minutes
	public static int companyId;



	//TODO 已修改成枚举类
//	public static  int demoGroupId=14;
//	//	public static final int liveGroupId = 0;
////	public static final String demoGroupName="CRM_VFX_USD";
//	public static String demoGroupName="demokcmusd";
//	public static int lgn=499;
//	public static String pwd="Passw0rd123";
//	//	public static final String ip="210.215.131.102:443";
//	public static String ipDemo="123.103.249.178:443";
//	public static String ipLive="202.147.45.151:443";
//	public static int update_interval=1;   //Open order list update interval in minutes
//	public static int companyId=4;

}

