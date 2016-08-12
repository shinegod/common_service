/**
 * <p>FileNmae : UsernamePasswordToken.java</p>
 * <p>Created by santos - santos.ding@tainyitechs.com</p>
 * <p>Created on 2014-4-27</p>
 * <p>CopyRright (c) www.tianyitechs.com 2015  </p>
 */
package com.fx.shiro.token;

/**
 *  
 * <p>
 * Title : 带验证码的用户名密码令牌
 * </p>
 * <p>
 * Description :
 * </p>
 * <p>
 * Created on  2014-4-27 10:35 
 * </p>
 * <p>
 * Create by by santos - santos.ding@tainyitechs.com
 * </p>
 * <p>
 * CopyRright (c)www.tianyitechs.com 2015  
 * </p>
 */
public class UsernamePasswordCaptchaToken extends
		org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;
	private String utc;

	public String getUtc() {
		return utc;
	}

	public void setUtc(String utc) {
		this.utc = utc;
	}

	public UsernamePasswordCaptchaToken() {
		super();
	}

	public UsernamePasswordCaptchaToken(String username, String password,String utc) {
		super(username, password);
		this.utc=utc;
	}

	public UsernamePasswordCaptchaToken(String username, char[] password,
			boolean rememberMe, String host,String utc) {
		super(username, password, rememberMe, host);
		this.utc = utc;
	}
}
