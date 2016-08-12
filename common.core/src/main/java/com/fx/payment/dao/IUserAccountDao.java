package com.fx.payment.dao;

import java.util.List;
import java.util.Map;

import com.fx.payment.model.UserAccount;
import com.fx.user.model.UserRegister;

import mybatis.framework.core.dao.IValueObjectDao;

public interface IUserAccountDao extends IValueObjectDao<UserAccount> {

	/**
	 * getUserAccountByUid	根据用户UID获取用户账户信息
	 * @param uid
	 * @return UserAccount
	 * @exception 
	*/
	public UserAccount getUserAccountByUid(int uid);

	/**
	 * getByUidList	根据用户UID批量获取用户账户信息
	 * @param uidList
	 * @return List<UserAccount>
	 * @exception 
	*/
	public List<UserAccount> getByUidList(List<Integer> uidList);
	
	/**
	 * 
	 * <p>Title: getUserAccountByMT4Account</p>
	 * <p>Description: 根据mt4账号查询数据库，获得UserAccount</p>
	 * @exception:
	 * @author:alan.ma
	 * @time:2014-12-23 下午1:29:33
	 * UserAccount 用户账号对象
	 * @param mt4Account mt4账号
	 * @return
	 */
	public UserAccount getUserAccountByMT4Account(int mt4Account);

	/**
	 * getByGroup	根据GroupID批量获取用户账户信息
	 * @param groupId
	 * @return List<UserAccount>
	 * @exception 
	*/
	public List<UserAccount> getByGroup(int groupId);
	
	/**
	 * 
	 * <p>Title: getByGroupByIbId</p>
	 * <p>Description: 根据组id和ib_id查询UserAccount</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 上午11:59:25
	 * List<UserAccount>
	 * @param groupId   查询的组id
	 * @param ib_id   用户的推荐人id
	 * @return
	 */
	public List<UserAccount> getByGroupByIbId(int groupId,int ib_id);
	
	/**
	 * 
	 * <p>Title: pageQueryByConditionMt4Type</p>
	 * <p>Description:查询所有的demo用户</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 下午4:14:56
	 * List<UserAccount>
	 * @param params
	 * @return
	 */
	public List<UserAccount> pageQueryByConditionMt4Type(Map<String, Object> params);

	public void deleteByUid(int uid);
}