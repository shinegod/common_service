package com.fx.payment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.payment.dao.IMT4DetailDao;
import com.fx.payment.model.MT4Detail;
import com.fx.payment.model.TradeDetail;
import com.fx.payment.service.IMT4DetailService;
import com.fx.util.Pagination;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MT4DetailServiceImpl extends BaseVOService<MT4Detail> implements IMT4DetailService {
    @Autowired
    private IMT4DetailDao mT4DetailDao;

	@Override
	public PageIterator<MT4Detail> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize) {
        int totalCount = mT4DetailDao.queryCountByCondition(params);
		
		int offset = (pageNo-1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<MT4Detail> list = mT4DetailDao.queryByCondition(params);
		PageIterator<MT4Detail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}

	@Override
	public MT4Detail getDetailByTradeId(int tradeId) {
		return mT4DetailDao.getDetailByTradeId(tradeId);
	}

	@Override
	public PageIterator<MT4Detail>  queryMt4OperaterDetailList(Integer userId,
			int mt4Account, Pagination pagination) {
		//TODO 组装变量
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("mT4account", mt4Account);
		params.put("status", 2);
		
	    //TOTAL
		int totalCount = mT4DetailDao.queryCountByCondition(params);
		
		int offset = pagination.getOffset();
		int pageSize = pagination.getLimit();
		int pageNo = (offset / pageSize) + 1;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<MT4Detail> mT4DetailList =  mT4DetailDao.queryByCondition(params);
		
		PageIterator<MT4Detail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(mT4DetailList);
		return page;
	}
}