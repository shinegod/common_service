package com.fx.queryConfig.dao.impl;

import com.fx.queryConfig.dao.ISysQueryConfigDao;
import com.fx.queryConfig.model.SysQueryConfig;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SysQueryConfigDaoImpl extends BaseDao<SysQueryConfig> implements ISysQueryConfigDao {

    public SysQueryConfigDaoImpl() {
        super(ISysQueryConfigDao.class.getName());
    }

    @Override
    public List<SysQueryConfig> selectByMenuid(int menuId) {
        return findList("selectByMenuid",menuId);
    }
}