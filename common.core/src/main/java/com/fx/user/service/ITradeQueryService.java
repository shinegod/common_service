package com.fx.user.service;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.user.model.TradeQuery;

public interface ITradeQueryService extends IValueObjectService<TradeQuery> {
	/**
	 * getByUid	根据Uid获取交易查询信息
	 * @param uid
	 * @return TradeQuery
	 * @exception 
	*/
	public List<TradeQuery> getByUid(int uid);
	public PageIterator<TradeQuery> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize);
}