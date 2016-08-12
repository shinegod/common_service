package com.fx.payment.service;

import java.util.List;
import java.util.Map;

import com.fx.payment.model.MT4Detail;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IMT4DetailService extends IValueObjectService<MT4Detail> {
	public PageIterator<MT4Detail> pageQueryByCondition(Map<String, Object> params, int pageNo, int pageSize);
	
	public MT4Detail getDetailByTradeId(int tradeId);

	/**
	 * 根据当前用户与mt4账户查询 交易详情
	 * @param userId
	 * @param mt4Account
	 * @param pagination
	 * @return
	 */
	public PageIterator<MT4Detail> queryMt4OperaterDetailList(Integer userId,int mt4Account, Pagination pagination);
}