package com.fx.configuration.service;

import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.configuration.model.IbEaDeveloperConf;

public interface IIbEaDeveloperConfService extends IValueObjectService<IbEaDeveloperConf> {
	
	public List<IbEaDeveloperConf> getNotDelIbEaDeveloperConfList();

	public Map<Integer, IbEaDeveloperConf> getNotDelIbEaDeveloperConfMap();
}