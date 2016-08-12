package com.fx.configuration.dao;

import com.fx.configuration.model.IbCommissionConf;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IIbCommissionConfDao extends IValueObjectDao<IbCommissionConf> {

	public IbCommissionConf getIbCommissionConfByGroup(int groupId);
}