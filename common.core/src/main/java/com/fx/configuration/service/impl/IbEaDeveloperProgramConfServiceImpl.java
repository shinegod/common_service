package com.fx.configuration.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fx.configuration.dao.IIbEaDeveloperProgramConfDao;
import com.fx.configuration.model.IbEaDeveloperProgramConf;
import com.fx.configuration.service.IIbEaDeveloperProgramConfService;
import com.fx.user.model.User;

@Service
public class IbEaDeveloperProgramConfServiceImpl extends BaseVOService<IbEaDeveloperProgramConf> implements IIbEaDeveloperProgramConfService {
    @Autowired
    private IIbEaDeveloperProgramConfDao ibEaDeveloperConfDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<IbEaDeveloperProgramConf> getNotDelIbEaDeveloperConfList() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		return (List<IbEaDeveloperProgramConf>)ibEaDeveloperConfDao.findList("getNotDelIbEaDeveloperConfList", parameter);
	}

	@Override
	public Map<Integer, IbEaDeveloperProgramConf> getNotDelIbEaDeveloperConfMap() {
		 List<IbEaDeveloperProgramConf> eaConfList = getNotDelIbEaDeveloperConfList();
		 Map<Integer,IbEaDeveloperProgramConf> eaConfMap = new HashMap<Integer, IbEaDeveloperProgramConf>();
		 for (IbEaDeveloperProgramConf vo : eaConfList) {
			 eaConfMap.put(vo.getId(), vo);
		 }
		return eaConfMap;
	}

	@Override
	public List<IbEaDeveloperProgramConf> getIbEaDeveloperProgramConfByEaId(
			int eaDeveloperId) {
		return (List<IbEaDeveloperProgramConf>)ibEaDeveloperConfDao.getIbEaDeveloperProgramConfByEaId(eaDeveloperId);
	}
	@Override
	public PageIterator<IbEaDeveloperProgramConf> pageQueryByConditionByEaDeveloperId(
			Integer id, int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("eaDeveloperId", id);
		int totalCount = ibEaDeveloperConfDao.queryCountByCondition(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		List<IbEaDeveloperProgramConf> ibEaDeveloperProgramConfList = ibEaDeveloperConfDao.queryByCondition(params);
		PageIterator<IbEaDeveloperProgramConf> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(ibEaDeveloperProgramConfList);
		return page;
	}
}