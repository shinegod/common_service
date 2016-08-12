package com.fx.crm.sys.permission.dao;

import com.fx.crm.sys.permission.model.SysRolePermission;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface ISysRolePermissionDao extends IValueObjectDao<SysRolePermission> {
    int addRolePermission(String insertList, List<SysRolePermission> sysRolePermissionList);

    public List<SysRolePermission> selectDataRuleByMenuId(String menuId,String roleId);
}