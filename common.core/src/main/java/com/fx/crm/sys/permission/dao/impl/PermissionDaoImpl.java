package com.fx.crm.sys.permission.dao.impl;

import com.fx.crm.sys.permission.dao.IPermissionDao;
import com.fx.crm.sys.permission.model.Permission;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionDaoImpl extends BaseDao<Permission> implements IPermissionDao {

    public PermissionDaoImpl() {
        super(IPermissionDao.class.getName());
    }
}