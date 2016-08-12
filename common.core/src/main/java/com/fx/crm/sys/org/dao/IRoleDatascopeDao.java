package com.fx.crm.sys.org.dao;

import com.fx.crm.sys.org.model.RoleDatascope;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IRoleDatascopeDao extends IValueObjectDao<RoleDatascope> {
    List<RoleDatascope> findByRoleId(Integer id);
}