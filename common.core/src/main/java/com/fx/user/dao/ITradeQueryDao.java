package com.fx.user.dao;

import java.util.List;
import java.util.Map;

import com.fx.user.model.TradeQuery;
import com.fx.user.model.User;

import mybatis.framework.core.dao.IValueObjectDao;

public interface ITradeQueryDao extends IValueObjectDao<TradeQuery> {
	/**
	 * getByUid	根据Uid获取交易查询单详细信息
	 * @param uid
	 * @return TradeQuery
	 * @exception 
	*/
	List<TradeQuery> getByUid(int uid);
	public int queryCountByCondition(Map<String, Object> params);
	public List<TradeQuery> queryByCondition(Map<String, Object> params);
}