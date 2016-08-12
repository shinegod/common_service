package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.dao.IEaCommissionAccountDetailDao;
import com.fx.bonus.model.EaCommissionAccountDetail;
import com.fx.bonus.model.IBCommissionAccountDetail;
import com.fx.bonus.service.IEaCommissionAccountDetailService;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EaCommissionAccountDetailServiceImpl extends BaseVOService<EaCommissionAccountDetail> implements IEaCommissionAccountDetailService {
    @Autowired
    private IEaCommissionAccountDetailDao eaCommissionAccountDetailDao;

	@Override
	public PageIterator<EaCommissionAccountDetail> pageQueryByConditionByEaId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize) {
		int totalCount = eaCommissionAccountDetailDao.queryCountByCondition(dateIbId);
		int offset = (pageNo -1) * pageSize;
		dateIbId.put("offset", offset);
		dateIbId.put("limit", pageSize);
		List<EaCommissionAccountDetail> iBCommissionList = eaCommissionAccountDetailDao.queryByCondition(dateIbId);
		PageIterator<EaCommissionAccountDetail> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(iBCommissionList);
		return page;
	}
}