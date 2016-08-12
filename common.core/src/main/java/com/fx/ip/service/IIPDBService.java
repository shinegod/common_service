package com.fx.ip.service;

import com.fx.ip.model.IPDB;

import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IIPDBService extends IValueObjectService<IPDB> {

	public List<IPDB> queryListByCondition(IPDB ipQuery);

    public List<IPDB> getNoLocationIpList();

    public int getCountByIP(String ip);

	public List<String> queryCountryList();
}