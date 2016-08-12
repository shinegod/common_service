package com.fx.user.dao.impl;

import com.fx.user.dao.ISalesInfoDao;
import com.fx.user.model.SalesInfo;
import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SalesInfoDaoImpl extends BaseDao<SalesInfo> implements ISalesInfoDao {

    public SalesInfoDaoImpl() {
        super(ISalesInfoDao.class.getName());
    }

    @Override
    public List<SalesInfo> findByUserId(Integer id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", id);
        return findList("queryListByUserId", params);
    }
}