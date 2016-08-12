package com.fx.ip.dao;

import com.fx.ip.model.IPDB;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IIPDBDao extends IValueObjectDao<IPDB> {

    public List<IPDB> getNoLocationIpList();

    public int getCountByIP(String ip);
}