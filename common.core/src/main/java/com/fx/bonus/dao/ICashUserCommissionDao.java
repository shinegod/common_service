package com.fx.bonus.dao;

import java.util.List;
import java.util.Map;

import com.fx.bonus.model.CashUserCommission;
import mybatis.framework.core.dao.IValueObjectDao;

public interface ICashUserCommissionDao extends IValueObjectDao<CashUserCommission> {

	int queryCountByCondition(Map<String, Object> params);

	List<CashUserCommission> queryByCondition(Map<String, Object> params);
	int queryCountByUid(Map<String, Object> params);
	List<CashUserCommission> queryByUid(Map<String, Object> params);
}