package com.fx.configuration.service;

import java.util.List;

import mybatis.framework.core.service.IValueObjectService;

import com.fx.configuration.model.IbCommissionConf;

public interface IIbCommissionConfService extends IValueObjectService<IbCommissionConf> {
	
	public List<IbCommissionConf> getNotDelIbCommissionConfList();
	public IbCommissionConf getIbCommissionConfByGroup(int groupId);
}