package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ICommisionCoefficientDao;
import com.fx.bonus.model.CommisionCoefficient;
import com.fx.bonus.service.ICommisionCoefficientService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CommisionCoefficientServiceImpl extends BaseVOService<CommisionCoefficient> implements ICommisionCoefficientService {
    @Autowired
    private ICommisionCoefficientDao commisionCoefficientDao;


	@Override
	public List<CommisionCoefficient> getCommisionCoefficientByRuleId(int ruleId) {
		return commisionCoefficientDao.getCommisionCoefficientByRuleId(ruleId);
	}

	@Override
	public List<CommisionCoefficient> getCommisionCoefficientByRoleId(Integer id) {
		return commisionCoefficientDao.getCommisionCoefficientByRoleId(id);
	}

	@Override
	public List<CommisionCoefficient> findByRoleId(int roleId) {
		return commisionCoefficientDao.findByRoleId(roleId);
	}

    @Override
    public List<CommisionCoefficient> findByRoleIdAndRuleId(int commissionGroupRulesId, int roleId) {
        return commisionCoefficientDao.findByRoleIdAndRuleId(commissionGroupRulesId, roleId);
    }


    @Override
	public void deleteByRuleId(int ruleId) {
		commisionCoefficientDao.deleteByRuleId(ruleId);
		
	}

	@Override
	public List<CommisionCoefficient> findByRuleId(int ruleId) {
		return commisionCoefficientDao.findByRuleId(ruleId);
	}

	@Override
	public void updateByRuleId(Integer id) {
		commisionCoefficientDao.updateByRuleId(id);

	}

	@Override
	public void updateByRoleId(Integer id) {
		commisionCoefficientDao.updateByRoleId(id);

	}

	@Override
	public CommisionCoefficient findCommisionCoefficientByRoleIdAndRuleId(
			Integer id, int roleId) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("ruleId", id);
		params.put("roleId", roleId);
		return (CommisionCoefficient)commisionCoefficientDao.findOne("findCommisionCoefficientByRoleIdAndRuleId", params);
	}

	@Override
	public List<CommisionCoefficient> findByRoleIdList(List<Integer> roleIdList) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("roleIdList", roleIdList);
		return commisionCoefficientDao.findList("findByRoleIdList",params);
	}

	@Override
	public void updateCoefficientByUidAndRuleId(CommisionCoefficient cc) {
		 commisionCoefficientDao.updateCoefficientByUidAndRuleId(cc);
	}

    @Override
    public CommisionCoefficient findCommisionCoefficientByUserIdAndRuleId(Integer id, Integer userId) {
        HashMap<String,Object> params = new HashMap<String,Object>();
        params.put("ruleId", id);
        params.put("userId", userId);
        return (CommisionCoefficient)commisionCoefficientDao.findOne("findCommisionCoefficientByRoleIdAndRuleId", params);
    }

}