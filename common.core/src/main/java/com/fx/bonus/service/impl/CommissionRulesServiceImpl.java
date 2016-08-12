package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ICommissionRulesDao;
import com.fx.bonus.model.CommissionRules;
import com.fx.bonus.service.ICommissionRulesService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommissionRulesServiceImpl extends BaseVOService<CommissionRules> implements ICommissionRulesService {
    @Autowired
    private ICommissionRulesDao commissionRulesDao;

	@Override
	public List<CommissionRules> findAllByStatus(int typeid) {
		return commissionRulesDao.findAllByStatus(typeid);
	}

	@Override
	public void updatestaById(int id) {
		commissionRulesDao.updatestaById(id);
	}

	@Override
	public List<CommissionRules> findAllByStatus() {
		return commissionRulesDao.findAllByStatus();
	}

	@Override
	public List<CommissionRules> findByDataSourceId(int dataSourceId) {
		return commissionRulesDao.findByDataSourceId(dataSourceId);
	}

	@Override
	public CommissionRules getCommissionRulesByParam(
			HashMap<String, Object> params) {
		return (CommissionRules)commissionRulesDao.findOne("getCommissionRulesByParam", params);
	}

	@Override
	public List<CommissionRules> getCommissionRulesByUid(
			HashMap<String, Object> params) {
		return commissionRulesDao.findList("getCommissionRulesByUid", params);
	}
}