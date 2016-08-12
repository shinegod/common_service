package com.fx.bonus.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IEaCommissionDao;
import com.fx.bonus.model.EaCommission;

@Repository
public class EaCommissionDaoImpl extends BaseDao<EaCommission> implements IEaCommissionDao {

    public EaCommissionDaoImpl() {
        super(IEaCommissionDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<EaCommission> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
}