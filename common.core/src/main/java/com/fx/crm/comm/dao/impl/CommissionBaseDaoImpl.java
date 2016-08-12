package com.fx.crm.comm.dao.impl;


import java.util.HashMap;
import java.util.List;



import java.util.Map;


import com.fx.crm.comm.dao.ICommissionBaseDao;
import com.fx.crm.comm.model.CommissionBase;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class CommissionBaseDaoImpl extends BaseDao<CommissionBase> implements ICommissionBaseDao {

    public CommissionBaseDaoImpl() {
        super(ICommissionBaseDao.class.getName());
    }


	@Override
	public int queryCountByConditionByAccount(HashMap<String, Object> params) {
		return (Integer) super.findOne("queryCountByConditionByAccount", params);
	}

	@Override
	public List<CommissionBase> queryByConditionByAccount(
			HashMap<String, Object> params) {
		return findList("queryByConditionByAccount", params);
	}


	@Override
	public int queryCountByConditiontAndGroupByTradeCateId(
			Map<String, Object> params) {
		
		return (int) super.findOne("queryCountByConditiontAndGroupByTradeCateId", params);
	}

	@Override
	public List<CommissionBase> pageQueryByConditiontAndGroupByTradeCateId(
			Map<String, Object> params) {
		return super.findList("pageQueryByConditiontAndGroupByTradeCateId", params);
	}

	@Override
	public int queryCountByConditiont(Map<String, Object> params) {
		return (int) super.findOne("queryCountByConditiont", params);
	}

	@Override
	public List<CommissionBase> pageQueryByConditiont(Map<String, Object> params) {
		return super.findList("pageQueryByConditiont", params);
	}
	
	@Override
	public int queryCountByGroupByAgentAccount(Map<String, Object> params) {
		return (int) super.findOne("queryCountByGroupByAgentAccount", params);
	}


	@Override
	public List<CommissionBase> pageQueryGroupByAgentAccount(
			Map<String, Object> params) {
		return super.findList("pageQueryGroupByAgentAccount", params);
	}
}