package com.fx.crm.comm.dao.impl;

import com.fx.crm.comm.dao.ICommissionMonthDao;
import com.fx.crm.comm.model.CommissionMonth;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommissionMonthDaoImpl extends BaseDao<CommissionMonth> implements ICommissionMonthDao {

    public CommissionMonthDaoImpl() {
        super(ICommissionMonthDao.class.getName());
    }

	@Override
	public int queryCountByConditionByIbId(HashMap<String, Object> params) {
		return (Integer) super.findOne("queryCountByConditionByIbId", params);
	}

	@Override
	public List<CommissionMonth> queryByConditionByIbId(
			HashMap<String, Object> params) {
		return findList("queryByConditionByIbId", params);
	}

    

	@Override
	public Integer queryCountByConditionByStatus(Map<String, Object> params) {
		return (Integer)super.findOne("queryCountByConditionByStatus", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommissionMonth> queryByCondition(Map<String, Object> params) {
		return super.findList("queryByCondition", params);
	}

	@Override
	public List<CommissionMonth> findCommissionByCondition(
			Map<String, Object> params) {
		return super.findList("findCommissionByCondition", params);
	}

	@Override
	public List<CommissionMonth> getCommissionMonthByUser(
			CommissionMonth commissionMonth) {
		return super.findList("getCommissionMonthByUser", commissionMonth);
	}

	@Override
	public List<CommissionMonth> getCommissionMonthByUserStatus(
			CommissionMonth commissionMonth) {
		return super.findList("getCommissionMonthByUserStatus", commissionMonth);
	}

    @Override
    public int queryCountByUserIdAndMT4Account(HashMap<String, Object> params) {
        return (Integer) super.findOne("queryCountByUserIdAndMT4Account", params);
    }

    @Override
    public List<CommissionMonth> queryByUserIdAndMT4Account(HashMap<String, Object> params) {
        return findList("queryByUserIdAndMT4Account", params);
    }

}