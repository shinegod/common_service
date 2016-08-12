package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.dao.IIBCommissionAccountDetailDao;
import com.fx.bonus.model.IBCommissionAccountDetail;
import com.fx.bonus.model.IBCommissionDetail;
import com.fx.bonus.service.IIBCommissionAccountDetailService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IBCommissionAccountDetailServiceImpl extends BaseVOService<IBCommissionAccountDetail> implements IIBCommissionAccountDetailService {
    @Autowired
    private IIBCommissionAccountDetailDao iBCommissionAccountDetailDao;

	@Override
	public PageIterator<IBCommissionAccountDetail> pageQueryByConditionByIbId(
			HashMap<String, Object> dateRange, int pageNo, int pageSize) {
		int totalCount = iBCommissionAccountDetailDao.queryCountByCondition(dateRange);
		int offset = (pageNo -1) * pageSize;
		dateRange.put("offset", offset);
		dateRange.put("limit", pageSize);
		List<IBCommissionAccountDetail> iBCommissionList = iBCommissionAccountDetailDao.queryByCondition(dateRange);
		PageIterator<IBCommissionAccountDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}