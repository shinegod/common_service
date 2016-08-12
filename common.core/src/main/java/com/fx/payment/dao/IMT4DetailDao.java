package com.fx.payment.dao;

import java.util.List;
import java.util.Map;

import com.fx.payment.model.MT4Detail;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IMT4DetailDao extends IValueObjectDao<MT4Detail> {

	public int queryCountByCondition(Map<String, Object> params);

	public List<MT4Detail> queryByCondition(Map<String, Object> params);

	public MT4Detail getDetailByTradeId(int tradeId);
}