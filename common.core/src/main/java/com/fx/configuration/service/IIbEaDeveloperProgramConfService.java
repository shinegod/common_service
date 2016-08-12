package com.fx.configuration.service;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.configuration.model.IbEaDeveloperProgramConf;

public interface IIbEaDeveloperProgramConfService extends IValueObjectService<IbEaDeveloperProgramConf> {
	
	public List<IbEaDeveloperProgramConf> getNotDelIbEaDeveloperConfList();

	public Map<Integer, IbEaDeveloperProgramConf> getNotDelIbEaDeveloperConfMap();
	
	public List<IbEaDeveloperProgramConf> getIbEaDeveloperProgramConfByEaId(int eaDeveloperId);
	public PageIterator<IbEaDeveloperProgramConf> pageQueryByConditionByEaDeveloperId(
			Integer id, int pageNo, int pageSize);
}