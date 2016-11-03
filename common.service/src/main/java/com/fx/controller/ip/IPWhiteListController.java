package com.fx.controller.ip;

import com.fx.controller.base.BaseController;
import com.fx.enums.ExceptionEnum;
import com.fx.enums.SuccessEnum;
import com.fx.ip.model.Authorization;
import com.fx.ip.model.IPWhiteList;
import com.fx.ip.service.IAuthorizationService;
import com.fx.ip.service.IIPWhiteListService;
import com.fx.pojo.JsonResult;
import com.fx.util.ConfigProperties;
import com.fx.util.DecryptUtils;
import com.fx.util.InputStreamCacher;
import com.fx.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by Michael on 8/16/2016.
 */
@Controller
public class IPWhiteListController extends BaseController {

    @Autowired
    IIPWhiteListService ipWhiteListService;

    @Autowired
    IAuthorizationService authorizationService;

    /**
     * 根据用户ID、系统类型、当前登录IP判断是否有登录权限
     * @return 加密结果
     */
    @RequestMapping(value = "/login/checkIpPermission", method = RequestMethod.GET)
    @ResponseBody
    public String check() {
        String queryString = request.getQueryString();
        String msg = ExceptionEnum.getMsg(ExceptionEnum.QUERY_FAILURE.getCode());
        String code = ExceptionEnum.QUERY_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = null;
        try {
            queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", queryString, authorization.getApp_secret());
            e.printStackTrace();
        }
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for(Object obj: keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        params.put("permission_ip", ConfigProperties.getProperty("allPermission.ip"));
        IPWhiteList permissionIp = ipWhiteListService.findBySysAndUserIdAndPermission(params);
        if (permissionIp != null) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
        }
        IPWhiteList ipWhiteList = ipWhiteListService.findBySysTypeAndUserIdAndIp(params);
        if (ipWhiteList != null) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code, "");
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    /**
     * 根据用户ID、系统类型删除用户所有IP白名单记录
     * @return 加密结果
     */
    @RequestMapping(value = "/login/delAllByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String delAllByUserId() {
        String queryString = request.getQueryString();
        String msg = ExceptionEnum.getMsg(ExceptionEnum.OPERATE_FAILURE.getCode());
        String code = ExceptionEnum.OPERATE_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = null;
        try {
            queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", queryString, authorization.getApp_secret());
            e.printStackTrace();
        }
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for (Object obj : keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        int result = ipWhiteListService.delAllByUserId(params);
        if (result > 0) {
            msg = SuccessEnum.getMsg(SuccessEnum.OPERATE_SUCCESS.getCode());
            code = SuccessEnum.OPERATE_SUCCESS.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code, "");
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    /**
     * 根据IP的ID、系统类型删除单条IP记录
     * @return
     */
    @RequestMapping(value = "/login/delById", method = RequestMethod.POST)
    @ResponseBody
    public String delById() {
        String postParams = null;
        try {
            postParams = RequestUtil.getPostParams(InputStreamCacher.getInputStream());
        } catch (IOException e) {
            logger.error("获取POST请求参数失败，POST请求参数：{}", postParams);
            e.printStackTrace();
        }
        String msg = ExceptionEnum.getMsg(ExceptionEnum.OPERATE_FAILURE.getCode());
        String code = ExceptionEnum.OPERATE_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = null;
        try {
            queryStringResult = DecryptUtils.decode(postParams, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", postParams, authorization.getApp_secret());
            e.printStackTrace();
        }
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for (Object obj : keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        int result = ipWhiteListService.delById(params);
        if (result > 0) {
            msg = SuccessEnum.getMsg(SuccessEnum.OPERATE_SUCCESS.getCode());
            code = SuccessEnum.OPERATE_SUCCESS.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code, "");
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    /**
     * 新增一条IP白名单记录
     * @return
     */
    @RequestMapping(value = "/login/addByUserId", method = RequestMethod.POST)
    @ResponseBody
    public String addByUserId() {
        String postParams = null;
        try {
            postParams = RequestUtil.getPostParams(InputStreamCacher.getInputStream());
        } catch (IOException e) {
            logger.error("获取POST请求参数失败，POST请求参数：{}", postParams);
            e.printStackTrace();
        }
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String postParamsResult = null;
        try {
            postParamsResult = DecryptUtils.decode(postParams, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", postParams, authorization.getApp_secret());
            e.printStackTrace();
        }
        String msg = ExceptionEnum.getMsg(ExceptionEnum.OPERATE_FAILURE.getCode());
        String code = ExceptionEnum.OPERATE_FAILURE.getCode();
        IPWhiteList ipWhiteList = gson.fromJson(postParamsResult, IPWhiteList.class);
        int result = ipWhiteListService.doInsert(ipWhiteList);
        if (result > 0) {
            msg = SuccessEnum.getMsg(SuccessEnum.OPERATE_SUCCESS.getCode());
            code = SuccessEnum.OPERATE_SUCCESS.getCode();
        }
        JsonResult jsonResult = new JsonResult(msg, code, "");
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    /**
     * 根据传来的用户ID列表，查询IP白名单
     * @return
     */
    @RequestMapping(value = "/login/queryByUserIds", method = RequestMethod.GET)
    @ResponseBody
    public String queryByUserIds() {
        String queryString = request.getQueryString();
        String msg = ExceptionEnum.getMsg(ExceptionEnum.QUERY_FAILURE.getCode());
        String code = ExceptionEnum.QUERY_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = null;
        try {
            queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", queryString, authorization.getApp_secret());
            e.printStackTrace();
        }
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for (Object obj : keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        String ids = (String) params.get("ids");
        String[] idsArr = ids.split(",");
        if (idsArr.length > 50) {
            msg = ExceptionEnum.getMsg(ExceptionEnum.PARAMS_TOO_LONG.getCode());
            code = ExceptionEnum.PARAMS_TOO_LONG.getCode();
            JsonResult jsonResult = new JsonResult(msg, code, "");
            return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
        }
        List<IPWhiteList> ipWhiteLists = new ArrayList<>();
        params.remove("ids");
        for (String s : idsArr) {
            params.put("user_id", s);
            IPWhiteList ipWhiteList = ipWhiteListService.queryTop1ByUserId(params);
            if (ipWhiteList != null) {
                ipWhiteLists.add(ipWhiteList);
            }
        }
        String result = "";
        if (ipWhiteLists != null && ipWhiteLists.size() > 0) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
            result = gson.toJson(ipWhiteLists);
        }
        JsonResult jsonResult = new JsonResult(msg, code, result);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

    /**
     * 根据系统类型、用户ID查询IP白名单列表
     * @return
     */
    @RequestMapping(value = "/login/queryByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String queryByUserId() {
        String queryString = request.getQueryString();
        String msg = ExceptionEnum.getMsg(ExceptionEnum.QUERY_FAILURE.getCode());
        String code = ExceptionEnum.QUERY_FAILURE.getCode();
        String app_id = request.getHeader("app_id");
        Authorization authorization = authorizationService.findById(app_id);
        String queryStringResult = null;
        try {
            queryStringResult = DecryptUtils.decode(queryString, authorization.getApp_secret());
        } catch (IOException e) {
            logger.error("加密失败，请求加密内容：{}，加密key：{}", queryString, authorization.getApp_secret());
            e.printStackTrace();
        }
        Map<String, Object> queryParams = RequestUtil.getUrlParams(queryStringResult);
        Map<String, Object> params = new HashMap<>();
        Set keySet = queryParams.keySet();
        for (Object obj : keySet) {
            params.put(obj.toString(), queryParams.get(obj));
        }
        List<IPWhiteList> ipWhiteLists = ipWhiteListService.queryByUserId(params);
        String result = "";
        if (ipWhiteLists != null && ipWhiteLists.size() > 0) {
            msg = SuccessEnum.getMsg(SuccessEnum.QUERY_SUCCESS.getCode());
            code = SuccessEnum.QUERY_SUCCESS.getCode();
            result = gson.toJson(ipWhiteLists);
        }
        JsonResult jsonResult = new JsonResult(msg, code, result);
        return DecryptUtils.encode(gson.toJson(jsonResult), authorization.getApp_secret());
    }

}
