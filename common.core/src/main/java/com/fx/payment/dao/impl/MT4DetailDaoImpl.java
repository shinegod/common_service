package com.fx.payment.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.payment.dao.IMT4DetailDao;
import com.fx.payment.model.MT4Detail;

@Repository
public class MT4DetailDaoImpl extends BaseDao<MT4Detail> implements IMT4DetailDao {

    public MT4DetailDaoImpl() {
        super(IMT4DetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<MT4Detail> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public MT4Detail getDetailByTradeId(int tradeId) {
		return (MT4Detail)super.findOne("getDetailByTradeId", tradeId);
	}
}