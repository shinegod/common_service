package com.fx.bonus.dao.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IIBCommissionAccountDetailDao;
import com.fx.bonus.model.IBCommissionAccountDetail;

@Repository
public class IBCommissionAccountDetailDaoImpl extends BaseDao<IBCommissionAccountDetail> implements IIBCommissionAccountDetailDao {

    public IBCommissionAccountDetailDaoImpl() {
        super(IIBCommissionAccountDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(HashMap<String, Object> dateRange) {
		return (Integer)findOne("pageQueryCount", dateRange);
	}

	@Override
	public List<IBCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateRange) {
		return findList("pageQueryList", dateRange);
	}
}