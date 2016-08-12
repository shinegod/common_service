package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.IEaCommissionDao;
import com.fx.bonus.model.EaCommission;
import com.fx.bonus.model.IBCommissionAccountDetail;
import com.fx.bonus.service.IEaCommissionService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaCommissionServiceImpl extends BaseVOService<EaCommission> implements IEaCommissionService {
    @Autowired
    private IEaCommissionDao eaCommissionDao;

	@Override
	public int createEaCommission(EaCommission eaCommission) {
		return eaCommissionDao.doInsert("insert", eaCommission);
	}

	@Override
	public PageIterator<EaCommission> pageQueryByCondition(int status,
			String queryFrom, String queryTo, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		int totalCount = eaCommissionDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<EaCommission> iBCommissionList = eaCommissionDao.queryByCondition(params);
		PageIterator<EaCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}

	@Override
	public PageIterator<EaCommission> pageQueryByConditionByeaId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize) {
		int totalCount = eaCommissionDao.queryCountByCondition(dateIbId);
		int offset = (pageNo -1) * pageSize;
		dateIbId.put("offset", offset);
		dateIbId.put("limit", pageSize);
		List<EaCommission> iBCommissionList = eaCommissionDao.queryByCondition(dateIbId);
		PageIterator<EaCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}