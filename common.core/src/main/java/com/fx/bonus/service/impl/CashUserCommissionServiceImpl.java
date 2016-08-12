package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.ICashUserCommissionDao;
import com.fx.bonus.model.CashUserCommission;
import com.fx.bonus.model.IBCommission;
import com.fx.bonus.service.ICashUserCommissionService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CashUserCommissionServiceImpl extends BaseVOService<CashUserCommission> implements ICashUserCommissionService {
    @Autowired
    private ICashUserCommissionDao cashUserCommissionDao;

	@Override
	public PageIterator<CashUserCommission> pageQueryByConditionByIbId(
			 int status, String queryFrom, String queryTo,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		int totalCount = cashUserCommissionDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CashUserCommission> iBCommissionList = cashUserCommissionDao.queryByCondition(params);
		PageIterator<CashUserCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public PageIterator<CashUserCommission> pageQueryByUid(Integer id,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", id);
		int totalCount = cashUserCommissionDao.queryCountByUid(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<CashUserCommission> iBCommissionList = cashUserCommissionDao.queryByUid(params);
		PageIterator<CashUserCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
	@Override
	public List<CashUserCommission> getCashUserCommissionByUidDate(
			HashMap<String, Object> dateIbId) {
		return cashUserCommissionDao.findList("getCashUserCommissionByUidDate", dateIbId);
	}
}