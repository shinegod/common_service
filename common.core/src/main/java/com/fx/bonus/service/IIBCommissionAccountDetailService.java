package com.fx.bonus.service;

import java.util.HashMap;

import com.fx.bonus.model.IBCommissionAccountDetail;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IIBCommissionAccountDetailService extends IValueObjectService<IBCommissionAccountDetail> {
	public PageIterator<IBCommissionAccountDetail> pageQueryByConditionByIbId(HashMap<String, Object> dateRange, int pageNo, int pageSize);
}