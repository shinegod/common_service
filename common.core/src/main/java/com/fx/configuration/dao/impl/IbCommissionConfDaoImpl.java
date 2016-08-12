package com.fx.configuration.dao.impl;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.configuration.dao.IIbCommissionConfDao;
import com.fx.configuration.model.IbCommissionConf;
import com.fx.user.model.User;

@Repository
public class IbCommissionConfDaoImpl extends BaseDao<IbCommissionConf> implements IIbCommissionConfDao {

    public IbCommissionConfDaoImpl() {
        super(IIbCommissionConfDao.class.getName());
    }

	@Override
	public IbCommissionConf getIbCommissionConfByGroup(int groupId) {
		return (IbCommissionConf) super.findOne("getByGroup", groupId);
	}
}