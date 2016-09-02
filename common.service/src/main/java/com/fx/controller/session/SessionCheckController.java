package com.fx.controller.session;

import com.fx.controller.base.BaseController;
import com.fx.enums.ExceptionEnum;
import com.fx.enums.SuccessEnum;
import com.fx.ip.model.Authorization;
import com.fx.ip.service.IAuthorizationService;
import com.fx.pojo.JsonResult;
import com.fx.shiro.UserCacheEntity;
import com.fx.util.CacheMgr;
import com.fx.util.DecryptUtils;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Michael on 9/1/2016.
 */
@Controller
public class SessionCheckController extends BaseController {

    @Autowired
    private IAuthorizationService authorizationService;

    @RequestMapping(value = "/login/sessioncheck/{user_id}", method = RequestMethod.GET)
    @ResponseBody
    public String sessionCheck(@PathVariable String user_id) throws Base64DecodingException {
        String msg = ExceptionEnum.QUERY_FAILURE.getMsg(ExceptionEnum.QUERY_FAILURE.getCode());
        String code = ExceptionEnum.QUERY_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String user_idResult = DecryptUtils.decode(user_id, authorization.getApp_secret());
        UserCacheEntity user = CacheMgr.getUserOnLine(user_idResult);
        if (user != null) {
            msg = SuccessEnum.QUERY_SUCCESS.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

}
