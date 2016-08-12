/**
 * 
 */
package com.fx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fx.config.AppConfigs;
import com.fx.config.ConfigProperties;
import com.fx.enums.LanguageEnum;
import com.fx.util.CookieUtil;
import com.fx.util.LanguageUtil;

import freemarker.ext.servlet.IncludePage;

/**
 *
 */
public class FreemarkerVariablesInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object obj, ModelAndView mv)
			throws Exception {
		setFtlVar(request, response, mv);
	}

	public static void setFtlVar(HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv) {
		if (mv != null) {
			String ctx = request.getContextPath();
			mv.addObject("configs", AppConfigs.getInstance().getConfigs());
			mv.addObject("request", request);
			mv.addObject("ctx", ctx);
			String resourceContextPath = AppConfigs.getInstance().get(
					"webapp.remote_static_resource"); // 远程 静态资源目录
			if (StringUtils.isEmpty(resourceContextPath)) {
				resourceContextPath = ctx + "/resources";
			}
			request.setAttribute("resourceContextPath", resourceContextPath);
			String localRCP = AppConfigs.getInstance().get(
					"webapp.local_static_resource"); // 本地 静态资源目录
			if (StringUtils.isEmpty(localRCP)) {
				localRCP = ctx + "/resources";
			}
			mv.addObject("localRCP", localRCP);
			mv.addObject("include_page", new IncludePage(request, response));
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ ctx + "/";
			mv.addObject("basePath", basePath);
			String domains = "http://"+ConfigProperties.getProperty("domain.www");
			mv.addObject("domainsNzgft", domains);
			String domainsMyAccount = "http://"+ConfigProperties.getProperty("domain.myaccount");
			mv.addObject("domainsMyAccount", domainsMyAccount);
			String _loginUserName = CookieUtil.getCookie(request, CookieUtil.COOKIE_USER_NAME);
			if (!StringUtils.isBlank(_loginUserName)) {
				mv.addObject("_loginUserName", _loginUserName);
			}
			LanguageEnum languageEnum = LanguageUtil.getLanguage(request);
			int language = languageEnum.getValue();
			String file_lang="cn";
			String file_lang2="zh_cn";
			if(language==LanguageEnum.EN.getValue()){
				file_lang="en";
				file_lang2="en";
			}else if(language==LanguageEnum.ZH.getValue()){
				file_lang="zh";
				file_lang2="zh";
			}else if(language==LanguageEnum.ZH_CN.getValue()){
				file_lang="cn";
				file_lang2="zh_cn";
			}
			mv.addObject("file_langs", file_lang);
			mv.addObject("file_langs2", file_lang2);
		}
	}

}
