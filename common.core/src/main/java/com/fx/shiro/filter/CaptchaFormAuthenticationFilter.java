/**
 * <p>FileNmae : CaptchaFormAuthenticationFilter.java</p>
 * <p>Created by santos - santos.ding@tainyitechs.com</p>
 * <p>Created on 2015-4-27</p>
 * <p>CopyRright (c) www.tianyitechs.com 2015  </p>
 */
package com.fx.shiro.filter;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fx.shiro.token.UsernamePasswordCaptchaToken;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *  
 * <p>Title : [名称]</p>
 * <p>Description : [描述]</p>
 * <p>Created on  2015-4-27 10:33 </p>
 * <p>Create by santos - santos.ding@tainyitechs.com</p>
 * <p>CopyRright (c) www.tianyitechs.com 2015  </p>
 */
public class CaptchaFormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);
	

    public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_MESSAGE = "shiroLoginFailureMessage";
    
    private String failureKeyAttributeMessage = DEFAULT_ERROR_KEY_ATTRIBUTE_MESSAGE;

    private String utcParam;
    
    

    public String getUtcParam() {
    	return utcParam;
	}

	public void setUtcParam(String utcParam) {
		this.utcParam = utcParam;
	}

	/**
     * 默认的成功地址
     */
    private String defaultSuccessUrl;
    
    public String getDefaultSuccessUrl() {
		return defaultSuccessUrl;
	}

	public void setDefaultSuccessUrl(String defaultSuccessUrl) {
		this.defaultSuccessUrl = defaultSuccessUrl;
	}

    public void setFailureKeyAttributeMessage(String failureKeyAttributeMessage) {
		this.failureKeyAttributeMessage = failureKeyAttributeMessage;
	}
    
    public String getFailureKeyAttributeMessage() {
		return failureKeyAttributeMessage;
	}

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String utc = request.getParameter(getUtcParam());
        if (password==null){
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        return new UsernamePasswordCaptchaToken(username, password.toCharArray(), rememberMe, host,utc);
    }
    
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	logger.debug(getPathWithinApplication(request));
    	if(isLoginRequest(request, response))
    		return false;
		else
			try {
				return !executeLogin(request, response);
			} catch (Exception e) {
				logger.error("{}",e);
			}
    	return false;
    }
    
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String className = ae.getClass().getName();
        request.setAttribute(getFailureKeyAttribute(), className);
        request.setAttribute(getFailureKeyAttributeMessage(), ae.getMessage());
    }
    
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
        	logger.debug("{}",e);
            return onLoginFailure(token, e, request, response);
        }
    }



}
