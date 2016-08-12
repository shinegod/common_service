package com.fx.configuration.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.configuration.dao.IIbEaDeveloperConfDao;
import com.fx.configuration.model.IbEaDeveloperConf;
import com.fx.configuration.service.IIbEaDeveloperConfService;

import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IbEaDeveloperConfServiceImpl extends BaseVOService<IbEaDeveloperConf> implements IIbEaDeveloperConfService {
    @Autowired
    private IIbEaDeveloperConfDao ibEaDeveloperConfDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<IbEaDeveloperConf> getNotDelIbEaDeveloperConfList() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		return (List<IbEaDeveloperConf>)ibEaDeveloperConfDao.findList("getNotDelIbEaDeveloperConfList", parameter);
	}

	@Override
	public Map<Integer, IbEaDeveloperConf> getNotDelIbEaDeveloperConfMap() {
		 List<IbEaDeveloperConf> eaConfList = getNotDelIbEaDeveloperConfList();
		 Map<Integer,IbEaDeveloperConf> eaConfMap = new HashMap<Integer, IbEaDeveloperConf>();
		 for (IbEaDeveloperConf vo : eaConfList) {
			 eaConfMap.put(vo.getId(), vo);
		 }
		return eaConfMap;
	}
}