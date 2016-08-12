package com.fx.crm.sys.menu.dao.impl;

import com.fx.crm.sys.menu.dao.IMenuDao;
import com.fx.crm.sys.menu.model.Menu;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import mybatis.framework.core.dao.BaseDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuDaoImpl extends BaseDao<Menu> implements IMenuDao {

    public MenuDaoImpl() {
        super(IMenuDao.class.getName());
    }

    @Override
    public List<Menu> getMenusByRoleId(Integer roleId, String parentMenu) {
        Map<String, Object> params = new HashMap<String, Object>();
        if(roleId != null){
            params.put("roleId", roleId);
        }
        if(StringUtils.isNotBlank(parentMenu)){
            params.put("parentMenu", parentMenu);
        }
        return findList("getMenusByRoleId",params);
    }
}