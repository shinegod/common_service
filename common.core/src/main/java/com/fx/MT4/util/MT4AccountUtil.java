package com.fx.MT4.util;

import com.fx.MT4.enums.MT4EnableEnum;
import com.fx.MT4.enums.MT4PasswordRequireEnum;
import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.vo.MT4User;
import com.fx.common.model.City;
import com.fx.common.model.Country;
import com.fx.common.service.ICityService;
import com.fx.common.service.ICountryService;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.util.I18N;
import com.fx.util.SpringUtils;
import com.fx.util.UserUtil;
import com.google.gson.Gson;
import mybatis.framework.core.service.BaseVOService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Random;

public class MT4AccountUtil extends BaseVOService<UserRegister>{

	private static Logger logger = LoggerFactory.getLogger(MT4AccountUtil.class);

	private static final int DEFAULT_LEVERAGE = 100;//默认杠杆

	public static MT4User createDemoMT4Account(UserRegister userRegister,int login,DataSourceBean dataSourceBean){
		User user=new User();
		user.setFirstName(userRegister.getEmail());
		return createMT4Account(MT4PasswordRequireEnum.REQUIRE,user,userRegister,dataSourceBean.getMt4Group(),DEFAULT_LEVERAGE,login,QueryMtT4GroupIdEnum.DEMO,0,dataSourceBean); //todo demo默认agentAccount为0
	}

	public static MT4User createLiveMT4Account(User user, UserRegister userRegister,String groupNameMT4,int leverage,int login ,int agentAccount,DataSourceBean dataSourceBean){
		return createMT4Account(MT4PasswordRequireEnum.NOT_REQUIRE, user, userRegister, groupNameMT4, leverage, login, QueryMtT4GroupIdEnum.LIVE, agentAccount, dataSourceBean);
	}

	public static MT4User createMT4Account(MT4PasswordRequireEnum requireEnum,User user, UserRegister userRegister,String groupNameMT4,int leverage,int login,QueryMtT4GroupIdEnum groupIdEnum,int agentAccount,DataSourceBean dataSourceBean){
		if (user == null) {
			user = new User();
		}
		if (userRegister == null) {
			userRegister = new UserRegister();
		}


		// Create MT4user in MT4 Server     By Dean
		String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
//			ip=MT4Configuration.ipDemo;
            ip = dataSourceBean.getMt4DemoIp();
		else
//			ip=MT4Configuration.ipLive;
            ip = dataSourceBean.getMt4LiveIp();
		// Create base user in MT4 
		MT4User mt4user=new MT4User();
		mt4user.login=(login);
		mt4user.g_name_MT4=groupNameMT4;

		mt4user.name=userRegister.getCnName();

		logger.debug("######Create base user in MT4 before,return mt4user:{}",new Gson().toJson(mt4user));
		logger.info("######调用DLLAgent的addClient方法，执行成功提示后提示addClient successs.");
		DLLAgent.addClient(mt4user, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
		/**
		 * dll返回用户有误，以下语句避免错误乱码
		 */
		mt4user.name=userRegister.getCnName();
		logger.info("######addClient successs.");
		logger.debug("######Create base user in MT4 after,return mt4user:{}",new Gson().toJson(mt4user));
		//update user info
		mt4user.enable=MT4EnableEnum.ENABLE.getValue();
		if(leverage<=0) leverage=DEFAULT_LEVERAGE;
		mt4user.leverage=(leverage);
		mt4user.agent_account=(agentAccount);
		mt4user.email=(null2Space(userRegister.getEmail()));
		mt4user.phone=(null2Space(userRegister.getPhoneNum()));
		mt4user.address=(null2Space(user.getAddress()));
        /*
            if (user.getNationalityCode() != null) {
                mt4user.country=null2Space(DictionaryUtil.COUNTRY_MAP.get(user.getNationalityCode()));
            }
		*/
        ICountryService countryService = SpringUtils.getBean(ICountryService.class);
        ICityService cityService = SpringUtils.getBean(ICityService.class);
		if (user.getNationalityCode() != null) {
			if (!StringUtils.equals(user.getResidencyCountryCode(), "0")) {
				Country country = countryService.findById(user.getResidencyCountryCode());
				Locale locale = UserUtil.getCurrentLocale();
				if (locale != null) {
					if (locale.getLanguage().equals("en_US")) {
						mt4user.country = country.getCountryNameEn();
					}
				} else {
					mt4user.country = country.getCountryNameCn();
				}
			}
		}
		if (user.getCityCode() != null) {
//			if (!StringUtils.equals(user.getCityCode(), "0")) {
//				City city = cityService.findById(user.getCityCode());
//				mt4user.city = I18N.getLangValue(UserUtil.getCurrentLocale(), city.getCityCode(), city.getCityname());
//			}
			//城市不是读取数据库
			mt4user.city = user.getCityCode();
		}

//		mt4user.city=(null2Space(user.getCityCode()));
		mt4user.zipcode=(null2Space(user.getPostcode()));
		//mt4user.id=(null2Space(user.getIdNum()));// TODO 此字段，MT4平台有其他用途
		mt4user.enable_read_only=0;
		mt4user.enable_change_password=1;
		logger.info("######update base user in MT4 before,return mt4user:{}",new Gson().toJson(mt4user));
		logger.info("######调用DLLAgent的updateClient方法，执行成功提示后提示updateClient successs.");
        mt4user=DLLAgent.updateClient(mt4user, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
		logger.info("######updateClient successs.");
		logger.info("######update base user in MT4 after,return mt4user:{}",new Gson().toJson(mt4user));
		// -----------

		//log
		logger.info("######create MT4 account,return mt4user:{}",new Gson().toJson(mt4user));

		return mt4user;
	}

	public static String null2Space(String str){
		return str == null ? "":str;
	}

	public static MT4User getMT4ClientInfo(int mt4Account,QueryMtT4GroupIdEnum groupIdEnum, DataSourceBean dataSourceBean){
		/*String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			ip=MT4Configuration.ipDemo;
		else
			ip=MT4Configuration.ipLive;
		return DLLAgent.getClient(mt4Account, MT4Configuration.lgn, MT4Configuration.pwd, ip);*/
        String ip;
        if (groupIdEnum.getValue() == QueryMtT4GroupIdEnum.DEMO.getValue()) {
            ip = dataSourceBean.getMt4DemoIp();
        } else {
            ip = dataSourceBean.getMt4LiveIp();
        }
        return DLLAgent.getClient(mt4Account, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
    }

    public static MT4User getMT4ClientInfo(int mt4Account,QueryMtT4GroupIdEnum groupIdEnum){
		//解决未使用数据源导致dll 链接失败问题
		IUserMT4AccountService userMT4AccountService = SpringUtils.getBean(IUserMT4AccountService.class);
		IDataSourceBeanService dataSourceBeanService = SpringUtils.getBean(IDataSourceBeanService.class);

		UserMT4Account userMT4Account = userMT4AccountService.getByMt4Account(mt4Account);
		DataSourceBean dataSourceBean = dataSourceBeanService.findDataSourceBeanById(userMT4Account.getDataSourceId());
		String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			ip = dataSourceBean.getMt4DemoIp();
		else
			ip = dataSourceBean.getMt4LiveIp();
        return DLLAgent.getClient(mt4Account, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
    }

	public static MT4User updateMT4ClientInfo(MT4User mt4User,QueryMtT4GroupIdEnum groupIdEnum, DataSourceBean dataSourceBean){
		String ip;

        if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
//			ip=MT4Configuration.ipDemo;
            ip = dataSourceBean.getMt4DemoIp();
        else
//			ip=MT4Configuration.ipLive;
            ip = dataSourceBean.getMt4LiveIp();
        return DLLAgent.updateClient(mt4User,dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
	}

	public static int resetMT4Password(int login,String password,String passwordInvestor,QueryMtT4GroupIdEnum groupIdEnum, DataSourceBean dataSourceBean){
		String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
//			ip=MT4Configuration.ipDemo;
            ip = dataSourceBean.getMt4DemoIp();
		else
//			ip=MT4Configuration.ipLive;
            ip = dataSourceBean.getMt4LiveIp();
		return DLLAgent.UserPasswordSet(login, password, passwordInvestor, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
	}

	public static String generataRandomPassword(int length){
		String strAll = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		//定义一个结果
		String result = "";
		//实例化Random对象
		Random random = new Random();
		//使用for循环得到6为字符
		for (int i = 0; i < length; i++)
		{
			//返回一个小于62的int类型的随机数
			int rd = random.nextInt(62);
			//随机从指定的位置开始获取一个字符
			String oneChar = strAll.substring(rd,(rd+1));
			//循环加到6为
			result += oneChar;
		}
		return result;
	}

	public static String generataRandomPasswordForWebsite(int length){
		String strAll = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numAll="0123456789";
		//定义一个结果
		String result = "";
		//实例化Random对象
		Random random = new Random();
		//使用for循环得到6为字符
		for (int i = 0; i < length-4; i++)
		{
			//返回一个小于62的int类型的随机数
			int rd = random.nextInt(52);
			//随机从指定的位置开始获取一个字符
			String oneChar = strAll.substring(rd,(rd+1));
			//循环加到6为
			result += oneChar;
		}
		for(int j=0;j<4;j++){
			//返回一个小于62的int类型的随机数
			int rd = random.nextInt(10);
			//随机从指定的位置开始获取一个字符
			String oneChar = numAll.substring(rd,(rd+1));
			//循环加到6为
			result += oneChar;
		}
		return result;
	}
	public static int checkPassword(int login,String password,QueryMtT4GroupIdEnum groupIdEnum){
		String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			ip=MT4Configuration.ipDemo;
		else
			ip=MT4Configuration.ipLive;
		return DLLAgent.checkPassword(login, password, MT4Configuration.lgn, MT4Configuration.pwd, ip);
	}
	public static int checkPassword(int login,String password,QueryMtT4GroupIdEnum groupIdEnum, DataSourceBean dataSourceBean){
		String ip;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
//			ip=MT4Configuration.ipDemo;
			ip = dataSourceBean.getMt4DemoIp();
		else
//			ip=MT4Configuration.ipLive;
			ip = dataSourceBean.getMt4LiveIp();
		return DLLAgent.checkPassword(login, password, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(), ip);
	}
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		System.out.println(generataRandomPasswordForWebsite(8));
	}

}
