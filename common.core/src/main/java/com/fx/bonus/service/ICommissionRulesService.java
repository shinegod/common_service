package com.fx.bonus.service;

import com.fx.bonus.model.CommissionRules;

import mybatis.framework.core.service.IValueObjectService;

import java.util.HashMap;
import java.util.List;

public interface ICommissionRulesService extends IValueObjectService<CommissionRules> {

	List<CommissionRules> findAllByStatus(int typeid);
	public void updatestaById(int id);
	public List<CommissionRules> findAllByStatus();

	public List<CommissionRules> findByDataSourceId(int dataSourceId);
	CommissionRules getCommissionRulesByParam(HashMap<String, Object> params);
	List<CommissionRules> getCommissionRulesByUid(HashMap<String, Object> params);
}