package com.fx.ip.service;

import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 8/15/2016.
 */
public interface IIPWhiteListService extends IValueObjectService<IPWhiteList> {
    public IPWhiteList findBySysTypeAndUserIdAndIp(Map<String, Object> params);

    public int delAllByUserId(Map<String, Object> params);

    public int delById(Map<String, Object> params);

    public List<IPWhiteList> queryByUserIds(Map<String, Object> params);

    public IPWhiteList findBySysAndUserIdAndPermission(Map<String, Object> params);

    public List<IPWhiteList> queryByUserId(Map<String, Object> params);
}