package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.CashUserCommissionDetail;
import mybatis.framework.core.dao.IValueObjectDao;

public interface ICashUserCommissionDetailDao extends IValueObjectDao<CashUserCommissionDetail> {

	int queryCountByCondition(HashMap<String, Object> dateRange);

	List<CashUserCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange);

	int queryCountByAccount(HashMap<String, Object> dateRange);

	List<CashUserCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange);
			
	
	public List<CashUserCommissionDetail> getCashUserCommissionDetailByUid(
			Integer userId);
	int queryCountByUid(HashMap<String, Object> dateRange);
	List<CashUserCommissionDetail> queryByUid(HashMap<String, Object> dateRange);
	List<CashUserCommissionDetail> getCashUserCommissionDetailByAccount(
			int mt4Account);
	int queryCountByMt4Account(HashMap<String, Object> dateRange);
	List<CashUserCommissionDetail> queryByMt4Account(
			HashMap<String, Object> dateRange);
	int queryCountByUidDate(HashMap<String, Object> dateRange);
	List<CashUserCommissionDetail> queryByUidDate(
			HashMap<String, Object> dateRange);
}