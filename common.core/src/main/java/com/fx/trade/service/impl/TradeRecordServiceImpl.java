package com.fx.trade.service.impl;

import java.util.ArrayList;
import java.util.List;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.stereotype.Service;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4TradeRecordUtil;
import com.fx.MT4.vo.MT4TradeRecord;
import com.fx.trade.service.ITradeRecordService;

@Service
public class TradeRecordServiceImpl extends BaseVOService<MT4TradeRecord> implements ITradeRecordService{

	@Override
	public ArrayList<MT4TradeRecord> getOpenOrders(boolean isDemo) {
		return MT4TradeRecordUtil.getOpenOrders(isDemo);
	}
	
	@Override
	public ArrayList<MT4TradeRecord> getHistoryByUser(int login,String date1, String date2,QueryMtT4GroupIdEnum groupIdEnum){
		return MT4TradeRecordUtil.getHistoryByUser(login, date1, date2,groupIdEnum);
	}

	@Override
	public ArrayList<MT4TradeRecord> getHistoryByGroup(ArrayList<Integer> clients, String date1, String date2,QueryMtT4GroupIdEnum groupIdEnum) {
		return MT4TradeRecordUtil.getHistoryByGroup(clients, date1, date2,groupIdEnum);
	}

	@Override
	public PageIterator<MT4TradeRecord> pageQueryHistoryByUser(int queryId,String queryStartDate, String queryEndDate, int pageNo, int pageSize,QueryMtT4GroupIdEnum groupIdEnum) {
		int offset = (pageNo -1) * pageSize;
		List<MT4TradeRecord> HistoryByUser=getHistoryByUser(queryId, queryStartDate, queryEndDate, groupIdEnum);
		int totalCount=HistoryByUser.size();
		PageIterator<MT4TradeRecord> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<MT4TradeRecord> resList;
		
		if (offset+pageSize>totalCount)
			resList=new ArrayList<MT4TradeRecord>(HistoryByUser.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<MT4TradeRecord>(HistoryByUser.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}
	
	@Override
	public PageIterator<MT4TradeRecord> pageQueryOpenOrder(int pageNo, int pageSize,QueryMtT4GroupIdEnum groupIdEnum) {
		int offset = (pageNo -1) * pageSize;
		boolean isDemo=false;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())	
			isDemo=true;
		
		List<MT4TradeRecord> openOrderList=MT4TradeRecordUtil.getOpenOrders(isDemo);
		int totalCount=openOrderList.size();
		PageIterator<MT4TradeRecord> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<MT4TradeRecord> resList;
		
		if (offset+pageSize>totalCount)
			resList=new ArrayList<MT4TradeRecord>(openOrderList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<MT4TradeRecord>(openOrderList.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}
	
	public static void main(String args[]){
		ITradeRecordService tradeRecordService=new TradeRecordServiceImpl();
		PageIterator<MT4TradeRecord> res=tradeRecordService.pageQueryOpenOrder(2,10,QueryMtT4GroupIdEnum.DEMO);
		System.out.println(res.getData().get(0).order);
	}

	@Override
	public PageIterator<MT4TradeRecord> getOpenOrdersyByUser(int mt4Id, int pageNo, int pageSize,QueryMtT4GroupIdEnum groupIdEnum) {
		int offset = (pageNo -1) * pageSize;

		List<MT4TradeRecord> openOrderList=MT4TradeRecordUtil.getOpenOrders(mt4Id,groupIdEnum);
		
		int totalCount=openOrderList.size();
		PageIterator<MT4TradeRecord> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		List<MT4TradeRecord> resList;
		
		if (offset+pageSize>totalCount)
			resList=new ArrayList<MT4TradeRecord>(openOrderList.subList(offset, totalCount));     //只用sublist不会创建新的内存空间，必须new一个
		else 
			resList=new ArrayList<MT4TradeRecord>(openOrderList.subList(offset, offset+pageSize));
		page.setData(resList);
		return page;
	}


}
