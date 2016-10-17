package com.fx.controller.session;

import com.fx.common.mail.MailBaseMessage;
import com.fx.controller.base.BaseController;
import com.fx.enums.ExceptionEnum;
import com.fx.enums.SuccessEnum;
import com.fx.ip.model.Authorization;
import com.fx.ip.service.IAuthorizationService;
import com.fx.pojo.JsonResult;
import com.fx.shiro.UserCacheEntity;
import com.fx.util.*;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 9/1/2016.
 */
@Controller
public class SessionCheckController extends BaseController {

    @Autowired
    private IAuthorizationService authorizationService;

    @RequestMapping(value = "/login/sessioncheck", method = RequestMethod.GET)
    @ResponseBody
    public String sessionCheck() throws Base64DecodingException {
        String msg = ExceptionEnum.getMsg(ExceptionEnum.QUERY_FAILURE.getCode());
        String code = ExceptionEnum.QUERY_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryParams = request.getQueryString();
        String user_idResult = DecryptUtils.decode(queryParams, authorization.getApp_secret());
        UserCacheEntity user = CacheMgr.get(user_idResult);
        if (user != null) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
            // 发送邮件 已经登录
            Map<String, Object> data = new HashMap<>();
            data.put("name", user.getLoginName());
            data.put("date", DateUtil.getCurrentTime());
            data.put("ip", getUserIP());
            MailBaseMessage message = new MailBaseMessage(getUserIP(), user.getLoginName(),
                                                            "754354038@qq.com", data, "系统通知",
                                                            "login_check");
            com.fx.util.mail.MailUtil.sendMail(message);
        }
        JsonResult jsonResult = new JsonResult(msg, code);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    @RequestMapping(value = "/login/putCache/{sys_type}", method = RequestMethod.POST)
    @ResponseBody
    public String putCache(@PathVariable String sys_type) throws IOException, Base64DecodingException {
        String msg, code;
        String postParams = RequestUtil.getPostParams(InputStreamCacher.getInputStream());
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String sys_typeResult = DecryptUtils.decode(sys_type, authorization.getApp_secret());
        String paramsResult = DecryptUtils.decode(postParams, authorization.getApp_secret());
        UserCacheEntity user = gson.fromJson(paramsResult, UserCacheEntity.class);
        if (user != null) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
            String key = sys_typeResult + "_" + user.getUserId();
            CacheMgr.put(key, user);
        } else {
            msg = ExceptionEnum.getMsg(ExceptionEnum.OPERATE_FAILURE.getCode());
            code = ExceptionEnum.OPERATE_FAILURE.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    @RequestMapping(value = "/login/removeCache", method = RequestMethod.POST)
    @ResponseBody
    public String removeCache() throws Base64DecodingException {
        String postParams = request.getQueryString();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String postParamsResult = DecryptUtils.decode(postParams, authorization.getApp_secret());
        CacheMgr.remove(postParamsResult);
        String msg = SuccessEnum.getMsg(SuccessEnum.OPERATE_SUCCESS.getCode());
        String code = SuccessEnum.OPERATE_SUCCESS.getCode();
        JsonResult jsonResult = new JsonResult(msg, code);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

}
