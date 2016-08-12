package com.fx.crm.sys.menu.service.impl;

import com.fx.crm.sys.menu.dao.IMenuDao;
import com.fx.crm.sys.menu.model.Menu;
import com.fx.crm.sys.menu.service.IMenuService;

import mybatis.framework.core.service.BaseVOService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseVOService<Menu> implements IMenuService {
    @Autowired
    private IMenuDao menuDao;

    @Override
    public List<Menu> findMenuByPersonAndParentMenu(Integer roleId, String parentMenu) {
        List<Menu> allMenu = menuDao.getMenusByRoleId(roleId, parentMenu);
        return allMenu;
    }

    @Override
    public List<Menu> findMenuByParentMenu(String parentMenu) {
        return menuDao.findList("selectMenuByParentMenu",parentMenu);
    }

	@Override
	public List<Menu> findAllByStatus() {
		return menuDao.findList("findAllByStatus",0);
	}

    @Override
    public List<Menu> findAllByStatusAndNoDispaly() {
        return  menuDao.findList("findAllByStatusAndNoDispaly",0);
    }

}