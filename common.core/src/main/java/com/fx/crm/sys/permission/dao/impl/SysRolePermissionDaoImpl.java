package com.fx.crm.sys.permission.dao.impl;

import com.fx.crm.sys.permission.dao.ISysRolePermissionDao;
import com.fx.crm.sys.permission.model.SysRolePermission;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SysRolePermissionDaoImpl extends BaseDao<SysRolePermission> implements ISysRolePermissionDao {

    public SysRolePermissionDaoImpl() {
        super(ISysRolePermissionDao.class.getName());
    }

    @Override
    public int addRolePermission(String insertList, List<SysRolePermission> sysRolePermissionList) {
        if(null == sysRolePermissionList || sysRolePermissionList.size() <= 0){
            return 0;
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("list", sysRolePermissionList);
        return super.doInsertList(insertList,params);
    }

    @Override
    public List<SysRolePermission> selectDataRuleByMenuId(String menuId,String roleId) {
        menuId = menuId + "!%";
        Map map = new HashMap();
        map.put("menuId",menuId);
        map.put("roleId",roleId);
        return findList("selectDataRuleByMenuId",map);
    }

}