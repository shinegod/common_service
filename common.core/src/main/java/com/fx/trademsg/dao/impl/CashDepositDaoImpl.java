package com.fx.trademsg.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.trademsg.dao.ICashDepositDao;
import com.fx.trademsg.model.CashDeposit;

@Repository
public class CashDepositDaoImpl extends BaseDao<CashDeposit> implements ICashDepositDao {

    public CashDepositDaoImpl() {
        super(ICashDepositDao.class.getName());
    }

	@Override
	public int pageQueryListCount(Map<String, Object> params) {
		return (Integer)findOne("pageQueryListCount", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CashDeposit> pageQueryList(int pageNo, int pageSize,
			Map<String, Object> params) {
		int offset = (pageNo-1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		return (List<CashDeposit>)findList("pageQueryList", params);
	}
}