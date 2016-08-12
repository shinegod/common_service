package com.fx.bonus.dao.impl;

import com.fx.bonus.dao.ICommisionCoefficientDao;
import com.fx.bonus.model.CommisionCoefficient;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommisionCoefficientDaoImpl extends BaseDao<CommisionCoefficient> implements ICommisionCoefficientDao {

    public CommisionCoefficientDaoImpl() {
        super(ICommisionCoefficientDao.class.getName());
    }

	@Override
	public void deleteByRuleId(int ruleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ruleId", ruleId);
		doDelete("deleteByRuleId", params);
	}

	@Override
	public List<CommisionCoefficient> findByRuleId(int ruleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ruleId", ruleId);
		return findList("findByRuleId", params);
	}

	@Override
	public void updateByRuleId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ruleId", id);
		doUpdate("updateByRuleId", params);
	}

	@Override
	public void updateByRoleId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", id);
		doUpdate("updateByRoleId", params);
	}
	
	@Override
	public List<CommisionCoefficient> getCommisionCoefficientByRuleId(int ruleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ruleId", ruleId);
		return findList("getCommisionCoefficientByRuleId", params);
	}

	@Override
	public List<CommisionCoefficient> getCommisionCoefficientByRoleId(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", id);
		return findList("getCommisionCoefficientByRoleId", params);
	}

	@Override
	public List<CommisionCoefficient> findByRoleId(int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		return findList("findByRoleId", params);
	}

    @Override
    public List<CommisionCoefficient> findByRoleIdAndRuleId(int commissionGroupRulesId, int roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("ruleId", commissionGroupRulesId);
        params.put("roleId", roleId);
        return findList("findByRoleIdAndRuleId", params);
    }

	@Override
	public void updateCoefficientByUidAndRuleId(CommisionCoefficient cc) {
		 doUpdate("updateCoefficientByUidAndRuleId", cc);
	}
}