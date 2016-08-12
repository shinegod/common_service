package com.fx.bonus.service;

import java.util.List;

import com.fx.bonus.model.TradingGroup;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface ITradingGroupService extends IValueObjectService<TradingGroup> {
	 public void deleteTradingGroup(int id);
	
	/**
	 * 找出某数据源的所有品种组
	 * @param dataSourceId
	 * @return
	 */
	public List<TradingGroup> getTradeGroupByDataSource(int dataSourceId);

	public List<TradingGroup> selectTradingGroupBydataSourceId(int dataSourceId);

	public PageIterator<TradingGroup> pageQueryByDataSource(
			Integer dataSourceId, Pagination pagination);
}