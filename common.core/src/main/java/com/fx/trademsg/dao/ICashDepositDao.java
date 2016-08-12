package com.fx.trademsg.dao;

import java.util.List;
import java.util.Map;

import com.fx.trademsg.model.CashDeposit;
import mybatis.framework.core.dao.IValueObjectDao;

public interface ICashDepositDao extends IValueObjectDao<CashDeposit> {

	int pageQueryListCount(Map<String, Object> params);

	List<CashDeposit> pageQueryList(int pageNo, int pageSize,
			Map<String, Object> params);
}