package com.fx.bonus.service;

import java.util.HashMap;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.bonus.model.EaCommission;

public interface IEaCommissionService extends IValueObjectService<EaCommission> {
	public int createEaCommission(EaCommission eaCommission);
	public PageIterator<EaCommission> pageQueryByCondition(int status, String queryFrom, String queryTo, int pageNo, int pageSize);
	public PageIterator<EaCommission> pageQueryByConditionByeaId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
}