
package com.fx.controller.base;

import com.fx.admin.model.Admin;
import com.fx.common.service.IDictionaryService;
import com.fx.mail.service.IMailSendLogService;
import com.fx.mail.service.IMailServerService;
import com.fx.mail.service.IMailUserService;
import com.fx.util.*;
import com.fx.util.freemarker.FreemarkerUtil;
import com.google.gson.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * 项目抽象controller
 */
public abstract class BaseController extends AbstractCommonController {
	@Autowired
	protected IMailServerService mailServerService;

	@Autowired
	protected IMailUserService mailUserService;

	@Autowired
	protected IMailSendLogService mailSendLogService;

	@Autowired
	protected IDictionaryService dictionaryService;

	protected SimpleDateFormat defaultDateFormat = new SimpleDateFormat(Config.getDateFormatDate());

	protected static final String dateFormat = Config.getDateFormatDatetime();

	protected static final Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
		public Date deserialize(JsonElement json, Type typeOfT,
								JsonDeserializationContext context)
				throws JsonParseException {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			String dateStr = json.getAsString();
			try {
				return format.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return null;
		}
	}).serializeNulls().setDateFormat(dateFormat).create();

	public static final String SUCCESS = "0";
	public static final String ERROR = "1";
	public static final String HANDLE ="2" ;//处理中
	public static final String CREATE_ACCOUNT_ERROR = "2";

	protected Locale locale;

	protected HttpServletRequest request;
	protected HttpServletResponse response;


	/**
	 * init request
	 *
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
							 HttpServletResponse response, Locale locale) {
		this.request = request;
		this.response = response;
		this.locale = locale;
		this.setAppLang();
	}

	protected String getLanguge(){
		String strLangCode = this.locale.toString();// zh_CN;en_US
		if (Config.getLangCodeSupport().indexOf(strLangCode) == -1) {
			strLangCode = Config.getLangCodeDefault();
		}
		return strLangCode;
	}

	protected void setAppLang(){
		String strLangCode = this.locale.toString();// zh_CN;en_US
		String strSkinCode = "";
		if (Config.getLangCodeSupport().indexOf(strLangCode) == -1) {
			strLangCode = Config.getLangCodeDefault();
		}
		Cookie objSkinCode = Cookies.getCookieByName(this.request, Config.getCookieNameSkinCode());
		if (objSkinCode != null && !StringUtils.isBlank(objSkinCode.getValue())) {
			strSkinCode = objSkinCode.getValue();
		} else {
			strSkinCode = Config.getSkinValue();//增加数据库判断
		}
		Map<String, String> appLang = I18N.getLangByCode(strLangCode);
		this.request.setAttribute(Config.CONSET_PAGE_APP_LANG, appLang);
		this.request.setAttribute(Config.CONSET_PAGE_APP_LANG_CODE, strLangCode);
		this.request.setAttribute(Config.CONSET_PAGE_APP_THEME_SKIN, strSkinCode);
		this.request.setAttribute(Config.CONST_MONEY_OPTIONS, Config.getMoneyOptions());
	}

	/**
	 *
	 */
	public BaseController(){
	}


	/**
	 * 获取当前登陆信息
	 *
	 * @return
	 */
	protected Admin getCurrentUserInfo(){
//		Admin loginUserVo = SessionUtil.getLoginSession(request);
		Admin loginUserVo = SessionUtil.getLoginAdmin();
		return loginUserVo;
	}

	/**
	 * 取用户IP地址
	 * @return
	 */
	protected String getUserIP() {
		return HttpServletUtil.getRemoteAddr(request);
	}



	/**
	 * 导入枚举到ftl中
	 * @param enumClass
	 * @param mv
	 */
	@SuppressWarnings("rawtypes")
	protected void importEnum2Ftl(Class enumClass, ModelAndView mv) {
        FreemarkerUtil.importStaticClass2Ftl(enumClass, mv);
    }

	public ModelAndView to404(){
		logger.warn("ready forward 404 page,the request url is [{}]", request.getRequestURL().toString());
		return new ModelAndView("common/404");
	}

	public ModelAndView toError(String errorMsg){
		logger.warn("ready forward error page,the request url is [{}]", request.getRequestURL().toString());
		ModelAndView mav = new ModelAndView("common/error");
		mav.addObject("errorMsg", errorMsg);
		return mav;
	}

	/**
	 * 注册时创建登陆的session信息
	 *
	 * @param request
	 */
	public void setLoginSession(HttpServletRequest request, Admin admin) {
		setLoginSession(admin);
	}

	public void setLoginSession(Admin admin){
		//存登陆信息
		Session session = SecurityUtils.getSubject().getSession();
		//HttpSession session = request.getSession(true);
		//存session
		session.setAttribute(AdminConstrants.KEY_LOGIN_ADMIN_4_SESSION, admin);
	}

	public void removeLoginSession(){
		//删登陆信息
//		HttpSession session = request.getSession(true);
		Session session = SecurityUtils.getSubject().getSession();
		//删session
		session.removeAttribute(AdminConstrants.KEY_LOGIN_ADMIN_4_SESSION);
	}

	public void removeLoginSession(HttpServletRequest request) {
		removeLoginSession();
	}

	protected String getUserName(String enName, String cnName) {
		String userName = "";
		if (!StringUtils.isBlank(enName) && !StringUtils.isBlank(cnName)) {
			userName = enName + "-" + cnName;
		} else if (StringUtils.isBlank(enName)) {
			userName = cnName == null ? "" : cnName;
		} else if (StringUtils.isBlank(cnName)) {
			userName = enName == null ? "" : enName;
		}
		return userName;
	}
	public static String null2Space(String str){
		return str == null ? "":str;
	}

	public String getBodyString(BufferedReader br) {
		String inputLine;
		String str = "";
		try {
			while ((inputLine = br.readLine()) != null) {
				str += inputLine;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return str;
	}
}
