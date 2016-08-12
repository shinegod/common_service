package com.fx.payment.dao;

import com.fx.payment.model.UserMT4Account;
import mybatis.framework.core.dao.IValueObjectDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserMT4AccountDao extends IValueObjectDao<UserMT4Account> {

	/**
	 * getUserAccountByUid	根据用户UID获取用户账户信息
	 * @param uid
	 * @return UserAccount
	 * @exception
	*/
	public List<UserMT4Account> getUserMT4AccountByUid(int uid);

	public List<UserMT4Account> getUserMT4AccountByUidStatus(int uid,int status);

	public int queryCountByConditionByStatus(Map<String, Object> params);

	public List<UserMT4Account> queryByCondition(Map<String, Object> params);

	public UserMT4Account getUserCommissionAccountByUid(Integer uid);

	public UserMT4Account getUserMT4AccountByMt4Account(int mt4Account);

	public List<Integer> queryByGroupAndIB(Map<String, Object> queryConditionMap);
	public UserMT4Account getUserMT4AccountByUidUserStatus(int uid,
			int userStatus);

	public List<UserMT4Account> getUserMT4AccountByUidCount(Integer userId);

	public List<UserMT4Account> getUserMT4AccountByGroup(Integer groupId);

	public List<UserMT4Account> getUserMT4AccountByGroupUidList(
			int queryGroupId, ArrayList<Integer> userIdList);


    public List<UserMT4Account> queryByCondition2(Map<String, Object> params);

	public UserMT4Account getByUidAndUserStaus(int uid,int userStatus);

	public int selectCountByStatus(int uid,int userStatus);

    public List<UserMT4Account> selectMt4AccountByStatus(Integer userId, int status, int dataSource);
	public List<UserMT4Account> selectMt4AccountByStatus2(Integer userId, int status, int dataSource,int mt4Account2);

    public Integer deleteByUid(Integer userId);

	public int selectCountByIsIbId(int ibid);

	public List<UserMT4Account> selectAllAndNoDel();

	public void updautomatedReview(int id,int auto);

	public List<UserMT4Account> getUserMT4AccountByIbId(int ib_id);

	public int queryCountByConditionByUid(Map<String, Object> params);

	public List<UserMT4Account> queryByConditionByUid(Map<String, Object> params);

	public int pageCountUserMT4AccountByUidCount(Map<String, Object> params);

	public List<UserMT4Account> pageQueryUserMT4AccountByUidCount(
			Map<String, Object> params);

	public List<UserMT4Account> getUserCommissionAccountsByUidAndDataSourceId(
			Integer id, int mt4DataSourceId);
	public int queryCountCommissionByUid(Map<String, Object> params);

	public List<UserMT4Account> pageQueryCommissionByUid(
			Map<String, Object> params);

	public int queryCountByConditiontByIbidAndAgentAccount(
			Map<String, Object> params);

	public List<UserMT4Account> pageQueryByConditiontByIbidAndAgentAccount(
			Map<String, Object> params);

    public List<UserMT4Account> getUserMT4AccountByDataSourceId(int dataSourceId);

    public int queryCountByConditionByStatusMoreTable(UserMT4Account userMT4Account);

    public List<UserMT4Account> queryByConditionMoreTable(UserMT4Account userMT4Account);

	public UserMT4Account getByMt4Account(UserMT4Account login);

	public List findLoginsByCondition(UserMT4Account userMT4Account);
	public List findLoginsByNewNameAccountCondition(UserMT4Account userMT4Account);

    public int getCountByEmailAndDataSourceType(int userId);

	UserMT4Account getByMt4Account2(int account, Integer dataSourceId);

	public List<UserMT4Account> selectAllAndSuccDeposit(String mt4Accounts);

	UserMT4Account getByMt4AccountComp(int login);

    public List<UserMT4Account> getUserMT4AccountByUserIdAndIbId(Integer userId, int ib_id);

	public List<Integer> findLoginsByConditionTrader(UserMT4Account userMT4Account);

    public int getMT4AccountPassCountByUid(int uid);

    public int getMT4AccountCountByUid(int uid);

    public List<UserMT4Account> getUserMT4AccountByUidOrderByAudit(Integer userId);

    public List<UserMT4Account> pageQueryByConditionByAgentAccount(Map<String, Object> params);

    public int queryCountByConditionByAgentAccount(Map<String, Object> params);

	public List<UserMT4Account> queryLoginList(UserMT4Account userMT4Account);

    public int queryCountByConditiontByUserId(Map<String, Object> params);

    public List<UserMT4Account> pageQueryByConditiontByUserId(Map<String, Object> params);

    public int queryCountByConditionByMt4Account(Map<String, Object> params);

    public List<UserMT4Account> pageQueryByConditionByMt4Account(Map<String, Object> params);

	List<UserMT4Account> getUserMT4AccountByUidList(HashMap<String, Object> params);

    public UserMT4Account getBeanByMt4Account(UserMT4Account userMT4Account);
}