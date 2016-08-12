package com.fx.payment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fx.payment.dao.IUserAccountDao;
import com.fx.payment.model.UserAccount;
import com.fx.user.model.UserRegister;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public class UserAccountDaoImpl extends BaseDao<UserAccount> implements IUserAccountDao {

    public UserAccountDaoImpl() {
        super( IUserAccountDao.class.getName());
    }

	@Override
	public UserAccount getUserAccountByUid(int uid) {
		return (UserAccount) findOne("getByUid", uid);
	}

	@Override
	public List<UserAccount> getByUidList(List<Integer> uidList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uidList", uidList);
		return super.findList("getByUidList", params);
	}

	@Override
	public List<UserAccount> getByGroup(int groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		return super.findList("getByGroup", params);
	}

	@Override
	public UserAccount getUserAccountByMT4Account(int mt4Account) {
		return (UserAccount) findOne("getByMT4Account", mt4Account);
	}

	@Override
	public List<UserAccount> getByGroupByIbId(int groupId, int ib_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("ib_id", ib_id);
		return super.findList("getByGroupIbId", params);
	}

	@Override
	public List<UserAccount> pageQueryByConditionMt4Type(
			Map<String, Object> params) {
		
		return findList("pageQueryMt4TypeList", params);
	}

	@Override
	public void deleteByUid(int uid) {
		super.doUpdate("deleteByUid",uid);
	}


}