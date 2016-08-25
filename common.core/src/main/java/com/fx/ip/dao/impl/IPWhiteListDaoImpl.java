package com.fx.ip.dao.impl;

import com.fx.ip.dao.IIPWhiteListDao;
import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Michael on 8/16/2016.
 */
@Repository
public class IPWhiteListDaoImpl extends BaseDao<IPWhiteList> implements IIPWhiteListDao {

    public IPWhiteListDaoImpl() {
        super(IIPWhiteListDao.class.getName());
    }

    @Override
    public IPWhiteList findBySysTypeAndUserId(Map<String, Object> params) {
        return (IPWhiteList) findOne("findBySysTypeAndUserId", params);
    }
}
