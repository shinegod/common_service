package com.fx.ip.service.impl;

import com.fx.ip.dao.IIPDBDao;
import com.fx.ip.model.IPDB;
import com.fx.ip.service.IIPDBService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IPDBServiceImpl extends BaseVOService<IPDB> implements IIPDBService {
    @Autowired
    private IIPDBDao iPDBDao;

	@Override
	public List<IPDB> queryListByCondition(IPDB ipQuery) {
		return iPDBDao.findList("queryListByCondition", ipQuery);
	}

    @Override
    public List<IPDB> getNoLocationIpList() {
        return iPDBDao.getNoLocationIpList();
    }

    @Override
    public int getCountByIP(String ip) {
        return iPDBDao.getCountByIP(ip);
    }

	@Override
	public List<String> queryCountryList() {
		return iPDBDao.findList("queryCountryList", null);
	}
}