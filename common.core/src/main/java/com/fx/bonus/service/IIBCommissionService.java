package com.fx.bonus.service;

import java.util.List;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.bonus.model.Bonus;
import com.fx.bonus.model.IBCommission;

public interface IIBCommissionService extends IValueObjectService<IBCommission> {
	public int createIBCommission(IBCommission ibCommission);
	public PageIterator<IBCommission> pageQueryByConditionByIbId(int ib_id, int status, String queryFrom, String queryTo, int pageNo, int pageSize);
	public List<IBCommission> getThisMonthIBCommissionByIbId(int ib_id);
	public PageIterator<IBCommission> pageQueryByIbId(int ib_id, int pageNo, int pageSize);
	
}