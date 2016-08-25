package com.fx.util;


import com.fx.admin.model.Admin;
import com.fx.controller.base.AdminConstrants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class SessionUtil {

	public static Admin getLoginSession(HttpServletRequest request){
//		HttpSession session = request.getSession(true);
//		if(null == session){
//			return null;
//		}
//		return (Admin)session.getAttribute(AdminConstrants.KEY_LOGIN_ADMIN_4_SESSION);
		return getLoginAdmin();
	}

	/**
	 * 获取Shiro session中存放的用户信息
	 * @return
	 */
	public static Admin getLoginAdmin(){
		Session session = SecurityUtils.getSubject().getSession();
		return (Admin)session.getAttribute(AdminConstrants.KEY_LOGIN_ADMIN_4_SESSION);
	}

	public static boolean checkPrevQueryParam(HttpServletRequest request,Map<String, String> params){
		HttpSession session = request.getSession(true);
		if(null == session){
			return false;
		}
		if (session.getAttribute("prevParams")==null){
			return false;
		}
		Map<String, String> prevParams=(Map<String, String>) session.getAttribute("prevParams");
		Set<String> keyset=params.keySet();
		for (String key:keyset){
			if (!params.get(key).equals(prevParams.get(key)))
				return false;
		}
		return true;
	}
	public static void setQueryParam(HttpServletRequest request,Map<String, String> params ){
		HttpSession session = request.getSession(true);
		if(null == session){
			return;
		}
		session.setAttribute("prevParams", params);

	}

	public static Map<String,Object> getPrevQueryResult(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		if(null == session){
			return null;
		}
		return (Map<String,Object>)session.getAttribute("prevQueryResult");
	}
	public static void setQueryResult(HttpServletRequest request,Map<String,Object> queryResult ){

		HttpSession session = request.getSession(true);
		if(null == session){
			return;
		}
		session.setAttribute("prevQueryResult", queryResult);

	}

}
