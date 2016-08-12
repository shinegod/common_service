package com.fx.bonus.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IIBCommissionDao;
import com.fx.bonus.model.IBCommission;

@Repository
public class IBCommissionDaoImpl extends BaseDao<IBCommission> implements IIBCommissionDao {

    public IBCommissionDaoImpl() {
        super(IIBCommissionDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<IBCommission> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public List<IBCommission> getThisMonthIBCommissionByIbId(int ib_id) {
		return super.findList("getThisMonthIBCommissionByIbId", ib_id);
	}

	@Override
	public int queryCountByIbId(Map<String, Object> params) {
		return (Integer)findOne("queryCountByIbId", params);
	}

	@Override
	public List<IBCommission> queryByIbId(Map<String, Object> params) {
		return findList("queryByIbId", params);
	}
}