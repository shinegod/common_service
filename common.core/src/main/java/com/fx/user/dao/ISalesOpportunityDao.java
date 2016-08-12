package com.fx.user.dao;

import com.fx.user.model.SalesOpportunity;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface ISalesOpportunityDao extends IValueObjectDao<SalesOpportunity> {
    public List<SalesOpportunity> findByUserId(Integer userId);

    public int queryCountByCondition(Map<String, Object> params);

    public List<SalesOpportunity> queryByCondition(Map<String, Object> params);
}