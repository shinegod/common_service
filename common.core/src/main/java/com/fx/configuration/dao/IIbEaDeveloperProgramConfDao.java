package com.fx.configuration.dao;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.IValueObjectDao;

import com.fx.configuration.model.IbEaDeveloperProgramConf;

public interface IIbEaDeveloperProgramConfDao extends IValueObjectDao<IbEaDeveloperProgramConf> {

	public List<IbEaDeveloperProgramConf> getIbEaDeveloperProgramConfByEaId(int eaDeveloperId);
	public int queryCountByCondition(Map<String, Object> params);
	public List<IbEaDeveloperProgramConf> queryByCondition(
			Map<String, Object> params);
}