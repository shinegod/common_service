package com.fx.bonus.dao;

import com.fx.bonus.model.CommisionCoefficient;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ICommisionCoefficientDao extends IValueObjectDao<CommisionCoefficient> {

	void deleteByRuleId(int ruleId);

	List<CommisionCoefficient> findByRuleId(int ruleId);

	void updateByRuleId(Integer id);

	void updateByRoleId(Integer id);
	
	public List<CommisionCoefficient> getCommisionCoefficientByRuleId(int ruleId);

	public List<CommisionCoefficient> getCommisionCoefficientByRoleId(Integer id);

	public List<CommisionCoefficient> findByRoleId(int roleId);

    public List<CommisionCoefficient> findByRoleIdAndRuleId(int commissionGroupRulesId, int roleId);

	void updateCoefficientByUidAndRuleId(CommisionCoefficient cc);
}