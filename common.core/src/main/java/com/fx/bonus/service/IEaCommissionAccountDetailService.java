package com.fx.bonus.service;

import java.util.HashMap;

import com.fx.bonus.model.EaCommissionAccountDetail;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

public interface IEaCommissionAccountDetailService extends IValueObjectService<EaCommissionAccountDetail> {

	public PageIterator<EaCommissionAccountDetail> pageQueryByConditionByEaId(
			HashMap<String, Object> dateIbId, int pageNo, int pageSize);
}