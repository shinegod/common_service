package com.fx.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fx.user.model.LoginUserVo;

public class SessionUtil {
	
	public static final String KEY_LOGIN_USERVO_4_SESSION = "loginUserVo";
	
	public static final String KEY_LOGIN_USERNAME_4_SESSION = "loginUserName";
	
	public static LoginUserVo getLoginSession(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(null == session){
			return null;
		}
		return (LoginUserVo)session.getAttribute(KEY_LOGIN_USERVO_4_SESSION);
	}
	
	public static void removeLoginSession(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession(false);
		if(null != session){
			session.removeAttribute(KEY_LOGIN_USERVO_4_SESSION);
			session.removeAttribute(KEY_LOGIN_USERNAME_4_SESSION);
		}
	}
}
