package com.fx.trademsg.service.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.trademsg.dao.ICashDepositDao;
import com.fx.trademsg.model.CashDeposit;
import com.fx.trademsg.service.ICashDepositService;

@Service
public class CashDepositServiceImpl extends BaseVOService<CashDeposit> implements ICashDepositService {
    @Autowired
    private ICashDepositDao cashDepositDao;

	@Override
	public PageIterator<CashDeposit> pageQueryByCondition(int pageNo,
			int pageSize, Map<String, Object> params) {
		int totalCount = cashDepositDao.pageQueryListCount(params);
		List<CashDeposit> list =  this.cashDepositDao.pageQueryList(pageNo, pageSize, params);
		PageIterator<CashDeposit> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(list);
		return page;
	}
	
}