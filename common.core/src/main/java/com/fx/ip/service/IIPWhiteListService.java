package com.fx.ip.service;

import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.service.IValueObjectService;

import java.util.Map;

/**
 * Created by Michael on 8/15/2016.
 */
public interface IIPWhiteListService extends IValueObjectService<IPWhiteList> {
    public IPWhiteList findBySysTypeAndUserId(Map<String, Object> params);
}