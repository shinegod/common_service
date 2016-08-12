package com.fx.ip.dao.impl;

import com.fx.ip.dao.IIPDBDao;
import com.fx.ip.model.IPDB;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IPDBDaoImpl extends BaseDao<IPDB> implements IIPDBDao {

    public IPDBDaoImpl() {
        super(IIPDBDao.class.getName());
    }

    @Override
    public List<IPDB> getNoLocationIpList() {
        Map<String, Object> params = new HashMap<>();
        return findList("getNoLocationIpList", params);
    }

    @Override
    public int getCountByIP(String ip) {
        return (Integer) findOne("getCountByIP", ip);
    }
}