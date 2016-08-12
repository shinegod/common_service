package com.fx.admin.service.impl;

import com.fx.admin.dao.IAdminDao;
import com.fx.admin.model.Admin;
import com.fx.admin.service.IAdminService;
import com.fx.user.model.User;
import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends BaseVOService<Admin> implements IAdminService {
    @Autowired
    private IAdminDao adminDao;
    
    public AdminServiceImpl() {
    	super();
    }

	@Override
	public int addAdmin(Admin admin) {
		int result = super.doInsert(admin);
		return result;
	}

	@Override
	public int updateAdmin(Admin admin) {
		int result = super.doUpdateById(admin);
		return result;
	}

	@Override
	public int deleteAdmin(Admin admin) {
		int result = adminDao.deleteByUpdate(admin);
		return result;
	}

	@Override
	public List<Admin> findAllAdmin() {
		List<Admin> adminList = super.findAll();
		return adminList;
	}

	@Override
	public Admin findById(int id) {
		Admin admin = super.findById(id);
		return admin;
	}

	@Override
	public Admin findByName(String name) {
		return adminDao.findByName(name);
	}

	@Override
	public PageIterator<Admin> pageQuery(String name, int status, int roleId,
			int pageNo, int pageSize) {
		int totalCount = adminDao.getQueryCount(name, status, roleId);
		int offset = (pageNo -1) * pageSize;
		List<Admin> adminList = adminDao.queryByCondition(name, status, roleId, offset, pageSize);
		PageIterator<Admin> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(adminList);
		return page;
	}

	@Override
	public PageIterator<Admin> pageQuerySale(String name, int status,
			int pageNo, int pageSize) {
		int totalCount = adminDao.getQuerySaleCount(name, status);
		int offset = (pageNo -1) * pageSize;
		List<Admin> adminList = adminDao.querySaleByCondition(name, status, offset, pageSize);
		PageIterator<Admin> page = PageIterator.createInstance(pageNo, pageSize, totalCount);
		page.setData(adminList);
		return page;
	}

	@Override
	public List<Admin> findAllSales() {
		return adminDao.findAllSales();
	}

	@Override
	public String oldpassById(int id) {
		// TODO Auto-generated method stub
		return adminDao.oldpassById(id);
	}

	@Override
	public List<Admin> getRoleName() {
		// TODO Auto-generated method stub
		return adminDao.getRoleName();
	}

	@Override
	public List<Admin> getAdminListByRolesId(List<Integer> roleIdList) {
		return adminDao.getAdminListByRolesId(roleIdList);
	}

	@Override
	public List<User> findAllUsers() {
		return adminDao.findAllUsers();
	}

	@Override
	public Admin findByUserId(int userId) {
		return adminDao.findByUserId(userId);
	}

	@Override
	public Admin findByNameOrEmail(String name) {
		return (Admin) adminDao.findOne("selectByNameOrEmail", name);
	}

	@Override
	public List<Admin> getAdminListByNameLike(String login) {
		String name = "%"+login+"%";
		return adminDao.findList("getAdminListByNameLike", name);
	}

}