package com.fx.interceptor;

import com.fx.ip.model.Authorization;
import com.fx.ip.service.IAuthorizationService;
import com.fx.logs.model.Logs;
import com.fx.logs.service.ILogsService;
import com.fx.util.*;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Michael on 8/23/2016.
 */
public class CommonServiceInterceptor extends HandlerInterceptorAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CommonServiceInterceptor.class);

    @Autowired
    private IAuthorizationService authorizationService;

    @Autowired
    private ILogsService logsService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request,  response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        new InputStreamCacher(request.getInputStream());
        String postParams = RequestUtil.getPostParams(InputStreamCacher.getInputStream());
        String queryString = request.getQueryString();
        // 从header中取app_id
        String app_id = request.getHeader("app_id");
        LOGGER.info("请求app_id:{}, 请求地址:{}, 请求参数:{}", app_id, request.getRequestURI(), request.getQueryString());
        if (StringUtils.isEmpty(app_id) || StringUtils.isBlank(app_id)) {
            LOGGER.error("请求没有app_id");
            response.sendRedirect("http://"+ ConfigProperties.getProperty("domain.www") + "/exception/message/E00001");
            return false;
        }
        String app_secret = Base64.encode(app_id.getBytes());
        Authorization authorization = authorizationService.findById(app_id);
        if (authorization != null) {
            if (authorization.getApp_secret().equals(app_secret)) {
                Logs log = new Logs();
                if (postParams != null && !StringUtil.equals(postParams, "")) {
                    log.setPost_params(DecryptUtils.decode(postParams, app_secret));
                } else {
                    log.setPost_params("");
                }
                log.setId(UUID.randomUUID().toString());
                log.setApp_id(app_id);
                log.setReq_url(request.getRequestURI());
                if (queryString != null && !StringUtil.equals(queryString, "")) {
                    log.setGet_params(DecryptUtils.decode(queryString, app_secret));
                } else {
                    log.setGet_params("");
                }
                log.setReq_ip(HttpServletUtil.getRemoteAddr(request));
                log.setReq_method(request.getMethod());
                logsService.doInsert(log);
                return true;
            } else {
                response.sendRedirect("http://" + ConfigProperties.getProperty("domain.www") + "/exception/message/E00002");
                return false;
            }
        }
        return false;
    }

}
