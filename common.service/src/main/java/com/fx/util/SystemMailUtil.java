package com.fx.util;

import com.fx.MT4.vo.MT4User;
import com.fx.config.ConfigProperties;
import com.fx.util.freemarker.FreemarkerUtil;

import java.util.HashMap;
import java.util.Map;

public class SystemMailUtil {
	
	public static String ROOT_URL = "http://"+ConfigProperties.getProperty("domain.www");
	public static String ROOT_URL2 = "http://"+ConfigProperties.getProperty("domain.myaccount");
	
	public static String SERVICE_EMAIL = "support@vantagefx.com";
	public static String URL = ROOT_URL+ "/about/contact";
	public static String COMPANY = "Klimex";
	
	public static String LOGIN_URL = ROOT_URL2 + "/login";
//	public static String MT4_DOWNLOAD_URL ="http://files.metaquotes.net/6112/mt4/mt4setup.exe";
	public static String MT4_DOWNLOAD_URL ="http://klimexcm.com/klimex4setup.exe";
	public static String MT4_DEPOSIT_URL ="http://klimexcm.com/clients/deposit/";
	public static String MT4_INSTALLATION_URL = ROOT_URL + "/platform/pc";//
	public static String APPLY_ONLINE_URL = ROOT_URL2 + "/tradingaccounts/registerlive";//开户url
	
	
	public static String TEMPLATE_PATH = "common/email.template";
	public static String TEMPLATE_FILE_NAME_01 = "01-live_mt4_notice";//01-真实帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_02 = "02-demo_mt4_notice";//02-模拟帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_03 = "03-vip_mt4_notice";//03-VIP帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_04 = "04-vvip_mt4_notice";//04-VVIP帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_05 = "05-guaranteed_mt4_notice";//05-外汇活存帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_06 = "06-cash_dividend_mt4_notice";//06-现金股利帐户MT4通知.html
	public static String TEMPLATE_FILE_NAME_07 = "07-forget_password";//07-忘记密码.html
	public static String TEMPLATE_FILE_NAME_08 = "08-deposit_success";//08-存款成功.html
	public static String TEMPLATE_FILE_NAME_09 = "09-deposite_failed";//09-存款未到帐.html
	public static String TEMPLATE_FILE_NAME_10 = "10-withdraw_success";//10-提款成功.html
	public static String TEMPLATE_FILE_NAME_11 = "11-withdraw_failed";//11-提款未成功.html
	public static String TEMPLATE_FILE_NAME_12 = "12-account_cancellation";//12-销户.html
	public static String TEMPLATE_FILE_NAME_13 = "13-transaction_enquiry";//13-交易查询.html
	public static String TEMPLATE_FILE_NAME_14 = "14-complaint";//14-投诉.html
	public static String TEMPLATE_FILE_NAME_15 = "15-money_back";//15-返金.html
	public static String TEMPLATE_FILE_NAME_16 = "16-ib_commission";//16-IB佣金.html
	public static String TEMPLATE_FILE_NAME_19 = "19-welcome_mail";//19-欢迎.html
    public static String TEMPLATE_FILE_NAME_20 = "20-open_live_info";//20-真实账户开户通知.html

	
	/**
	 * 邮件内容获取01-真实帐户MT4通知
	 * @param userName
     * @param mt4User
	 * @return
	 */
	public static String getEmailContent01LiveMT4Notice(String userName,MT4User mt4User){
		Integer mt4 = 0;
		String mt4pwd ="";
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4pwd = mt4User.getPassword();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4pwd", mt4pwd);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("mt4DepositUrl", MT4_DEPOSIT_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		data.put("applyOnlineUrl", APPLY_ONLINE_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_01, data);
	}
	public static String getEmailContent19WelcomeMail(String email,String userName,String password){
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("email", email);
		data.put("password", password);
		data.put("company", COMPANY);
		data.put("login_url", LOGIN_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_19, data);
	}

    /**
     *
     * @param email
     * @return
     */
    public static String getEmailContent20WelcomeMail(String email) {
        Map<String, Object> data = new HashMap<>();
        data.put("email", email);
        data.put("serviceEmail", SERVICE_EMAIL);
        return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_20, data);
    }

    /**
	 * 邮件内容获取02-模拟帐户MT4通知.html
	 * @param userName
     * @param mt4User
	 * @return
	 */
	public static String getEmailContent02DemoMT4Notice(String userName,MT4User mt4User){
		Integer mt4 = 0;
		String mt4pwd ="";
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4pwd = mt4User.getPassword();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4pwd", mt4pwd);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("mt4DepositUrl", MT4_DEPOSIT_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		data.put("applyOnlineUrl", APPLY_ONLINE_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_02, data);
	}
	
	
	/**
	 * 邮件内容获取03-VIP帐户MT4通知.html
	 * @param userName
     * @param mt4User
	 * @return
	 */
	public static String getEmailContent03VipMT4Notice(String userName,MT4User mt4User){
		Integer mt4 = 0;
		String mt4pwd ="";
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4pwd = mt4User.getPassword();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4pwd", mt4pwd);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_03, data);
	}
	
	
	/**
	 * 邮件内容获取//04-VVIP帐户MT4通知.html
	 * @param userName
     *
	 * @return
	 */
	public static String getEmailContent04VvipMT4Notice(String userName,MT4User mt4User){
		Integer mt4 = 0;
		String mt4pwd ="";
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4pwd = mt4User.getPassword();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4pwd", mt4pwd);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_04, data);
	}
	
	/**
	 * 邮件内容获取//05-外汇活存帐户MT4通知.html
	 * @param userName
     * @param mt4User
	 * @return
	 */
	public static String getEmailContent05GuaranteedMT4Notice(String userName, MT4User mt4User){
		Integer mt4 = 0;
		String mt4investor_password ="";//唯讀密碼
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4investor_password = mt4User.getPassword_investor();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4investor_password", mt4investor_password);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_05, data);
	}
	
	/**
	 * 邮件内容获取06-现金股利帐户MT4通知.html
	 * @param userName
     * @param mt4User
	 * @return
	 */
	public static String getEmailContent06CashDividendMT4Notice(String userName, MT4User mt4User){
		Integer mt4 = 0;
		String mt4investor_password ="";//唯讀密碼
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4investor_password = mt4User.getPassword_investor();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4investor_password", mt4investor_password);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_06, data);
	}
	
	
	public static String getEmailContent16IbCommission(String userName, MT4User mt4User){
		Integer mt4 = 0;
		String mt4investor_password ="";//唯讀密碼
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4investor_password = mt4User.getPassword_investor();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4investor_password", mt4investor_password);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_16, data);
	}
	public static String getEmailContent15MoneyBack(String userName, MT4User mt4User){
		Integer mt4 = 0;
		String mt4investor_password ="";//唯讀密碼
		if (mt4User != null) {
			mt4 = mt4User.getLogin();
			mt4investor_password = mt4User.getPassword_investor();
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4", mt4+"");
		data.put("mt4investor_password", mt4investor_password);
		data.put("loginUrl", LOGIN_URL);
		data.put("mt4DownloadUrl", MT4_DOWNLOAD_URL);
		data.put("installationUrl", MT4_INSTALLATION_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_15, data);
	}
	/**
	 * 邮件内容获取07-忘记密码.html
	 * @param userName
     * @param loginAccount
     * @param password
	 * @return
	 */
	public static String getEmailContent07ForgetPassword(String userName,
			String loginAccount,
			String password//存入金额
			){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("loginAccount", loginAccount);
		data.put("password", password);
		data.put("loginUrl", LOGIN_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_07, data);
		
	}
	
	
	/**
	 * 邮件内容获取08-存款成功.html
	 * @param userName
     * @param mt4_account_number
     * @param deposits
	 * @return
	 */
	public static String getEmailContent08DepositSuccess(String userName,
			String mt4_account_number,
			String deposits//存入金额
			){
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4_account_number", mt4_account_number+"");
		data.put("deposits", deposits);
		data.put("loginUrl", LOGIN_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_08, data);
	}
	/**
	 * 邮件内容获取09-存款未到帐.html
	 * @param userName
     * @param deposit_status
     * @param deposits
	 * @return
	 */
	public static String getEmailContent09DepositeFailed(String userName,
			String mt4_account_number,
			String deposits,//存入金额
			String deposit_status){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4_account_number", mt4_account_number+"");
		data.put("deposits", deposits);
		data.put("deposit_status", deposit_status);
		data.put("loginUrl", LOGIN_URL);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_09, data);
	}

    /**
     * 邮件内容获取//10-提款成功.html
     * @param userName
     * @param mt4_account_number
     * @param amount_raised
     * @param fee
     * @param bank
     * @param subok
     * @param swiftcode
     * @param accountName
     * @param bank_account_number
     * @param withdrawStatus
     * @return
     */
	public static String getEmailContent10WithdrawSuccess(String userName,
			String mt4_account_number,
			String amount_raised,
			String fee,
			String bank,
			String subok,
			String swiftcode,
			String accountName,
			String bank_account_number,
			String withdrawStatus){
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4_account_number", mt4_account_number+"");
		data.put("amount_raised", amount_raised);
		data.put("fee", fee);
		data.put("bank", bank);
		data.put("subok", subok);
		data.put("swiftcode", swiftcode);
		data.put("accountName", accountName);
		data.put("bank_account_number", bank_account_number);
		data.put("withdrawStatus", withdrawStatus);
		
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_10, data);
	}

    /**
     * 邮件内容获取/11-提款未成功.html
     * @param userName
     * @param mt4_account_number
     * @param amount_raised
     * @param fee
     * @param bank
     * @param subok
     * @param swiftcode
     * @param accountName
     * @param bank_account_number
     * @param withdrawStatus
     * @return
     */
	public static String getEmailContent11WithdrawFailed(String userName,
			String mt4_account_number,
			String amount_raised,
			String fee,
			String bank,
			String subok,
			String swiftcode,
			String accountName,
			String bank_account_number,
			String withdrawStatus){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		data.put("mt4_account_number", mt4_account_number+"");
		data.put("amount_raised", amount_raised);
		data.put("fee", fee);
		data.put("bank", bank);
		data.put("subok", subok);
		data.put("swiftcode", swiftcode);
		data.put("accountName", accountName);
		data.put("bank_account_number", bank_account_number);
		data.put("withdrawStatus", withdrawStatus);
		//TODO 待定
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_11, data);
	}

    /**
     * 邮件内容获取//12-销户.html
     * @param userName
     * @param loginAccount
     * @param reason
     * @return
     */
	public static String getEmailContent12AccountCancellation(String userName,String loginAccount, String reason){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("loginAccount", loginAccount);
		data.put("reason", reason);
		data.put("company", COMPANY);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_12, data);
	}
	/**
	 * 邮件内容获取//13-交易查询.html
	 * @param userName
	 * @return
	 */
	public static String getEmailContent13TransactionEnquiry(String userName){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_13, data);
	}
	/**
	 * 邮件内容获取//14-投诉.html
	 * @param userName
	 * @return
	 */
	public static String getEmailContent14Complaint(String userName){
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("userName", userName);
		data.put("serviceEmail", SERVICE_EMAIL);
		data.put("url", URL);
		data.put("company", COMPANY);
		return FreemarkerUtil.parseTemplate(TEMPLATE_PATH, TEMPLATE_FILE_NAME_14, data);
	}

}
