package com.fx.crm.sys.menu.service;

import com.fx.crm.sys.menu.model.Menu;
import mybatis.framework.core.service.IValueObjectService;

import java.util.List;

public interface IMenuService extends IValueObjectService<Menu> {

    /**
     * 通过角色获取功能菜单
     * @param roleId
     * @return
     */
    public  List<Menu> findMenuByPersonAndParentMenu(Integer roleId, String parentMenu);

    List<Menu> findMenuByParentMenu(String parentMenu);
    
    /**
     *  获取可用菜单
     * @return
     */
	public List<Menu> findAllByStatus();

    /**
     *  获取可用菜单 包括用于非显示菜单归类权限
     * @return
     */
    public List<Menu> findAllByStatusAndNoDispaly();
}