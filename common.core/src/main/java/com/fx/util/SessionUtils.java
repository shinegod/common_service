package com.fx.util;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


public final class SessionUtils {
	
	protected static final Logger logger = Logger.getLogger(SessionUtils.class);
	
	private static final String SESSION_USER = "session_user";

	private static final String SESSION_VALIDATECODE = "session_validatecode";//验证码
	
	
	private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
	
	
	private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮

  
	 public static void setAttr(HttpServletRequest request,String key,Object value){
		 request.getSession(true).setAttribute(key, value);
	 }
	 
	 
	
	 public static Object getAttr(HttpServletRequest request,String key){
		 return request.getSession(true).getAttribute(key);
	 }
	 
	 
	 public static void removeAttr(HttpServletRequest request,String key){
		 request.getSession(true).removeAttribute(key);
	 }
	 
	
	/* public static void setUser(HttpServletRequest request,SysUser user){
		 request.getSession(true).setAttribute(SESSION_USER, user);
	 }
	 
	 
	
	 public static SysUser getUser(HttpServletRequest request){
		return (SysUser)request.getSession(true).getAttribute(SESSION_USER);
	 }
	 
	
	 public static Integer getUserId(HttpServletRequest request){
		 SysUser user = getUser(request);
		 if(user != null){
			 return user.getId();
		 }
		return null; 
	 }*/
	 
	 
	
	 public static void removeUser(HttpServletRequest request){
		removeAttr(request, SESSION_USER);
	 }
	 
	 
	 
	 public static void setValidateCode(HttpServletRequest request,String validateCode){
		 request.getSession(true).setAttribute(SESSION_VALIDATECODE, validateCode);
	 }
	 
	 
	
	 public static String getValidateCode(HttpServletRequest request){
		return (String)request.getSession(true).getAttribute(SESSION_VALIDATECODE);
	 }
	 
	
	 public static void removeValidateCode(HttpServletRequest request){
		removeAttr(request, SESSION_VALIDATECODE);
	 }
	 
	/* public static boolean isAdmin(HttpServletRequest request){ //判断登录用户是否超级管理员
		 SysUser user =  getUser(request);
		 if(user == null  || user.getSuperAdmin() != SuperAdmin.YES.key){
			 return false;
		 }
		 return true;
	 }*/
	 
	 
	 
	
	 public static void setAccessUrl(HttpServletRequest request,List<String> accessUrls){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_ACCESS_URLS, accessUrls);
	 }
	 
	 

	 public static boolean isAccessUrl(HttpServletRequest request,String url){ 
		 List<String> accessUrls = (List)getAttr(request, SESSION_ACCESS_URLS);
		 if(accessUrls == null ||accessUrls.isEmpty() || !accessUrls.contains(url)){
			 return false;
		 }
		 return true;
	 }
	 
	 
	
	 public static void setMemuBtnMap(HttpServletRequest request,Map<String,List> btnMap){ //判断登录用户是否超级管理员
		 setAttr(request, SESSION_MENUBTN_MAP, btnMap);
	 }
	 
	
	 public static List<String> getMemuBtnListVal(HttpServletRequest request,String menuUri){ //判断登录用户是否超级管理员
		 Map btnMap  = (Map)getAttr(request, SESSION_MENUBTN_MAP);
		 if(btnMap == null || btnMap.isEmpty()){
			 return null;
		 }
		 return (List<String>)btnMap.get(menuUri);
	 }
	 
//		private static final String SESSION_ACCESS_URLS = "session_access_urls"; //系统能够访问的URL
//		
//		
//		private static final String SESSION_MENUBTN_MAP = "session_menubtn_map"; //系统菜单按钮
	
}