package com.fx.user.service.impl;

import java.util.List;
import java.util.Map;

import com.fx.payment.model.ApplyWithDrawMoney;
import com.fx.user.dao.ITradeQueryDao;
import com.fx.user.model.TradeQuery;
import com.fx.user.service.ITradeQueryService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeQueryServiceImpl extends BaseVOService<TradeQuery> implements ITradeQueryService {
    @Autowired
    private ITradeQueryDao tradeQueryDao;

	@Override
	public List<TradeQuery> getByUid(int uid) {
		return tradeQueryDao.getByUid(uid);
	}
	@Override
	public PageIterator<TradeQuery> pageQueryByCondition(
			Map<String, Object> params, int pageNo, int pageSize) {
		int totalCount = tradeQueryDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<TradeQuery> list = tradeQueryDao.queryByCondition(params);
		PageIterator<TradeQuery> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}
}