package com.fx.user.dao.impl;

import com.fx.user.dao.ISalesOpportunityDao;
import com.fx.user.model.SalesOpportunity;
import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SalesOpportunityDaoImpl extends BaseDao<SalesOpportunity> implements ISalesOpportunityDao {

    public SalesOpportunityDaoImpl() {
        super(ISalesOpportunityDao.class.getName());
    }

    @Override
    public List<SalesOpportunity> findByUserId(Integer userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return findList("queryListByUserId", params);
    }

    @Override
    public int queryCountByCondition(Map<String, Object> params) {
        return (Integer)findOne("pageQueryCount", params);
    }

    @Override
    public List<SalesOpportunity> queryByCondition(Map<String, Object> params) {
        return findList("pageQueryList", params);
    }
}