package com.fx.bonus.service;

import com.fx.bonus.model.EaDeveloperCommission;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IEaDeveloperCommissionService extends IValueObjectService<EaDeveloperCommission> {
	public int createEaDeveloperCommission(EaDeveloperCommission eaDeveloperCommission);
	public PageIterator<EaDeveloperCommission> pageQueryByCondition(int status, String queryFrom, String queryTo, int pageNo, int pageSize);
	
	public PageIterator<EaDeveloperCommission> pageQueryByUid(Integer userId,
															  int pageNo, int pageSize);
}