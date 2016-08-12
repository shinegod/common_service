package com.fx.bonus.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IIBCommissionDetailDao;
import com.fx.bonus.model.IBCommissionDetail;

@Repository
public class IBCommissionDetailDaoImpl extends BaseDao<IBCommissionDetail> implements IIBCommissionDetailDao {

    public IBCommissionDetailDaoImpl() {
        super(IIBCommissionDetailDao.class.getName());
    }

	@Override
	public int queryCountByCondition(HashMap<String, Object> dateRange) {
		return (Integer)findOne("pageQueryCount", dateRange);
	}

	@Override
	public List<IBCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange) {
		return findList("pageQueryList", dateRange);
	}

	@Override
	public int queryCountByAccount(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByAccount", dateRange);
	}

	@Override
	public List<IBCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange) {
		return findList("queryByAccount", dateRange);
	}

	@Override
	public List<IBCommissionDetail> getIBCommissionDetailByUid(int uid,int ib_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("ib_id", ib_id);
		return super.findList("getIBCommissionDetailByUid", params);
	}

	@Override
	public int queryCountByUid(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByUid", dateRange);
	}

	@Override
	public List<IBCommissionDetail> queryByUid(HashMap<String, Object> dateRange) {
		return findList("queryByUid", dateRange);
	}

	@Override
	public int queryCountByIbIdDate(HashMap<String, Object> dateRange) {
		return (Integer)findOne("queryCountByIbIdDate", dateRange);
	}

	@Override
	public List<IBCommissionDetail> queryByIbIdDate(
			HashMap<String, Object> dateRange) {
		return findList("queryByIbIdDate", dateRange);
	}

}