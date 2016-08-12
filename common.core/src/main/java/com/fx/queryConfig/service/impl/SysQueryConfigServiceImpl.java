package com.fx.queryConfig.service.impl;

import com.fx.queryConfig.dao.ISysQueryConfigDao;
import com.fx.queryConfig.model.SysQueryConfig;
import com.fx.queryConfig.service.ISysQueryConfigService;
import mybatis.framework.core.service.BaseVOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysQueryConfigServiceImpl extends BaseVOService<SysQueryConfig> implements ISysQueryConfigService {
    @Autowired
    private ISysQueryConfigDao sysQueryConfigDao;

    @Override
    public List<SysQueryConfig> selectByMenuid(int menuId) {
        return sysQueryConfigDao.selectByMenuid(menuId);
    }
}