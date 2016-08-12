package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;

import com.fx.bonus.model.EaCommissionAccountDetail;
import mybatis.framework.core.dao.IValueObjectDao;

public interface IEaCommissionAccountDetailDao extends IValueObjectDao<EaCommissionAccountDetail> {

	public int queryCountByCondition(HashMap<String, Object> dateIbId);

	public List<EaCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateIbId);
}