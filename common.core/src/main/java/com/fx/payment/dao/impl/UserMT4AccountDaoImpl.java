package com.fx.payment.dao.impl;

import com.fx.payment.dao.IUserMT4AccountDao;
import com.fx.payment.model.UserMT4Account;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserMT4AccountDaoImpl extends BaseDao<UserMT4Account> implements IUserMT4AccountDao {

    public UserMT4AccountDaoImpl() {
        super( IUserMT4AccountDao.class.getName());
    }

	@Override
	public List<UserMT4Account> getUserMT4AccountByUid(int uid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		return super.findList("getByUid", params);
	}

	@Override
	public int queryCountByConditionByStatus(Map<String, Object> params) {
		return (Integer)findOne("pageQueryCountByStatus", params);
	}

	@Override
	public List<UserMT4Account> queryByCondition(Map<String, Object> params) {
		return findList("queryByCondition", params);
	}

    @Override
    public List<UserMT4Account> queryByCondition2(Map<String, Object> params) {
        return findList("queryByCondition2", params);
    }

	@Override
	public UserMT4Account getUserCommissionAccountByUid(Integer uid) {
		return (UserMT4Account) super.findOne("getUserCommissionAccountByUid", uid);
	}

	@Override
	public UserMT4Account getUserMT4AccountByMt4Account(int mt4Account) {
		return (UserMT4Account) super.findOne("getUserMT4AccountByMt4Account", mt4Account);
	}

	@Override
	public List<Integer> queryByGroupAndIB(Map<String, Object> queryConditionMap) {
		int groupType=(Integer) queryConditionMap.get("queryGroup");
		int queryIBid=(Integer) queryConditionMap.get("queryIBid");
		switch (groupType){
		case 4:         //A4 B4 
			return super.findList("findCommonByIb", queryIBid);
		case 3: 	    //A3 B3 
			return super.findList("findVIPByIb", queryIBid);
		case 2: 		//A2 B2 
			return super.findList("findVVIPByIb", queryIBid);
		case 5: 		//A5 
			return super.findList("findCashByIb", queryIBid);
		}
		return null;
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByUidStatus(int uid, int status) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("status", status);
		return super.findList("getByUidStatus", params);
	}

	@Override
	public UserMT4Account getUserMT4AccountByUidUserStatus(int uid,
			int userStatus) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", uid);
		params.put("userStatus", userStatus);
		return (UserMT4Account)findOne("pageQueryCountByUidUserStatus", params);
	}
	
	@Override
	public List<UserMT4Account> getUserMT4AccountByUidCount(Integer userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("uid", userId);
		return super.findList("getByUidCount", params);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByGroup(Integer groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		return super.findList("getUserMT4AccountByGroup", params);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByGroupUidList(
			int queryGroupId, ArrayList<Integer> userIdList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", queryGroupId);
		params.put("list", userIdList);
		return super.findList("getUserMT4AccountByGroupUidList", params);
	}

	@Override
	public UserMT4Account getByUidAndUserStaus(int uid,int userStatus){
		Map params = new HashMap();
		params.put("uid",uid);
		params.put("userStaus",userStatus);
		return (UserMT4Account)super.findOne("getByUidAndUserStaus", params);
	}

	@Override
	public int selectCountByStatus(int uid, int userStatus) {
		Map params = new HashMap();
		params.put("uid",uid);
		params.put("userStaus",userStatus);
		return (Integer)super.findOne("selectCountByStatus",params);
	}

    @Override
    public List<UserMT4Account> selectMt4AccountByStatus(Integer userId, int status, int dataSource) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", userId);
        params.put("userStaus", status);
        params.put("dataSource", dataSource);
        return findList("selectMt4AccountByStatus", params);
    }
	@Override
	public List<UserMT4Account> selectMt4AccountByStatus2(Integer userId, int status, int dataSource,int mt4Account2) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", userId);
		params.put("userStaus", status);
		params.put("dataSource", dataSource);
		params.put("mt4Account2","%"+mt4Account2+"%");
		return findList("selectMt4AccountByStatus", params);
	}

    @Override
    public Integer deleteByUid(Integer userId) {
        return (Integer) super.doUpdate("deleteByUid", userId);
    }

	@Override
	public int selectCountByIsIbId(int ibid) {
		return (Integer)findOne("selectCountByIsIbId", ibid);
	}

	@Override
	public List<UserMT4Account> selectAllAndNoDel() {
		return findList("selectAllAndNoDel","");
	}

	@Override
	public void updautomatedReview(int id,int auto) {
		Map map = new HashMap();
		map.put("id",id);
		map.put("auto",auto);
		update("updautomatedReview", map);
	}

	@Override
	public List<UserMT4Account> getUserMT4AccountByIbId(int ib_id) {
		return findList("getUserMT4AccountByIbId", ib_id);
	}

	@Override
	public int queryCountByConditionByUid(Map<String, Object> params) {
		return (Integer) super.findOne("queryCountByConditionByUid", params);
	}

	@Override
	public List<UserMT4Account> queryByConditionByUid(Map<String, Object> params) {
		return findList("queryByConditionByUid", params);
	}

	@Override
	public int pageCountUserMT4AccountByUidCount(Map<String, Object> params) {
		return (Integer) super.findOne("pageCountUserMT4AccountByUidCount", params);
	}

	@Override
	public List<UserMT4Account> pageQueryUserMT4AccountByUidCount(
			Map<String, Object> params) {
		return findList("pageQueryUserMT4AccountByUidCount", params);
	}

	@Override
	public List<UserMT4Account> getUserCommissionAccountsByUidAndDataSourceId(
			Integer id, int mt4DataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("uid", id);
		params.put("mt4DataSourceId", mt4DataSourceId);
		return findList("getUserCommissionAccountsByUidAndDataSourceId", params);
	}
	@Override
	public int queryCountCommissionByUid(Map<String, Object> params) {
		return (Integer)findOne("queryCountCommissionByUid", params);
	}

	@Override
	public List<UserMT4Account> pageQueryCommissionByUid(
			Map<String, Object> params) {
		return findList("pageQueryCommissionByUid", params);
	}

	@Override
	public int queryCountByConditiontByIbidAndAgentAccount(
			Map<String, Object> params) {
		return (Integer)findOne("queryCountByConditiontByIbidAndAgentAccount", params);
	}

	@Override
	public List<UserMT4Account> pageQueryByConditiontByIbidAndAgentAccount(
			Map<String, Object> params) {
		return findList("pageQueryByConditiontByIbidAndAgentAccount", params);
	}

    @Override
    public List<UserMT4Account> getUserMT4AccountByDataSourceId(int dataSourceId) {
        return findList("getUserMT4AccountByDataSourceId", dataSourceId);
    }

    @Override
    public int queryCountByConditionByStatusMoreTable(UserMT4Account userMT4Account) {
        return (Integer)findOne("pageQueryCountByStatusMoreTable", userMT4Account);
    }

    @Override
    public List<UserMT4Account> queryByConditionMoreTable(UserMT4Account userMT4Account) {
        return findList("queryByConditionMoreTable", userMT4Account);
    }

	@Override
	public List<Integer> findLoginsByCondition(UserMT4Account userMT4Account) {
		return super.findList("findLoginsByCondition", userMT4Account);
	}

	@Override
	public List<Integer> findLoginsByNewNameAccountCondition(UserMT4Account userMT4Account) {
		return super.findList("findLoginsByNewNameAccountCondition", userMT4Account);
	}
	@Override
	public List<Integer> findLoginsByConditionTrader(UserMT4Account userMT4Account) {
		return super.findList("findLoginsByConditionTrader", userMT4Account);
	}

	@Override
    public int getCountByEmailAndDataSourceType(int userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return (Integer) findOne("getCountByEmailAndDataSourceType", params);
    }

    @Override
	public UserMT4Account getByMt4Account(UserMT4Account userMT4Account) {
		return (UserMT4Account)findOne("getByMt4Account", userMT4Account);
	}

	@Override
	public UserMT4Account getByMt4Account2(int account, Integer dataSourceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("login",account);
		params.put("dataSourceId",dataSourceId);
		return (UserMT4Account)findOne("getByMt4Account2", params);
	}

	@Override
	public List<UserMT4Account> selectAllAndSuccDeposit(String mt4Accounts) {
		Map map = new HashMap();
		map.put("mt4Accounts",mt4Accounts);
		return findList("selectAllAndSuccDeposit",map);
	}

	@Override
	public UserMT4Account getByMt4AccountComp(int login) {
		return (UserMT4Account)findOne("getByMt4AccountComp", login);
	}

    @Override
    public List<UserMT4Account> getUserMT4AccountByUserIdAndIbId(Integer userId, int ib_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", userId);
        params.put("ibId", ib_id);
        return findList("getUserMT4AccountByIbId", params);
    }

    @Override
    public int getMT4AccountPassCountByUid(int uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        return (Integer) findOne("getMT4AccountPassCountByUid", params);
    }

    @Override
    public int getMT4AccountCountByUid(int uid) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", uid);
        return (Integer) findOne("getMT4AccountCountByUid", params);
    }

    @Override
    public List<UserMT4Account> getUserMT4AccountByUidOrderByAudit(Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return findList("getUserMT4AccountByUidOrderByAudit", params);
    }

    @Override
    public List<UserMT4Account> pageQueryByConditionByAgentAccount(Map<String, Object> params) {
        return findList("pageQueryByConditionByAgentAccount", params);
    }

    @Override
    public int queryCountByConditionByAgentAccount(Map<String, Object> params) {
        return (Integer)findOne("queryCountByConditionByAgentAccount", params);
    }

	@Override
	public List<UserMT4Account> queryLoginList(UserMT4Account userMT4Account) {
		return findList("queryLoginList",userMT4Account);
	}

    @Override
    public int queryCountByConditiontByUserId(Map<String, Object> params) {
        return (Integer)findOne("queryCountByConditiontByUserId", params);
    }

    @Override
    public List<UserMT4Account> pageQueryByConditiontByUserId(Map<String, Object> params) {
        return findList("pageQueryByConditiontByUserId", params);
    }

    @Override
    public int queryCountByConditionByMt4Account(Map<String, Object> params) {
        return (Integer) findOne("queryCountByConditionByMt4Account", params);
    }

    @Override
    public List<UserMT4Account> pageQueryByConditionByMt4Account(Map<String, Object> params) {
        return findList("pageQueryByConditionByMt4Account", params);
    }

	@Override
	public List<UserMT4Account> getUserMT4AccountByUidList(HashMap<String, Object> params) {
		return findList("getUserMT4AccountByUidList", params);
	}

    @Override
    public UserMT4Account getBeanByMt4Account(UserMT4Account userMT4Account) {
        return (UserMT4Account) findOne("getBeanByMt4Account", userMT4Account);
    }
}