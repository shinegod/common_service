package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.IBCommissionAccountDetail;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IIBCommissionAccountDetailDao extends IValueObjectDao<IBCommissionAccountDetail> {

	public int queryCountByCondition(HashMap<String, Object> dateRange);

	public List<IBCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateRange);
}