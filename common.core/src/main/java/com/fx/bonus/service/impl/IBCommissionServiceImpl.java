package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.bonus.dao.IIBCommissionDao;
import com.fx.bonus.model.IBCommission;
import com.fx.bonus.service.IIBCommissionService;
import com.fx.user.model.User;

@Service
public class IBCommissionServiceImpl extends BaseVOService<IBCommission> implements IIBCommissionService {
    @Autowired
    private IIBCommissionDao iBCommissionDao;

	@Override
	public int createIBCommission(IBCommission ibCommission) {
		return iBCommissionDao.doInsert("insert", ibCommission);
	}

	@Override
	public PageIterator<IBCommission> pageQueryByConditionByIbId(int ib_id,
			int status, String queryFrom, String queryTo, int pageNo,
			int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		params.put("ib_id", ib_id);
		int totalCount = iBCommissionDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<IBCommission> iBCommissionList = iBCommissionDao.queryByCondition(params);
		PageIterator<IBCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public List<IBCommission> getThisMonthIBCommissionByIbId(int ib_id) {
		return iBCommissionDao.getThisMonthIBCommissionByIbId(ib_id);
	}

	@Override
	public PageIterator<IBCommission> pageQueryByIbId(int ib_id, int pageNo,
			int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ib_id", ib_id);
		int totalCount = iBCommissionDao.queryCountByIbId(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<IBCommission> iBCommissionList = iBCommissionDao.queryByIbId(params);
		PageIterator<IBCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}