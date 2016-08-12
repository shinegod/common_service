package com.fx.user.dao.impl;

import com.fx.user.dao.IUserDao;
import com.fx.user.model.User;
import mybatis.framework.core.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl extends BaseDao<User> implements IUserDao {

    public UserDaoImpl() {
        super( IUserDao.class.getName());
    }

	@Override
	public User getByUid(int uid) {
		return (User) super.findOne("getByUid", uid);
	}
	
	@Override
	public int queryCountByCondition(Map<String, Object> params){
		return (Integer)findOne("pageQueryCount", params);
	}

	@Override
	public List<User> queryByCondition(Map<String, Object> params) {
		return findList("pageQueryList", params);
	}

	@Override
	public List<User> getIBUser(int is_ib) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("is_ib", is_ib);
		return findList("queryListByIB", params);
	}

	@Override
	public List<User> pageQueryByConditionByIsIb(Map<String, Object> params) {
		return findList("pageQueryListByIsIb", params);
	}

	@Override
	public int queryCountByConditionByIsIB(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCountByIsIb", params);
	}

	@Override
	public List<User> pageQueryByConditionByIsIbIbId(Map<String, Object> params) {
		return findList("pageQueryListByIsIbIbId", params);
	}

	@Override
	public int queryCountByConditionByIsIBIbId(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCountByIsIbIbId", params);
	}

	@Override
	public int queryCountByConditionByIbId(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCountByIbId", params);
	}

	@Override
	public List<User> pageQueryByConditionByIbId(Map<String, Object> params) {
		return findList("pageQueryListByIbId", params);
	}

    @Override
    public List<User> getParentUsersByRoleId(int roleId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleId", roleId);
        return findList("queryParentUsersByRoleId", params);
    }

	@Override
	public List<User> getIBIdUser(HashMap<String, Object> roleIdMap) {
		return findList("getIBIdUser", roleIdMap);
	}

	@Override
	public int queryEmailRepeat(String email) {
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		return (Integer) findOne("queryEmailRepeat", params);
	}

	@Override
	public void modifyIbUserTypeById(int id, int ibUserType) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("ibUserType",ibUserType);
		super.doUpdate("modifyIbUserTypeById",map);
	}

    @Override
    public void deleteById(User user) {
        super.doUpdate("deleteById",user);
    }

    @Override
	public List<User> getIBIdUser(int ib_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ib_id", ib_id);
		return findList("queryListByIBId", params);
	}

	@Override
	public List<User> getIBIdIBUser(int ib_id, int is_ib) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ib_id", ib_id);
		params.put("is_ib", is_ib);
		int s1=(Integer)params.get("ib_id");
		int s2=(Integer)params.get("is_ib");
		List<User> list=findList("queryListByIBIdIsIB", params);
		return list;
	}

	@Override
	public int queryCountByCondition1(User user) {
		return (Integer)findOne("pageCountList1", user);
	}

	@Override
	public List<User> queryByCondition1(User user) {
		return findList("pageQueryList1", user);
	}

	@Override
	public void updateIbidByUserIdList(User user) {
		 doUpdate("updateIbidByUserIdList", user);
	}
}