package com.fx.MT4.util;

import com.fx.MT4.vo.MT4GroupConfig;
import com.fx.MT4.vo.MT4TradeRecord;
import com.fx.MT4.vo.MT4User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;



public class DLLAgent {

	private static long last_pump_time_Demo=0;
	private static long last_pump_time_Live=0;
	//public static int update_interval=10;
	private static ArrayList<MT4TradeRecord> OpenOrderDemo=new ArrayList<MT4TradeRecord>();
	private static ArrayList<MT4TradeRecord> OpenOrderLive=new ArrayList<MT4TradeRecord>();
	static
	{
		System.out.println("##########start find dll");
		try{
			if (System.getProperty("sun.arch.data.model").equals("64")){
				System.load("c:\\dll\\kcm\\mtmanapi64.dll");
				System.load("c:\\dll\\kcm\\com_fx_MT4_util_DLLAgent.dll");}
			else {
				System.load("c:\\dll\\kcm\\mtmanapi.dll");
				System.load("c:\\dll\\kcm\\com_fx_MT4_util_DLLAgent32.dll");

			}

			System.out.println("com_fx_MT4_util_DLLAgent.dll loaded Successfully");

		}
		catch (UnsatisfiedLinkError e ){
			//System.load("D:/DLL_Agent_SVN/x64/Debug/com_fx_MT4_util_DLLAgent.dll");
			System.out.println("com_fx_MT4_util_DLLAgent.dll can't be found or has been loaded.");
			e.printStackTrace();
		}

	}

	//----JNI function
	// ----Login and get manager rights
	// Function:    changeBalance(mt4account,amount,comment,...)
	//return -1 login failed
	//return -2 Wrong Server Address
	//return -3 DLL Version unmathched
	//return > 0, login passed, return manager rights

	public static native int login(int  lgn,String pwd,String ip) throws Exception;

	// ----Account Util
	public static native MT4User getClient(int id, int lgn, String pwd,String ip);
	public static native int UserPasswordSet(int login, String password,String password_investor, int mT4_ID, String mT4_password,String mT4_ip);
	public static native MT4User addClient(MT4User newUser, int lgn, String pwd,String ip) ;
	public static native MT4User updateClient(MT4User user, int lgn, String pwd,String ip) ;
	/*
     * checkPassword(int MT4Login,String MT4Password, int lgn, String pwd,String ip)
     * return =	 0	, password matched
     * 			 1	, password unmatched
     * 			-1	, manager login failed
     * 			-2	, Wrong Server Address
     * 			-3	, DLL Version unmathched
     *
     */
	public static native int checkPassword(int MT4Login,String MT4Password, int lgn, String pwd,String ip) ;
	// -----Deposit Util
	// Function:    changeBalance(mt4account,amount,comment,...)
	// return code 	-20: wrong manager login or password
	//		 		-10: wrong ip address
	//		 		-2 : wrong mt4 account or amt is invalid
	//		  		 10: Return Ok
	public static native int changeBalance(int id, double amt, String comment, int lgn, String pwd,String ip);



	// TradeRecord Util
	public static native ArrayList<MT4TradeRecord> getRecord(ArrayList<MT4TradeRecord> res,int id, int[] date1, int[] date2, int lgn, String pwd,String ip,int timezone) ;
	public static native ArrayList<MT4TradeRecord> getGroupRecord(ArrayList<MT4TradeRecord> res_set, ArrayList<Integer> clients,int[] dat1, int[] dat2, int lgn, String pwd,String ip,int timezone);
	private static native ArrayList<MT4TradeRecord> getOpenOrderList(ArrayList<MT4TradeRecord> res,int mT4_ID, String mT4_password,String mT4_ip);
	public static ArrayList<MT4TradeRecord> getOpenOrders(boolean isDemo){
		long now=System.currentTimeMillis();
		if(isDemo){
			if (now-last_pump_time_Demo>1000*60*MT4Configuration.update_interval){
				System.out.println("Start Update Open Trade list on " +new Date(now).toString());
				OpenOrderDemo=getOpenOrderList(new ArrayList<MT4TradeRecord>(),MT4Configuration.lgn, MT4Configuration.pwd,MT4Configuration.ipDemo);
				last_pump_time_Demo=now;
				System.out.println("Open Trade List updated on "+new Date(System.currentTimeMillis()).toString());
			}
			return OpenOrderDemo;
		}
		else{
			if (now-last_pump_time_Live>1000*60*MT4Configuration.update_interval){
				System.out.println("Start Update Open Trade list on " +new Date(now).toString());
				OpenOrderLive=getOpenOrderList(new ArrayList<MT4TradeRecord>(),MT4Configuration.lgn, MT4Configuration.pwd,MT4Configuration.ipLive);
				last_pump_time_Live=now;
				System.out.println("Open Trade List updated on "+new Date(System.currentTimeMillis()).toString());
			}
			return OpenOrderLive;
		}
	}


	//----- Group Util
	public static native HashMap<String,MT4GroupConfig> requestGroupConfig(HashMap<String,MT4GroupConfig> gc, int lgn, String pwd, String ip);


	//----- Test
	public static native void CManagerFactoryTest();
	public static native void test();

	public static native int updateGeneralGroupConfig(MT4GroupConfig groupConfig, int lgn, String pwd, String ip);

	//根据组，mt4账号，数据源信息获取对应币种
	public static native String getDepositCurrency(String group, int lgn, String pwd, String ip);

	//修改信用相关方法  id是mt4账号
	public static native int changeCredit(int id, double amt, String comment, int days, int lgn, String pwd, String ip);


	public static void main(String args[]) throws Exception {
		//test();
		//updateGeneralGroupConfig(new MT4GroupConfig(),25,"Leo12345",MT4Configuration.ip);
		MT4User mt4User5 = new MT4User();
		mt4User5.login=(7822074);
		mt4User5.g_name_MT4="test_CRM_USD";

		mt4User5.name="哈哈";
		MT4User addClient = addClient(mt4User5, 488, "Passw0rd123", "82.145.44.116:443");


		String  currency = getDepositCurrency("test_CRM_USD",488,"Passw0rd123","82.145.44.116:443");
		System.out.println("getDepositCurrency():"+currency);

		int n = login(488,"Passw0rd123","82.145.44.116:443");
		System.out.println("login():"+n);

		MT4User mt4User = getClient(7821559, 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("mt4User():"+mt4User.getLogin());

		int w = UserPasswordSet(7821559, "123","123qwe", 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("UserPasswordSet():"+w);


		//addClient(mt4User, 488, "Passw0rd123", "82.145.44.116:443") ;
		mt4User.setName("汉语");
		mt4User.setAddress("这是地址");
		mt4User.setCity("城市");
		MT4User mt4User1 = updateClient(mt4User, 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("updateClient():"+mt4User1.getName());
		System.out.println("updateClient():"+mt4User1.getAddress());
		System.out.println("updateClient():"+mt4User1.getCity());

		MT4User mt4User2 = getClient(7821559, 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("getClient():"+mt4User2.getName());

		int checkPassword = checkPassword(7821559, "123", 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("checkPassword():"+checkPassword);

		int balance = changeBalance(7821559, 2.3d, "测试", 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("changeBalance():"+balance);

		int credit = changeCredit(7821559, 3.3d, "测试", 1, 488, "Passw0rd123", "82.145.44.116:443");
		System.out.println("changeCredit():"+balance);
	}
}
