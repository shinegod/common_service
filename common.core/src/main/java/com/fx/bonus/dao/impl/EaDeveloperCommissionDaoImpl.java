package com.fx.bonus.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IEaDeveloperCommissionDao;
import com.fx.bonus.model.EaDeveloperCommission;

@Repository
public class EaDeveloperCommissionDaoImpl extends BaseDao<EaDeveloperCommission> implements IEaDeveloperCommissionDao {

    public EaDeveloperCommissionDaoImpl() {
        super(IEaDeveloperCommissionDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<EaDeveloperCommission> queryByCondition(
			Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
	
		@Override
	public int queryCountByConditionByUid(Map<String, Object> params) {
		return (Integer)findOne("queryCountByConditionByUid", params);
	}

	@Override
	public List<EaDeveloperCommission> queryByConditionByUid(
			Map<String, Object> params) {
		return findList("queryByConditionByUid", params);
	}
}