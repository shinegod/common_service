package com.fx.queryConfig.dao;

import com.fx.queryConfig.model.SysQueryConfig;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ISysQueryConfigDao extends IValueObjectDao<SysQueryConfig> {

    public List<SysQueryConfig> selectByMenuid(int menuId);
}