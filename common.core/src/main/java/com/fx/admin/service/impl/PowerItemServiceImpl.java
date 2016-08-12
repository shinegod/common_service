package com.fx.admin.service.impl;

import java.util.List;

import com.fx.admin.dao.IPowerItemDao;
import com.fx.admin.model.PowerItem;
import com.fx.admin.service.IPowerItemService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerItemServiceImpl extends BaseVOService<PowerItem> implements IPowerItemService {
    @Autowired
    private IPowerItemDao powerItemDao;

	@Override
	public int addPowerItem(PowerItem powerItem) {
		int result = super.doInsert(powerItem);
		return result;
	}

	@Override
	public int updatePowerItem(PowerItem powerItem) {
		int result = super.doUpdateById(powerItem);
		return result;
	}

	@Override
	public int deletePowerItem(PowerItem powerItem) {
		int result = powerItemDao.deleteByUpdate(powerItem);
		return result;
	}

	@Override
	public List<PowerItem> findAllPowerItem() {
		return super.findAll();
	}

	@Override
	public PowerItem findById(int id) {
		return super.findById(id);
	}
}