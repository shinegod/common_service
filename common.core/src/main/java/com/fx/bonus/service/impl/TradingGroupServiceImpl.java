package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.ITradingGroupDao;
import com.fx.bonus.model.TradingGroup;
import com.fx.bonus.service.ITradingGroupService;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.Pagination;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradingGroupServiceImpl extends BaseVOService<TradingGroup> implements ITradingGroupService {
    @Autowired
    private ITradingGroupDao tradingGroupDao;

	@Override
	public void deleteTradingGroup(int id) {
		// TODO Auto-generated method stub
		tradingGroupDao.deleteTradingGroup(id);
		
	}

	@Override
	public List<TradingGroup> getTradeGroupByDataSource(int dataSourceId) {
		return tradingGroupDao.findList("getTradeGroupByDataSource", dataSourceId);
	}

	@Override
	public List<TradingGroup> selectTradingGroupBydataSourceId(int dataSourceId) {
		return tradingGroupDao.selectTradingGroupBydataSourceId(dataSourceId);
	}

	@Override
	public PageIterator<TradingGroup> pageQueryByDataSource(
			Integer dataSourceId, Pagination pagination) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dataSourceId", dataSourceId);
		int totalCount = tradingGroupDao.queryCountByConditionByDataSource(params);
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<TradingGroup> userMT4AccountList = tradingGroupDao.queryByConditionByDataSource(params);
		PageIterator<TradingGroup> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userMT4AccountList);
		return page;
	}
	
}