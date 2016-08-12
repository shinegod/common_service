package com.fx.bonus.service.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.bonus.dao.ICommissionSpecialRulesDao;
import com.fx.bonus.model.CommissionSpecialRules;
import com.fx.bonus.service.ICommissionSpecialRulesService;

@Service
public class CommissionSpecialRulesServiceImpl extends BaseVOService<CommissionSpecialRules> implements ICommissionSpecialRulesService {
    @Autowired
    private ICommissionSpecialRulesDao commissionSpecialRulesDao;

	@Override
	public List<CommissionSpecialRules> getCommissionSpecialRulesById(Integer id) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("specialId", id);
		return commissionSpecialRulesDao.findList("getCommissionSpecialRulesById", params);
	}
}