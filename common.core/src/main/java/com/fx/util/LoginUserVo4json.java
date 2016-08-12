package com.fx.util;

import com.fx.user.model.LoginUserVo;

public class LoginUserVo4json {
	private boolean boolLogin;
	private String loginUserName;
	private LoginUserVo loginUserVo;
	
	public boolean isBoolLogin() {
		return boolLogin;
	}
	public void setBoolLogin(boolean boolLogin) {
		this.boolLogin = boolLogin;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public LoginUserVo getLoginUserVo() {
		return loginUserVo;
	}
	public void setLoginUserVo(LoginUserVo loginUserVo) {
		this.loginUserVo = loginUserVo;
	}
	
	
}
