package com.fx.bonus.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IEaCommissionDetailDao;
import com.fx.bonus.model.EaCommissionDetail;

@Repository
public class EaCommissionDetailDaoImpl extends BaseDao<EaCommissionDetail> implements IEaCommissionDetailDao {

    public EaCommissionDetailDaoImpl() {
        super(IEaCommissionDetailDao.class.getName());
    }
	@Override
	public int queryCountByConditionByAccountEaid(Map<String, Object> params) {
		return (Integer)findOne("queryCountByConditionByAccountEaid", params);
	}
	@Override
	public List<EaCommissionDetail> queryByConditionByAccountEaid(
			Map<String, Object> params) {
		return findList("queryByConditionByAccountEaid", params);
	}
	@Override
	public int queryCountByCondition(HashMap<String, Object> dateRange) {
		return (Integer)findOne("pageQueryCount", dateRange);
	}
	@Override
	public List<EaCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange) {
		return findList("pageQueryList", dateRange);
	}
	@Override
	public int queryCountByAccount(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByAccount", dateRange);
	}
	@Override
	public List<EaCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange) {
		return findList("queryByAccount", dateRange);
	}
}