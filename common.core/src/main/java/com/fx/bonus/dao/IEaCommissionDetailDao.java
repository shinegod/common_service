package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fx.bonus.model.EaCommissionDetail;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IEaCommissionDetailDao extends IValueObjectDao<EaCommissionDetail> {
	public int queryCountByConditionByAccountEaid(Map<String, Object> params);
	public List<EaCommissionDetail> queryByConditionByAccountEaid(
			Map<String, Object> params);
	public int queryCountByCondition(HashMap<String, Object> dateRange);
	public List<EaCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange);
	public int queryCountByAccount(HashMap<String, Object> dateRange);
	public List<EaCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange);
}