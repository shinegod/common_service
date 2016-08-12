package com.fx.bonus.dao;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.bonus.model.IBCommission;

public interface IIBCommissionDao extends IValueObjectDao<IBCommission> {

	public int queryCountByCondition(Map<String, Object> params);

	public List<IBCommission> queryByCondition(Map<String, Object> params);

	public List<IBCommission> getThisMonthIBCommissionByIbId(int ib_id);

	public int queryCountByIbId(Map<String, Object> params);

	public List<IBCommission> queryByIbId(Map<String, Object> params);
}