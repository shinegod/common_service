package com.fx.bonus.dao;

import com.fx.bonus.model.EaDeveloperCommission;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface IEaDeveloperCommissionDao extends IValueObjectDao<EaDeveloperCommission> {
	public int queryCountByCondition(Map<String, Object> params);

	public List<EaDeveloperCommission> queryByCondition(Map<String, Object> params);

	public int queryCountByConditionByUid(Map<String, Object> params);

	public List<EaDeveloperCommission> queryByConditionByUid(
			Map<String, Object> params);
}