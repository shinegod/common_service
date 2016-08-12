package com.fx.user.service;

import com.fx.user.model.User;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.HashMap;
import java.util.List;

public interface IUserService extends IValueObjectService<User> {
	
	/**
	 * getByUid	根据Uid获取用户信息
	 * @param uid
	 * @return User
	 * @exception 
	*/
	public User getByUid(int uid);
	
	/**
	 * 
	 * <p>Title: getByIsIB</p>
	 * <p>Description: </p>
	 * @exception:获得所有的iB客户 
	 * @author:alan.ma
	 * @time:2014-12-22 上午11:17:34
	 * List<User>
	 * @param is_ib User表中标识iB
	 * @return
	 */
	public List<User> getByIsIB(int is_ib);
	
    /**
     * 	
     * <p>Title: getByIsIB</p>
     * <p>Description: 根据ib_id查出所有的与ib有关的用户</p>
     * @exception:
     * @author:alan.ma
     * @time:2015-1-5 上午9:02:59
     * List<User>
     * @param ib_id
     * @return
     */
	public List<User> getByIBId(int ib_id);
	
	/**
	 * 	
	 * <p>Title: getByIBIdIsIb</p>
	 * <p>Description: 查询用户id为ib_id的用户下的所有ib用户或者所有的非ib用户</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 上午11:39:55
	 * List<User>
	 * @param ib_id 该用户的id
	 * @param is_ib 是否为ib
	 * @return
	 */
	public List<User> getByIBIdIsIb(int ib_id,int is_ib);
	
	/**
	 * 
	 * <p>Title: pageQueryByConditionByIsIb</p>
	 * <p>Description:首先分出IB和不是IB的组，然后再就行分页 </p>
	 * @exception:
	 * @author:alan.ma
	 * @time:2014-12-24 上午9:00:44
	 * PageIterator<User>
	 * @param is_ib
	 * @param countryCode
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageIterator<User> pageQueryByConditionByIsIb(int is_ib,int countryCode, int status, int pageNo, int pageSize);
	
	public PageIterator<User> pageQueryByConditionByIsIbIbID(int ib_Id,int is_ib,int countryCode, int status, int pageNo, int pageSize);
	
	public PageIterator<User> pageQueryByConditionByIbID(int ib_Id,int countryCode, int status, int pageNo, int pageSize);
	
	/**
	 * pageQueryByCondition	列表页查询用
	 * @param countryCode
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return PageIterator<User>
	 * @exception 
	*/
	public PageIterator<User> pageQueryByCondition(int countryCode, int status, int pageNo, int pageSize);

	public PageIterator<User> pageQueryByCondition(int countryCode, int status, Pagination pagination);

	public List<User> pageQueryByCondition(int countryCode, int status, Pagination pagination, int is_ib,int support);

	public List<User> getParentUsersByRoleId(int roleId);

	public List<User> getUserByRoleId(HashMap<String, Object> roleIdMap);

	//检查email是否重复
	public int queryEmailRepeat(String email);

	public void modifyIbUserTypeById(int id, int ibUserType);

    public void deleteById(User user);

	public int pageCountByCondition(int i, int j, Pagination pagination, int k,int support);

	void updateIbidByUserIdList(List<String> idArray, Integer agent);
}