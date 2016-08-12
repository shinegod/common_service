package com.fx.user.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.user.dao.IMT4TransferDao;
import com.fx.user.model.MT4Transfer;

@Repository
public class MT4TransferDaoImpl extends BaseDao<MT4Transfer> implements IMT4TransferDao {

    public MT4TransferDaoImpl() {
        super(IMT4TransferDao.class.getName());
    }

	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<MT4Transfer> pageQueryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public List<MT4Transfer> queryListByCondition(HashMap<String, Object> params) {
		return findList("queryListByCondition", params);
	}

    @Override
	public List<MT4Transfer> queryListbyUid(Integer uid){
		return findList("queryListByUid", uid);
	}

	@Override
	public List<MT4Transfer> queryListByStatus(HashMap<String, Object> params) {
		return findList("queryListByStatus", params);
	}

	@Override
	public double findTotalByMt4Account(Integer id) {
		return (Double) findOne("queryAccountTotalByMt4Account", id);
	}


}