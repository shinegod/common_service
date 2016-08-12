package com.fx.trade.service;

import java.util.ArrayList;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.vo.MT4TradeRecord;

public interface ITradeRecordService extends IValueObjectService<MT4TradeRecord>{
	public ArrayList<MT4TradeRecord> getOpenOrders(boolean isDemo);
	
	public PageIterator<MT4TradeRecord> getOpenOrdersyByUser(int mt4Id, int pageNo, int pageSize, QueryMtT4GroupIdEnum groupIdEnum);
	
	public ArrayList<MT4TradeRecord> getHistoryByUser(int login, String date1,String date2,QueryMtT4GroupIdEnum groupIdEnum);
	
	public ArrayList<MT4TradeRecord> getHistoryByGroup(ArrayList<Integer> clients,String date1, String date2,QueryMtT4GroupIdEnum groupIdEnum);

	public PageIterator<MT4TradeRecord> pageQueryOpenOrder(int pageNo, int pageSize,QueryMtT4GroupIdEnum groupIdEnum);

	public PageIterator<MT4TradeRecord> pageQueryHistoryByUser(int queryId,String queryStartDate, String queryEndDate, int pageNo, int pageSize,QueryMtT4GroupIdEnum groupIdEnum);

	
}
