package com.fx.admin.dao.impl;

import com.fx.admin.dao.IAdminDao;
import com.fx.admin.enums.AdminStatusEnum;
import com.fx.admin.model.Admin;
import com.fx.user.model.User;
import mybatis.framework.core.dao.BaseDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Lazy(false)
public class AdminDaoImpl extends BaseDao<Admin> implements IAdminDao {

    public AdminDaoImpl() {
        super(IAdminDao.class.getName());
    }

	@Override
	public int deleteByUpdate(Admin admin) {
		return super.doUpdate("deleteByUpdate", admin);
	}

	@Override
	public Admin findByName(String name) {
		return (Admin) super.findOne("selectByName", name);
	}

	@Override
	public int getQueryCount(String name, int status, int roleId) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			params.put("name", name);
		}
		if(null != AdminStatusEnum.valueOf(status)){
			params.put("status", status);
		}
		if(roleId > 0){
			params.put("roleId", roleId);
		}
		return (Integer) findOne("getQueryCount", params);
	}

	@Override
	public List<Admin> queryByCondition(String name, int status, int roleId,
			int offset, int limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			params.put("name", name);
		}
		if(null != AdminStatusEnum.valueOf(status)){
			params.put("status", status);
		}
		if(roleId > 0){
			params.put("roleId", roleId);
		}
		if(offset < 0){
			offset = 0;
		}
		params.put("offset", offset);
		if(limit <= 0){
			limit = 20;
		}
		params.put("limit", limit);
		return findList("queryByCondition", params);
	}

	@Override
	public int getQuerySaleCount(String name, int status) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			params.put("name", name);
		}
		if(null != AdminStatusEnum.valueOf(status)){
			params.put("status", status);
		}
		return (Integer) findOne("getQuerySaleCount", params);
	}

	@Override
	public List<Admin> querySaleByCondition(String name, int status, int offset, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(name)){
			params.put("name", name);
		}
		if(null != AdminStatusEnum.valueOf(status)){
			params.put("status", status);
		}
		if(offset < 0){
			offset = 0;
		}
		params.put("offset", offset);
		if(pageSize <= 0){
			pageSize = 20;
		}
		params.put("limit", pageSize);
		return findList("querySaleByCondition", params);
	}

	@Override
	public List<Admin> findAllSales() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", 5);
		return findList("queryAllSales", params);
	}

	@Override
	public String oldpassById(int id) {
		// TODO Auto-generated method stub
		String pass ="";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Admin> l =  findList("oldpassById", params);
		if(l.size()>0){
			Admin m  =l.get(0);
			pass =(String) m.getPassword();
		}
		return pass;
	}

	@Override
	public List<Admin> getRoleName() {
		// TODO Auto-generated method stub
		List<Admin> list = findAll("selectAll");
		return list;
	}

	@Override
	public List<Admin> getAdminListByRolesId(List<Integer> roleIdList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleIdList", roleIdList);
		return findList("getAdminListByRolesId", params);
	}

	@Override
	public List<User> findAllUsers() {
		return findList("findAllUsers", null);
	}

	@Override
	public Admin findByUserId(int userId) {
		Map map = new HashMap();
		map.put("userId",userId);
		return (Admin)findOne("findAdminByUserId",userId);
	}


}