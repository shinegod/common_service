package com.fx.ip.dao.impl;

import com.fx.ip.dao.IIPWhiteListDao;
import com.fx.ip.model.IPWhiteList;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public IPWhiteList findBySysTypeAndUserIdAndIp(Map<String, Object> params) {
        return (IPWhiteList) findOne("findBySysTypeAndUserIdAndIp", params);
    }

    @Override
    public int delAllByUserId(Map<String, Object> params) {
        return doUpdate("delAllByUserId", params);
    }

    @Override
    public int delById(Map<String, Object> params) {
        return doUpdate("delById", params);
    }

    @Override
    public IPWhiteList findBySysAndUserIdAndPermission(Map<String, Object> params) {
        return (IPWhiteList) findOne("findBySysAndUserIdAndPermission", params);
    }

    @Override
    public List<IPWhiteList> queryByUserId(Map<String, Object> params) {
        return findList("queryByUserId", params);
    }

    @Override
    public IPWhiteList queryTop1ByUserId(Map<String, Object> params) {
        return (IPWhiteList) findOne("queryTop1ByUserId", params);
    }
}
