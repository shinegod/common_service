package com.fx.bonus.dao.impl;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IEaCommissionAccountDetailDao;
import com.fx.bonus.model.EaCommissionAccountDetail;

@Repository
public class EaCommissionAccountDetailDaoImpl extends BaseDao<EaCommissionAccountDetail> implements IEaCommissionAccountDetailDao {

    public EaCommissionAccountDetailDaoImpl() {
        super(IEaCommissionAccountDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(HashMap<String, Object> dateIbId) {
		return (Integer)findOne("pageQueryCount", dateIbId);
	}

	@Override
	public List<EaCommissionAccountDetail> queryByCondition(
			HashMap<String, Object> dateIbId) {
		return findList("pageQueryList", dateIbId);
	}
}