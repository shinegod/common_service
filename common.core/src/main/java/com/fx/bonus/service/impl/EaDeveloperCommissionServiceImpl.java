package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.IEaDeveloperCommissionDao;
import com.fx.bonus.model.EaCommissionDetail;
import com.fx.bonus.model.EaCommission;
import com.fx.bonus.model.EaDeveloperCommission;
import com.fx.bonus.service.IEaDeveloperCommissionService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaDeveloperCommissionServiceImpl extends BaseVOService<EaDeveloperCommission> implements IEaDeveloperCommissionService {
    @Autowired
    private IEaDeveloperCommissionDao eaDeveloperCommissionDao;

	@Override
	public int createEaDeveloperCommission(
			EaDeveloperCommission eaDeveloperCommission) {
		return eaDeveloperCommissionDao.doInsert("insert", eaDeveloperCommission);
	}

	@Override
	public PageIterator<EaDeveloperCommission> pageQueryByCondition(int status,
			String queryFrom, String queryTo, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(status > 0){
			params.put("status", status);
		}
		params.put("queryFrom", queryFrom);
		params.put("queryTo", queryTo);
		int totalCount = eaDeveloperCommissionDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<EaDeveloperCommission> eaDeveloperCommissionList = eaDeveloperCommissionDao.queryByCondition(params);
		PageIterator<EaDeveloperCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(eaDeveloperCommissionList);
		return page;
	}
		public PageIterator<EaDeveloperCommission> pageQueryByUid(Integer userId,
			int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", userId);
		int totalCount = eaDeveloperCommissionDao.queryCountByConditionByUid(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<EaDeveloperCommission> userList = eaDeveloperCommissionDao.queryByConditionByUid(params);
		PageIterator<EaDeveloperCommission> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(userList);
		return page;
	}
}