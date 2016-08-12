package com.fx.bonus.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.ICashUserCommissionDetailDao;
import com.fx.bonus.model.CashUserCommissionDetail;

@Repository
public class CashUserCommissionDetailDaoImpl extends BaseDao<CashUserCommissionDetail> implements ICashUserCommissionDetailDao {

    public CashUserCommissionDetailDaoImpl() {
        super(ICashUserCommissionDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(HashMap<String, Object> dateRange) {
		return (Integer)findOne("pageQueryCount", dateRange);
	}

	@Override
	public List<CashUserCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange) {
		return findList("pageQueryList", dateRange);
	}

	@Override
	public int queryCountByAccount(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByAccount", dateRange);
	}

	@Override
	public List<CashUserCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange) {
		return findList("queryByAccount", dateRange);
	}
	
	@Override
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByUid(
			Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", userId);
		return findList("getCashUserCommissionDetailByUid", params);
	}
	@Override
	public int queryCountByUid(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByUid", dateRange);
	}
	@Override
	public List<CashUserCommissionDetail> queryByUid(
			HashMap<String, Object> dateRange) {
		return findList("queryByUid", dateRange);
	}
	@Override
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByAccount(
			int mt4Account) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mt4Account", mt4Account);
		return findList("getCashUserCommissionDetailByAccount", params);
	}
	@Override
	public int queryCountByMt4Account(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByMt4Account", dateRange);
	}
	@Override
	public List<CashUserCommissionDetail> queryByMt4Account(
			HashMap<String, Object> dateRange) {
		return findList("queryByMt4Account", dateRange);
	}
	@Override
	public int queryCountByUidDate(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByUidDate", dateRange);
	}
	@Override
	public List<CashUserCommissionDetail> queryByUidDate(
			HashMap<String, Object> dateRange) {
		return findList("queryByUidDate", dateRange);
	}
}