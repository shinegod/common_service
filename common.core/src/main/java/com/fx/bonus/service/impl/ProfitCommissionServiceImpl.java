package com.fx.bonus.service.impl;

import java.util.List;
import java.util.Map;

import com.fx.bonus.dao.IProfitCommissionDao;
import com.fx.bonus.model.ProfitCommission;
import com.fx.bonus.service.IProfitCommissionService;
import com.fx.payment.model.UserMT4Account;
import com.fx.util.Constants;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitCommissionServiceImpl extends BaseVOService<ProfitCommission> implements IProfitCommissionService {
    @Autowired
    private IProfitCommissionDao profitCommissionDao;

	@Override
	public List<ProfitCommission> getProfitCommissionByRole(
			Map<String, Object> params) {
		return profitCommissionDao.findList("getProfitCommissionByRole", params);
	}

	@Override
	public List<ProfitCommission> getProfitCommissionAmountByRole(
			Map<String, Object> params) {
		ProfitCommission profitCommission = new ProfitCommission();
		profitCommission.getSqlMap().put("params",params);
		profitCommission.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return profitCommissionDao.findList("getProfitCommissionAmountByRole", profitCommission);
	}

	@Override
	public List<ProfitCommission> getProfitCommissionByPrice(
			Map<String, Object> params) {
		return profitCommissionDao.findList("getProfitCommissionByPrice", params);
	}

	@Override
	public List<ProfitCommission> getProfitCommissionByUserId(
			Map<String, Object> params) {
		ProfitCommission profitCommission = new ProfitCommission();
		profitCommission.getSqlMap().put("params",params);
		profitCommission.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return profitCommissionDao.findList("getProfitCommissionByUserId", profitCommission);
	}

	@Override
	public Integer getCountProfitCommissionByUserId(Map<String, Object> params) {
		ProfitCommission profitCommission = new ProfitCommission();
		profitCommission.getSqlMap().put("params",params);
		profitCommission.getSqlMap().put(Constants.SQLMAP_DATASCOPE_KEY, dataScopeFilter("org","u"));
		return (Integer) profitCommissionDao.findOne("getCountProfitCommissionAmountByRole", profitCommission);
	}

	@Override
	public List<ProfitCommission> getProfitCommissionAmountByRolesall(
			Map<String, Object> params) {
		return profitCommissionDao.findList("getProfitCommissionAmountByRolesall", params);
	}
}