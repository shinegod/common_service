package com.fx.bonus.service;

import java.util.List;
import java.util.Map;

import com.fx.bonus.model.ProfitCommission;

import mybatis.framework.core.service.IValueObjectService;

public interface IProfitCommissionService extends IValueObjectService<ProfitCommission> {
	
	/**
	 * 查询profitCommission
	 * @param params
	 * @return
	 */
	List<ProfitCommission> getProfitCommissionByRole(Map<String, Object> params);

	List<ProfitCommission> getProfitCommissionAmountByRole(
			Map<String, Object> params);

	List<ProfitCommission> getProfitCommissionByPrice(Map<String, Object> params);

	List<ProfitCommission> getProfitCommissionByUserId(
			Map<String, Object> params);

	Integer getCountProfitCommissionByUserId(Map<String, Object> params);

	List<ProfitCommission> getProfitCommissionAmountByRolesall(
			Map<String, Object> params);
}