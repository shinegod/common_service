package com.fx.bonus.dao.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ICashUserCommissionAccountDetailDao;
import com.fx.bonus.model.CashUserCommissionAccountDetail;
import com.fx.bonus.model.CashUserCommissionDetail;

@Repository
public class CashUserCommissionAccountDetailDaoImpl extends BaseDao<CashUserCommissionAccountDetail> implements ICashUserCommissionAccountDetailDao {

    public CashUserCommissionAccountDetailDaoImpl() {
        super(ICashUserCommissionAccountDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(HashMap<String, Object> dateRange) {
		return (Integer)findOne("pageQueryCount", dateRange);
	}

	@Override
	public List<CashUserCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateRange) {
		return findList("pageQueryList", dateRange);
	}
	@Override
	public int queryCountByUidDate(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByUidDate", dateRange);
	}
	@Override
	public List<CashUserCommissionAccountDetail> queryByUidDate(
			HashMap<String, Object> dateRange) {
		return findList("queryByUidDate", dateRange);
	}
}