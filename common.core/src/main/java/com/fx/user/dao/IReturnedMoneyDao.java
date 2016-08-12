package com.fx.user.dao;

import com.fx.user.model.ReturnedMoney;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IReturnedMoneyDao extends IValueObjectDao<ReturnedMoney> {

    public List<ReturnedMoney> findByUserId(Integer userId);

    public int queryCountByCondition(Map<String, Object> params);

    public List<ReturnedMoney> queryByCondition(Map<String, Object> params);
}