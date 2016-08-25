package com.fx.controller.ip;

import com.fx.config.ConfigProperties;
import com.fx.controller.base.BaseController;
import com.fx.ip.model.Authorization;
import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IAuthorizationService;
import com.fx.ip.service.IIPWhiteListService;
import com.fx.util.DecryptUtils;
import com.fx.util.Hmac_sha256Util;
import com.fx.util.StringUtil;
import com.fx.util.StringUtils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michael on 8/16/2016.
 */
@Controller
@RequestMapping("/login")
public class IPWhiteListController extends BaseController {

    @Autowired
    IIPWhiteListService ipWhiteListService;

    @Autowired
    IAuthorizationService authorizationService;

    @RequestMapping(value = "/ipWhiteList/testService", method = RequestMethod.POST)
    @ResponseBody
    public String testService() {
        response.setHeader("Access-Control-Allow-Origin", ConfigProperties.getProperty("access.control"));
        String header = request.getHeader("X-Authorization");
        String privateKey = ConfigProperties.getProperty("service.private.key");
        Hmac_sha256Util hmac_sha256Util = new Hmac_sha256Util();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = request.getReader();
        } catch (IOException e) {
            e.printStackTrace();
            return "{code:TYEE0001,msg:'error', value:{}}";
        }
        String data = getBodyString(bufferedReader);
        String resultKey = hmac_sha256Util.signature(data, privateKey);
        // 比较本地加密后与传来的是否相同
        String[] temp = header.split(":");
        String receivedKey = "";
        for (String str : temp) {
            if (StringUtil.equals(str, "Access_token")) {
                continue;
            } else {
                receivedKey = str;
            }
        }
        if (!StringUtil.equals(resultKey, receivedKey)) {
            return "{code:TYEE0001,msg:'error', value:{}}";
        }
        return "{code:TYES0000,msg:'success',value:{}}";
    }

    @RequestMapping(value = "/ipwhitelist", method = RequestMethod.GET)
    @ResponseBody
    public String check() throws Base64DecodingException {
        String queryString = request.getQueryString();
        String flag = "false";
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        Map<String, Object> queryParams = getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for(Object obj: keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        IPWhiteList ipWhiteList = ipWhiteListService.findBySysTypeAndUserId(params);
        if (ipWhiteList != null) {
            String currentIp = getUserIP();
            if (ipWhiteList.getIp_list().contains(currentIp)) {
                flag = "true";
            }
        }
        return DecryptUtils.encode(flag, authorization.getApp_secret());
    }

    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<>();
        if ("".equals(param) || null == param) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test() throws IOException, ServletException {
        StringUtils.toRequestParams(request.getParameterMap());
        StringBuffer info = new java.lang.StringBuffer();
        InputStream in = request.getInputStream();
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] buffer = new byte[1024];
        int iRead;
        while ((iRead = buf.read(buffer)) != -1) {
            info.append(new String(buffer, 0, iRead, "UTF-8"));
        }
        System.out.println(info);
    }


}
