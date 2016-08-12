package com.fx.crm.sys.menu.dao;

import com.fx.crm.sys.menu.model.Menu;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.List;

public interface IMenuDao extends IValueObjectDao<Menu> {
    /**
     * 通过角色获取功能菜单
     * @param roleId
     * @param parentMenu
     * @return
     */
    public List<Menu> getMenusByRoleId(Integer roleId, String parentMenu);
}