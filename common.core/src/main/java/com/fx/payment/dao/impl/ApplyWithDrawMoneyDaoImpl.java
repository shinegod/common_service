package com.fx.payment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.payment.dao.IApplyWithDrawMoneyDao;
import com.fx.payment.model.ApplyWithDrawMoney;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class ApplyWithDrawMoneyDaoImpl extends BaseDao<ApplyWithDrawMoney> implements IApplyWithDrawMoneyDao {

    public ApplyWithDrawMoneyDaoImpl() {
        super( IApplyWithDrawMoneyDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<ApplyWithDrawMoney> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public List<ApplyWithDrawMoney> queryList(int statusq, String userq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusq",statusq);
		params.put("userq",userq);
		return findList("selectwithdraw",params);
	}

	@Override
	public List<ApplyWithDrawMoney> selsectNopage(Map map) {
		return findList("selsectNopage",map);
	}

	@Override
	public List<ApplyWithDrawMoney> queryListMoreTable(int statusq, String userq) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("statusq",statusq);
		params.put("userq",userq);
		return findList("selectwithdrawMoreTable",params);
	}

}