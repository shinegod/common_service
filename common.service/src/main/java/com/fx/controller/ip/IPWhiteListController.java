package com.fx.controller.ip;

import com.fx.controller.base.BaseController;
import com.fx.ip.model.Authorization;
import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IAuthorizationService;
import com.fx.ip.service.IIPWhiteListService;
import com.fx.pojo.JsonResult;
import com.fx.util.DecryptUtils;
import com.fx.util.RequestUtil;
import com.fx.util.StringUtils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Michael on 8/16/2016.
 */
@Controller
public class IPWhiteListController extends BaseController {

    @Autowired
    IIPWhiteListService ipWhiteListService;

    @Autowired
    IAuthorizationService authorizationService;

    @RequestMapping(value = "/login/ipwhitelist", method = RequestMethod.GET)
    @ResponseBody
    public String check() throws Base64DecodingException {
        String queryString = request.getQueryString();
        String msg = "failure";
        String code = "E00003";
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for(Object obj: keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        IPWhiteList ipWhiteList = ipWhiteListService.findBySysTypeAndUserId(params);
        if (ipWhiteList != null) {
            String currentIp = getUserIP();
            if (ipWhiteList.getIp_list().contains(currentIp)) {
                msg = "success";
                code = "S00000";
            }
        }
        JsonResult jsonResult = new JsonResult(msg, code);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    @RequestMapping(value = "/login/test", method = RequestMethod.POST)
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
