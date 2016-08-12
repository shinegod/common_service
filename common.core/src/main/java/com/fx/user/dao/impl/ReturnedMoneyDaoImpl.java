
package com.fx.user.dao.impl;

import com.fx.user.dao.IReturnedMoneyDao;
import com.fx.user.model.ReturnedMoney;
import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ReturnedMoneyDaoImpl extends BaseDao<ReturnedMoney> implements IReturnedMoneyDao {

    public ReturnedMoneyDaoImpl() {
        super(IReturnedMoneyDao.class.getName());
    }

    @Override
    public List<ReturnedMoney> findByUserId(Integer userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return findList("queryListByUserId", params);
    }

    @Override
    public int queryCountByCondition(Map<String, Object> params) {
        return (Integer)findOne("pageQueryCount", params);
    }

    @Override
    public List<ReturnedMoney> queryByCondition(Map<String, Object> params) {
        return findList("pageQueryList", params);
    }
}