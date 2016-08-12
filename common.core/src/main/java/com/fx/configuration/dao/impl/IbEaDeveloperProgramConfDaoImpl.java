package com.fx.configuration.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.configuration.dao.IIbEaDeveloperProgramConfDao;
import com.fx.configuration.model.IbEaDeveloperProgramConf;

@Repository
public class IbEaDeveloperProgramConfDaoImpl extends BaseDao<IbEaDeveloperProgramConf> implements IIbEaDeveloperProgramConfDao {

    public IbEaDeveloperProgramConfDaoImpl() {
        super(IIbEaDeveloperProgramConfDao.class.getName());
    }

	@Override
	public List<IbEaDeveloperProgramConf> getIbEaDeveloperProgramConfByEaId(
			int eaDeveloperId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eaDeveloperId", eaDeveloperId);
		return findList("getIbEaDeveloperProgramConfByEaId", params);
	}
	@Override
	public int queryCountByCondition(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCount", params);
	}
	@Override
	public List<IbEaDeveloperProgramConf> queryByCondition(
			Map<String, Object> params) {
		return findList("pageQueryList", params);
	}
}
