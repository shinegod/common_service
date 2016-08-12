package com.fx.user.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.user.model.MT4Transfer;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IMT4TransferDao extends IValueObjectDao<MT4Transfer> {
	public int queryCountByCondition(Map<String, Object> params);
	public List<MT4Transfer> pageQueryByCondition(Map<String, Object> params);

	public List<MT4Transfer> queryListByCondition(HashMap<String, Object> params);

	public List<MT4Transfer> queryListByStatus(HashMap<String, Object> params);

	List<MT4Transfer> queryListbyUid(Integer uid);

	double findTotalByMt4Account(Integer id);
}