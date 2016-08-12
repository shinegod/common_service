/**
 * 
 */
package com.fx.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fx.config.AppConfigs;
import com.fx.config.ConfigProperties;
import com.fx.util.SessionUtil;
import com.fx.util.UrlEncoderUtil;
import com.fx.user.model.LoginUserVo;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.user.service.IUserService;

/**
 * 
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	protected static final int DEFAULT_PORT = 80;

	@Autowired
	private IUserRegisterService userRegisterService;
	@Autowired
	private IUserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO 用户登录拦截，只使用session或者request中的的Attribute，不要使用cookie
		//session 登陆信息
		LoginUserVo loginUserVo = SessionUtil.getLoginSession(request);
		if (loginUserVo == null || loginUserVo.getUserId() == null || loginUserVo.getUserId().intValue() == 0) {
			this.sendRedirect(request,response);
			return false;
		}
		
		//根据uid获取注册信息
		UserRegister userRegister = userRegisterService.findById(loginUserVo.getUserId());
		if(userRegister == null || userRegister.getId() == null || userRegister.getId().intValue() == 0) {
			this.sendRedirect(request,response);
			return false;
		}
		//比较密码
		if (StringUtils.isBlank(userRegister.getPassword()) || StringUtils.isBlank(loginUserVo.getPassword()) || !userRegister.getPassword().equals(loginUserVo.getPassword())) {
			this.sendRedirect(request,response);
			return false;
		}
		return true;
	}

	private void sendRedirect(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String currentUrl = getForwardUrl(request);
        String defaultRedirectValue = "/";
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("loginUserVo");
        }
		response.sendRedirect("http://"+ConfigProperties.getProperty("domain.myaccount")+"/login?redir="
                        + UrlEncoderUtil.encodeByUtf8(currentUrl, defaultRedirectValue)
        );
		
	}

	/**
     * 获取用户的请求URL，主要用于URL调整
     *
     * @param request
     * @return
     */
    protected String getForwardUrl(HttpServletRequest request) {
        int port = request.getServerPort();
        String servletPath = request.getServletPath();
        if (servletPath == null || "/".equals(servletPath) || "".equals(servletPath)) {
            servletPath = "";
        }
        StringBuilder stringBuilder = new StringBuilder(request.getScheme())
                .append("://").append(request.getServerName()).append(port == DEFAULT_PORT ? "" : ":" + port)
                .append(request.getContextPath()).append(servletPath).append(request.getPathInfo());
        if (request.getQueryString() != null && !request.getQueryString().isEmpty()) {
            stringBuilder.append("?").append(filterHeaderValue(request.getQueryString()));
        }
        return stringBuilder.toString();
    }
    
    public static String filterHeaderValue(String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(value.length());
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= 32 && c < 127) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
