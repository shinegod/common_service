package com.fx.crm.comm.dao;


import java.util.HashMap;
import java.util.List;


import java.util.Map;


import com.fx.crm.comm.model.CommissionBase;

import mybatis.framework.core.dao.IValueObjectDao;

public interface ICommissionBaseDao extends IValueObjectDao<CommissionBase> {


	int queryCountByConditionByAccount(HashMap<String, Object> params);

	List<CommissionBase> queryByConditionByAccount(
			HashMap<String, Object> params);


	public int queryCountByConditiontAndGroupByTradeCateId(Map<String, Object> params);

	public List<CommissionBase> pageQueryByConditiontAndGroupByTradeCateId(
			Map<String, Object> params);

	public int queryCountByConditiont(Map<String, Object> params);

	public List<CommissionBase> pageQueryByConditiont(Map<String, Object> params);

	public int queryCountByGroupByAgentAccount(Map<String, Object> params);

	public List<CommissionBase> pageQueryGroupByAgentAccount(Map<String, Object> params);
}