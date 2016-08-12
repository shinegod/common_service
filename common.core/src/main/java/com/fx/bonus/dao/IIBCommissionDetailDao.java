package com.fx.bonus.dao;

import java.util.HashMap;
import java.util.List;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.bonus.model.IBCommissionDetail;

public interface IIBCommissionDetailDao extends IValueObjectDao<IBCommissionDetail> {

	public int queryCountByCondition(HashMap<String, Object> dateRange);

	public List<IBCommissionDetail> queryByCondition(
			HashMap<String, Object> dateRange);

	public int queryCountByAccount(HashMap<String, Object> dateRange);

	public List<IBCommissionDetail> queryByAccount(
			HashMap<String, Object> dateRange);

	public List<IBCommissionDetail> getIBCommissionDetailByUid(int uid, int ib_id);

	public int queryCountByUid(HashMap<String, Object> dateRange);

	public List<IBCommissionDetail> queryByUid(HashMap<String, Object> dateRange);

	public int queryCountByIbIdDate(HashMap<String, Object> dateRange);

	public List<IBCommissionDetail> queryByIbIdDate(
			HashMap<String, Object> dateRange);
}