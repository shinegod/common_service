package com.fx.bonus.service;

import java.util.List;

import com.fx.bonus.model.CommissionSpecialRules;

import mybatis.framework.core.service.IValueObjectService;

public interface ICommissionSpecialRulesService extends IValueObjectService<CommissionSpecialRules> {

	List<CommissionSpecialRules> getCommissionSpecialRulesById(Integer id);
}