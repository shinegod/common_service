package com.fx.bonus.dao;

import java.util.List;
import java.util.Map;

import com.fx.bonus.model.EaCommission;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IEaCommissionDao extends IValueObjectDao<EaCommission> {

	public int queryCountByCondition(Map<String, Object> params);

	public List<EaCommission> queryByCondition(Map<String, Object> params);
}