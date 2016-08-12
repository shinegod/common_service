package com.fx.bonus.service.impl;

import com.fx.bonus.dao.ICommissionSpecialDao;
import com.fx.bonus.model.CommissionSpecial;
import com.fx.bonus.service.ICommissionSpecialService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommissionSpecialServiceImpl extends BaseVOService<CommissionSpecial> implements ICommissionSpecialService {
    @Autowired
    private ICommissionSpecialDao commissionSpecialDao;

	@Override
	public CommissionSpecial getCommissionSpecialByParams(
			HashMap<String, Object> params) {
		return (CommissionSpecial)commissionSpecialDao.findOne("getCommissionSpecialByParams", params);
	}

	@Override
	public List<CommissionSpecial> getCommissionSpecialByuserid(
			Map<String, Object> params) {
		return commissionSpecialDao.findList("getCommissionSpecialByuserid", params);
	}

    @Override
    public List<CommissionSpecial> getCommissionSpecialByRuleId(String commRuleId) {
        Map<String, Object> params = new HashMap<>();
        commRuleId = "%" + commRuleId + "%";
        params.put("ruleId", commRuleId);
        return commissionSpecialDao.findList("getCommissionSpecialByRuleId", params);
    }

    @Override
	public List<CommissionSpecial> getCommissionSpecialByuid(int uid,
			Integer specialUid) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("uid", uid);
		params.put("specialUid", specialUid);
		return commissionSpecialDao.findList("getCommissionSpecialByuid", params);
	}
}