package com.fx.user.dao;

import com.fx.user.model.SalesInfo;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ISalesInfoDao extends IValueObjectDao<SalesInfo> {
    public List<SalesInfo> findByUserId(Integer id);
}