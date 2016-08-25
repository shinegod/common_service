package com.fx.interceptor;

import com.fx.ip.service.IAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Michael on 8/23/2016.
 */
public class IPWhiteListInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(IPWhiteListInterceptor.class);

    @Autowired
    private IAuthorizationService authorizationService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从header中取app_id
        String app_id = request.getHeader("app_id");
        LOGGER.info("请求app_id:{}, 请求地址:{}, 请求参数:{}", app_id, request.getRequestURI(), request.getQueryString());
        if (StringUtils.isEmpty(app_id) || StringUtils.isBlank(app_id)) {
            LOGGER.error("请求没有app_id");
            response.sendRedirect("http://127.0.0.1:8080/exception/message/E00001");
            return false;
        }
        String app_secret = Base64.encode(app_id.getBytes());
        Authorization authorization = authorizationService.findById(app_id);
        if (authorization != null) {
            if (authorization.getApp_secret().equals(app_secret)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }*/
}
