package com.fx.queryConfig.service;

import com.fx.queryConfig.model.SysQueryConfig;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface ISysQueryConfigService extends IValueObjectService<SysQueryConfig> {

    public List<SysQueryConfig> selectByMenuid(int menuId);
}