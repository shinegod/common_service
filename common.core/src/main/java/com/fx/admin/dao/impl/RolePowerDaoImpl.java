package com.fx.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.admin.dao.IRolePowerDao;
import com.fx.admin.model.RolePower;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class RolePowerDaoImpl extends BaseDao<RolePower> implements IRolePowerDao {

    public RolePowerDaoImpl() {
        super(IRolePowerDao.class.getName());
    }

	@Override
	public void deleteByRoleId(int roleId) {
		super.delete("deleteByRoleId", roleId);
	}

	@Override
	public int addRolePowerList(List<RolePower> rolePowerList) {
		if(null == rolePowerList || rolePowerList.size() <= 0){
			return 0;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", rolePowerList);
		return super.doInsertList("insertList", params);
	}

	@Override
	public List<RolePower> findByRoleId(Integer roleId) {
		return findList("selectByRoleId", roleId);
	}
}