package com.fx.admin.dao.impl;

import com.fx.admin.dao.IPowerItemDao;
import com.fx.admin.model.PowerItem;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class PowerItemDaoImpl extends BaseDao<PowerItem> implements IPowerItemDao {

    public PowerItemDaoImpl() {
        super(IPowerItemDao.class.getName());
    }

	@Override
	public int deleteByUpdate(PowerItem powerItem) {
		int result = super.doUpdate("", powerItem);
		return result;
	}
}