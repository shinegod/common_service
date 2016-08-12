package com.fx.util;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/5/15.
 */
public class Config {


    public final static String CONSET_PAGE_APP_LANG = "appLang";
    public final static String CONSET_PAGE_APP_LANG_CODE = "appLangCode";
    public final static String CONSET_PAGE_APP_THEME_SKIN = "appThemeSkin";
    public final static String CONSET_PAGE_APP_THEME_FONT = "appThemeFont";


    public final static String CONST_LANG_CODE_SUPPORT = "lang.code.support";
    public final static String CONST_LANG_CODE_DEFAULT = "lang.code.default";


    public final static String CONST_COOKIE_NAME_SKIN_CODE = "cookie.skin.code"; //
    public final static String CONSET_PAGE_APP_SKIN_CODE = "crm.skin.default"; // 默认主题风格

    public final static String CONST_MONEY_OPTIONS = "moneyOptions";

    public final static String CONST_DATE_PRE = "date.format.";
    public final static String CONST_DATE_FORMAT_DATE = "date";
    public final static String CONST_DATE_FORMAT_TIME = "time";
    public final static String CONST_DATE_FORMAT_DATETIME = "datetime";
    public final static String CONST_DATE_FORMAT_SHORTDATE = "shortdate";
    public final static String CONST_DATE_FORMAT_SHORTTIME = "shorttime";
    public final static String CONST_DATE_FORMAT_SHORTDATETIME = "shortdatetime";
    public final static String CONST_DATE_FORMAT_DATESHORTTIME = "dateshorttime";

    public final static String CONST_DATE_FORMAT_DATELONGTIME = "datelongtime";

    public final static String CONTRACT_UPLOAD_PATH = "contract.upload.path"; // 合同附件上传

    public final  static  String IB_COMMISSION_MT4GROUP = "IB.Commission.MT4Group";
    public final  static  String CLIENT_COMMISSION_MT4GROUP = "Client.Commission.MT4Group";
    public final  static  String PAY_URL = "pay_url";   //支付地址

    public final static String CURRENT_SYSTEM_ID = "current_system_id";

    /**
     * 登录成功默认的跳转地址
     */
    public static final String SHIRO_DEFAULT_SUCCESS_URL_KEY = "shiro.default.success.url";

    /**
     * 默认超级管理员的账户名称
     */
    public static final String SUPPER_ADMIN = "root";
    public static final java.lang.String CACHE_SYSTEM_PREFIX = "redis.system.prefix";
    /**
     * 用户两次操作的空闲时间
     */
    private static final java.lang.String USER_IDLE_TIMEOUT = "system.user.idletime";

    /**
     * #客户生命周期相关操作 --创建 分配 开户 同名账户 入金 出金 操作
     */
    public static final String CUSTOMER_CREATE = "customer.lifecycle.create";
    public static final String CUSTOMER_ASSIGN= "customer.lifecycle.assgin";
    public static final String CUSTOMER_ACCOUNTS= "customer.initial.accounts";
    public static final String CUSTOMER_NAMESAKE_ACCOUNTS= "customer.namesake.account";
    public static final String CUSTOMER_DEPOSITS= "customer.lifecycle.deposits";
    public static final String CUSTOMER_WITHRAWAL= "customer.lifecycle.withd";

    /**
     * 重要操作日志 -- 修改组 修改上级 -修改返佣规则
     */
    public static final String LOG_MT4_MODIFY1 = "log.mt4.modfiy1";
    public static final String LOG_MT4_MODIFY2 = "log.mt4.modfiy2";
    public static final String LOG_MT4_MODIFY3 = "log.mt4.modfiy3";
    public static final String LOG_MT4_IBIDMODIFY1 = "log.mt4.ibidModfiy1";
    public static final String LOG_MT4_IBIDMODIFY2 = "log.mt4.ibidModfiy2";
    public static final String LOG_MT4_IBIDMODIFY3 = "log.mt4.ibidModfiy3";
    public static final String LOG_MT4_IBIDMODIFY4 = "log.mt4.ibidModfiy4";
    public static final String LOG_MT4_IBIDMODIFY5 = "log.mt4.ibidModfiy5";
    public static final String LOG_USER_CHANGEUPPER1 = "log.user.changeUpper1";
    public static final String LOG_USER_CHANGEUPPER2 = "log.user.changeUpper2";
    public static final String LOG_USER_CHANGEUPPER3 = "log.user.changeUpper3";
    public static String SHIRO_DEFAULT_SUCCESS_URL ;

    /**
     * 配置内部属性，默认缓存，支持重置
     */
    private static Map<String, String> cfgMap = new HashMap<String, String>();
    private static final String CONST_CONFIG_FILE_NAME = "classpath:configs*.properties";

//    private static final String CONST_CONFIG_EXT_FILE_NAME_PATTERN = "classpath:configs-*.properties";

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader propertiesLoader = null;

    public static final String TO_MAIL_ADDRESS = "open_account_to_email_address";
    private static int userIdleTimeout;

    private static final String EXP_MT4_GROUP = "exp_mt4_group";

    private static final String EXP_ACT_PWD = "exp_act_pwd";

    private static final String IP_LIST_PATH = "ip_list_path";

    /**
     * 初始化配置文件
     */
    public static void load() {
        if (cfgMap.size() == 0) {
            propertiesLoader = new PropertiesLoader(CONST_CONFIG_FILE_NAME);
        }
    }

    static {
        load();
        SHIRO_DEFAULT_SUCCESS_URL = getConfig(SHIRO_DEFAULT_SUCCESS_URL_KEY);
    }

    /**
     * 重置配置文件（重新加载）
     */
    public static void reset() {
        cfgMap.clear();
        load();
    }


    /**
     * 获取支持的多语言代码，如:en_US;zh_CN
     *
     * @return
     */
    public static String getLangCodeSupport() {
        return getConfig(CONST_LANG_CODE_SUPPORT);
    }

    /**
     * 获取货币的前缀
     * @return
     */
    public static String getMoneyOptions() {
        return getConfig(CONST_MONEY_OPTIONS);
    }

    /**
     * 获取默认的多语言代码，如:zh_CN
     *
     * @return
     */
    public static String getLangCodeDefault() {
        return getConfig(CONST_LANG_CODE_DEFAULT);
    }

    /**
     * 获取皮肤COOKIE CODE
     * @return
     */
    public static String getCookieNameSkinCode() {
        return getConfig(CONST_COOKIE_NAME_SKIN_CODE);
    }

    /**
     * 获取皮肤值
     *
     * @return
     */
    public static String getSkinValue() {
        return getConfig(CONSET_PAGE_APP_SKIN_CODE);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM-dd
     *
     * @return
     */
    public static String getDateFormatDate() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_DATE);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM-dd HH:mm:ss.SSS
     *
     * @return
     */
    public static String getDateFormatLongDate() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_DATELONGTIME);
    }

    /**
     * 获取日期格式化的设置，如:hh:mm:ss
     *
     * @return
     */
    public static String getDateFormatTime() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_TIME);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM-dd hh:mm:ss
     *
     * @return
     */
    public static String getDateFormatDatetime() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_DATETIME);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM
     *
     * @return
     */
    public static String getDateFormatShortDate() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_SHORTDATE);
    }

    /**
     * 获取日期格式化的设置，如:hh:mm
     *
     * @return
     */
    public static String getDateFormatShortTime() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_SHORTTIME);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM hh:mm
     *
     * @return
     */
    public static String getDateFormatShortDatetime() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_SHORTDATETIME);
    }

    /**
     * 获取日期格式化的设置，如:yyyy-MM-dd hh:mm
     *
     * @return
     */
    public static String getDateFormatDateShortTime() {
        return getConfig(CONST_DATE_PRE + CONST_DATE_FORMAT_DATESHORTTIME);
    }

    /**
     * 获取当前运行的系统标识
     * @return
     */
    public static String getSystemId(){
        return getConfig(CURRENT_SYSTEM_ID, "crm");
    }

    /**
     * 获取合同附件上传路径
     * @return
     */
    public static String getContractUploadPath() {
        return getConfig(CONTRACT_UPLOAD_PATH);
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = cfgMap.get(key);
        if (value == null) {
            value = propertiesLoader.getProperty(key);
            cfgMap.put(key, value);
        }
        return value;
    }

    public static String getConfig(String key, String defaultVal){
        String value = getConfig(key);
        return StringUtils.isBlank(value) ? defaultVal : value;
    }

    public static String getToEmail() {
        return getConfig(TO_MAIL_ADDRESS);
    }

    public static String getIbCommissionMt4group(){
        return getConfig(IB_COMMISSION_MT4GROUP);
    }

    public static String getClientCommissionMt4group(){
        return getConfig(CLIENT_COMMISSION_MT4GROUP);
    }

    public static String getPayUrl(){
        return getConfig(PAY_URL);
    }

    /**
     * 获取用户两次操作的空闲时间
     * @return
     */
    public static int getUserIdleTimeout() {
        return Integer.valueOf(getConfig(USER_IDLE_TIMEOUT, "" + 600000));
    }

    /**
     * 获取体验MT4组的开头配置
     * @return
     */
    public static String getExpMt4Group() {
        return getConfig(EXP_MT4_GROUP);
    }

    /**
     * 获取体验账户密码
     * @return
     */
    public static String getExpActPwd() {
        return getConfig(EXP_ACT_PWD);
    }

    /**
     * 获取上传IP列表路径
     * @return
     */
    public static String getIpListPath() {
        return getConfig(IP_LIST_PATH);
    }
}
