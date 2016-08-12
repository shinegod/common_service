package com.fx.bonus.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ICashUserCommissionDao;
import com.fx.bonus.model.CashUserCommission;

@Repository
public class CashUserCommissionDaoImpl extends BaseDao<CashUserCommission> implements ICashUserCommissionDao {

    public CashUserCommissionDaoImpl() {
        super(ICashUserCommissionDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<CashUserCommission> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
	@Override
	public int queryCountByUid(Map<String, Object> params) {
		return (Integer)findOne("queryCountByUid", params);
	}
	@Override
	public List<CashUserCommission> queryByUid(Map<String, Object> params) {
		return findList("queryByUid", params);
	}
}