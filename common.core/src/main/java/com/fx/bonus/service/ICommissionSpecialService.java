package com.fx.bonus.service;

import com.fx.bonus.model.CommissionSpecial;
import mybatis.framework.core.service.IValueObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICommissionSpecialService extends IValueObjectService<CommissionSpecial> {

	List<CommissionSpecial> getCommissionSpecialByuid(int uid, Integer specialUid);

	CommissionSpecial getCommissionSpecialByParams(
			HashMap<String, Object> params);

	List<CommissionSpecial> getCommissionSpecialByuserid(
			Map<String, Object> params);

    public List<CommissionSpecial> getCommissionSpecialByRuleId(String commRuleId);
}