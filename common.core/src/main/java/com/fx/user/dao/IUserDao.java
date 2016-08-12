package com.fx.user.dao;

import com.fx.user.model.User;

import mybatis.framework.core.dao.IValueObjectDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IUserDao extends IValueObjectDao<User> {

	/**
	 * getByUid	根据Uid获取用户详细信息
	 * @param uid
	 * @return User
	 * @exception 
	*/
	User getByUid(int uid);
	
	public List<User> getIBUser(int is_ib);
	
	/**
	 * 
	 * <p>Title: getIBIdUser</p>
	 * <p>Description: 获取ib_id用户下的用户信息</p>
	 * @exception:
	 * @author:alan.ma
	 * @time:2015-1-5 上午9:06:51
	 * List<User>
	 * @param ib_id ib的id
	 * @return
	 */
	public List<User> getIBIdUser(int ib_id);
	
	/**
	 * 
	 * <p>Title: getIBIdIBUser</p>
	 * <p>Description: 查询用户id为ib_id的用户下的所有ib用户或者所有的非ib用户</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 上午11:43:58
	 * List<User>
	 * @param ib_id  该用户的id
	 * @param is_ib  是否为ib
	 * @return
	 */
	public List<User> getIBIdIBUser(int ib_id,int is_ib);
	/**
	 * queryCountByCondition	列表页查询总数量
	 * @param params
	 * @return int
	 * @exception 
	*/
	public int queryCountByCondition(Map<String, Object> params);
	
	/**
	 * 
	 * <p>Title: queryCountByConditionByIsIB</p>
	 * <p>Description: 获取IB或者不是IB的个数</p>
	 * @exception:
	 * @author:alan.ma
	 * @time:2014-12-24 上午9:27:59
	 * int
	 * @param params
	 * @return
	 */
	public int queryCountByConditionByIsIB(Map<String, Object> params);
	
	public int queryCountByConditionByIsIBIbId(Map<String, Object> params);
	
	public int queryCountByConditionByIbId(Map<String, Object> params);

	/**
	 * queryCountByCondition	列表页查询
	 * @param params
	 * @return int
	 * @exception 
	*/
	public List<User> queryByCondition(Map<String, Object> params);
	
	/**
	 * 
	 * <p>Title: pageQueryByConditionByIsIb</p>
	 * <p>Description: 将iB和不是IB的分组之后，列表页查询</p>
	 * @exception:
	 * @author:alan.ma
	 * @time:2014-12-24 上午9:26:34
	 * List<User>
	 * @param params
	 * @return
	 */
	public List<User> pageQueryByConditionByIsIb(Map<String, Object> params);
	
	public List<User> pageQueryByConditionByIsIbIbId(Map<String, Object> params);
	
	public List<User> pageQueryByConditionByIbId(Map<String, Object> params);

	public List<User> getParentUsersByRoleId(int roleId);

	public List<User> getIBIdUser(HashMap<String, Object> roleIdMap);

	//检查email是否重复
	public int queryEmailRepeat(String email);

	public void modifyIbUserTypeById(int id, int ibUserType);

    public void deleteById(User user);

	int queryCountByCondition1(User user);

	List<User> queryByCondition1(User user);

	void updateIbidByUserIdList(User user);
}