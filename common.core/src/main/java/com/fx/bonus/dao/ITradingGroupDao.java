package com.fx.bonus.dao;

import com.fx.bonus.model.TradingGroup;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;
import java.util.Map;

public interface ITradingGroupDao extends IValueObjectDao<TradingGroup> {
    public void deleteTradingGroup(int id);

    public List<TradingGroup> selectTradingGroupBydataSourceId(int dataSourceId);

	public int queryCountByConditionByDataSource(Map<String, Object> params);

	public List<TradingGroup> queryByConditionByDataSource(
			Map<String, Object> params);
}