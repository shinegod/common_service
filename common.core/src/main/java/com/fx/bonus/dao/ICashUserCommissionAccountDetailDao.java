package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.CashUserCommissionAccountDetail;
import com.fx.bonus.model.CashUserCommissionDetail;

import mybatis.framework.core.dao.IValueObjectDao;

public interface ICashUserCommissionAccountDetailDao extends IValueObjectDao<CashUserCommissionAccountDetail> {

	int queryCountByCondition(HashMap<String, Object> dateRange);

	List<CashUserCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateRange);
	int queryCountByUidDate(HashMap<String, Object> dateRange);
	List<CashUserCommissionAccountDetail> queryByUidDate(
			HashMap<String, Object> dateRange);
}