package com.fx.bonus.dao.impl;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.bonus.dao.IEaDeveloperDao;
import com.fx.bonus.model.EaDeveloper;

@Repository
public class EaDeveloperDaoImpl extends BaseDao<EaDeveloper> implements IEaDeveloperDao {

    public EaDeveloperDaoImpl() {
        super(IEaDeveloperDao.class.getName());
    }

	@Override
	public int pageQueryListCount(Map<String, Object> params) {
		
		return (Integer)findOne("pageQueryListCount", params);
	}

	@Override
	public List<EaDeveloper> pageQueryList(int pageNo, int pageSize,
			Map<String, Object> params) {
		int offset = (pageNo-1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		return (List<EaDeveloper>)findList("pageQueryList", params);
	}
	@Override
	public EaDeveloper getByUid(int uid) {
		return (EaDeveloper) super.findOne("getByUid", uid);
	}
	@Override
	public EaDeveloper getByMt4Account(Integer mt4Account) {
		return (EaDeveloper) super.findOne("getByMt4Account", mt4Account);
	}
}