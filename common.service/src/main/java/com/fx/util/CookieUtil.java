package com.fx.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {
	
	public static final String COOKIE_USER_NAME = "u____n";
	public static final int MAX_AGE_USER_NAME = 1800;
	
	/**
	 * 塞cookie
	 * @param response
	 * @param domain
	 * @param maxAge
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(HttpServletResponse response,String domain, Integer maxAge,String path, String cookieName, String cookieValue){
		if (StringUtils.isBlank(cookieName) || StringUtils.isBlank(cookieValue)) {
			return;
		}
		Cookie cookie = new Cookie(cookieName,cookieValue);
		if (!StringUtils.isBlank(domain)) {
			cookie.setDomain(domain);
		}
		if (maxAge != null) {
			cookie.setMaxAge(maxAge);
		}
		if (StringUtils.isBlank(path)) {
			cookie.setPath("/");
		}else {
			cookie.setPath(path);
		}
		response.addCookie(cookie);
	}
	
	/**
	 * 取cookie
	 * @param request
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest request,String cookieName){
		String value = "";
		if (StringUtils.isBlank(cookieName)) {
			return value;
		}
		Cookie[] cookie = request.getCookies();
		if (cookie != null) {
			for (int i = 0; i < cookie.length; i++) {
				Cookie cook = cookie[i];
				if(cookieName.equals(cook.getName())){ //获取键 
					value = cook.getValue();
					break;
				}
			}
		}
		return value;
	}
	
	/**
	 * 删cookie
	 * @param response
	 * @param cookieName
	 */
	public static void delCookie(HttpServletResponse response,String cookieName){
		Cookie cookie = new Cookie(cookieName, null); 
		cookie.setMaxAge(0);
		response.addCookie(cookie); 
	}

}
