package com.fx.ip.dao;

import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 8/16/2016.
 */
public interface IIPWhiteListDao extends IValueObjectDao<IPWhiteList> {

    public IPWhiteList findBySysTypeAndUserIdAndIp(Map<String, Object> params);

    public int delAllByUserId(Map<String, Object> params);

    public int delById(Map<String, Object> params);

    public List<IPWhiteList> queryByUserIds(Map<String, Object> params);

    public IPWhiteList findBySysAndUserIdAndPermission(Map<String, Object> params);

    public List<IPWhiteList> queryByUserId(Map<String, Object> params);
}
