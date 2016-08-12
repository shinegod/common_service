package com.fx.crm.sys.org.service;

import com.fx.crm.sys.org.model.RoleDatascope;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IRoleDatascopeService extends IValueObjectService<RoleDatascope> {
    List<String> findByRoleId(Integer id);
}