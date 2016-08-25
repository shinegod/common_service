package com.fx.ip.dao;

import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.Map;

/**
 * Created by Michael on 8/16/2016.
 */
public interface IIPWhiteListDao extends IValueObjectDao<IPWhiteList> {

    IPWhiteList findBySysTypeAndUserId(Map<String, Object> params);
}
