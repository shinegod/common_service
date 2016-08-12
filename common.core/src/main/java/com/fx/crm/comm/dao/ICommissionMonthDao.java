package com.fx.crm.comm.dao;


import com.fx.crm.comm.model.CommissionMonth;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICommissionMonthDao extends IValueObjectDao<CommissionMonth> {


	int queryCountByConditionByIbId(HashMap<String, Object> params);

	List<CommissionMonth> queryByConditionByIbId(HashMap<String, Object> params);


	public Integer queryCountByConditionByStatus(Map<String, Object> params);

	public List<CommissionMonth> queryByCondition(Map<String, Object> params);

	List<CommissionMonth> findCommissionByCondition(Map<String, Object> params);

	List<CommissionMonth> getCommissionMonthByUser(
			CommissionMonth commissionMonth);

	List<CommissionMonth> getCommissionMonthByUserStatus(
			CommissionMonth commissionMonth);

    public int queryCountByUserIdAndMT4Account(HashMap<String, Object> params);

    public List<CommissionMonth> queryByUserIdAndMT4Account(HashMap<String, Object> params);
}