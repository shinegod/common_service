package com.fx.configuration.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.configuration.dao.IIbCommissionConfDao;
import com.fx.configuration.model.IbCommissionConf;
import com.fx.configuration.service.IIbCommissionConfService;

@Service
public class IbCommissionConfServiceImpl extends BaseVOService<IbCommissionConf> implements IIbCommissionConfService {
    @Autowired
    private IIbCommissionConfDao ibCommissionConfDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<IbCommissionConf> getNotDelIbCommissionConfList() {
		Map<String,Object> parameter = new HashMap<String, Object>();
		return (List<IbCommissionConf>)ibCommissionConfDao.findList("getNotDelIbCommissionConfList", parameter);
	}

	@Override
	public IbCommissionConf getIbCommissionConfByGroup(int groupId) {
		return ibCommissionConfDao.getIbCommissionConfByGroup(groupId);
	}
    
}