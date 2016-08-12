package com.fx.bonus.service;

import com.fx.bonus.model.CommisionCoefficient;

import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ICommisionCoefficientService extends IValueObjectService<CommisionCoefficient> {

	void deleteByRuleId(int ruleId);

	List<CommisionCoefficient> findByRuleId(int ruleId);

	void updateByRuleId(Integer id);

	void updateByRoleId(Integer id);

	public List<CommisionCoefficient> getCommisionCoefficientByRuleId(int ruleId);

	public List<CommisionCoefficient> getCommisionCoefficientByRoleId(Integer id);

	public List<CommisionCoefficient> findByRoleId(int roleId);

    public List<CommisionCoefficient> findByRoleIdAndRuleId(int commissionGroupRulesId, int roleId);

	CommisionCoefficient findCommisionCoefficientByRoleIdAndRuleId(Integer id,
			int roleId);

	public List<CommisionCoefficient> findByRoleIdList(List<Integer> roleIdList);

	void updateCoefficientByUidAndRuleId(CommisionCoefficient cc);

    CommisionCoefficient findCommisionCoefficientByUserIdAndRuleId(Integer id, Integer id1);
}