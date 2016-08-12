package com.fx.payment.service;

import com.fx.MT4.vo.MT4TradeRecord;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.UserMT4Account;
import com.fx.task.model.TaskManage;
import com.fx.user.model.UserRegister;
import com.fx.util.Pagination;
import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @ClassName: IUserAccountService
* @Description: 用户账户类相关业务操作接口
* @author zhangpj
* @date 2013-12-1 上午10:21:19
*
*/
public interface IUserMT4AccountService extends IValueObjectService<UserMT4Account> {



	/**
	 * getUserMT4Account	根据uid获取用户账户信息
	 *
	 * @param uid	用户uid
	 * @return UserMT4Account	用户账户信息
	 * @exception
	 *
	*/
	public List<UserMT4Account> getUserMT4AccountByUid(int uid) throws PayException;
	
	public List<UserMT4Account> getUserMT4AccountByUid(int uid,Integer flag) throws PayException;

	public List<UserMT4Account> getUserMT4AccountByUidStatus(int uid,int status) throws PayException;


	/**
	 * createUserMT4Account	为某用户创建账户信息
	 * @param userMT4Account	uid,create_user,create_time,creat_ip为必填项
	 * @return int	1:成功		0:fail
	 * @exception
	*/
	public int createUserMT4Account(UserMT4Account userMT4Account) throws PayException;


	public PageIterator<UserMT4Account> pageQueryByConditiontByStatus(int userMT4AccountType,int status, int pageNo, int pageSize);

	public int getUserCommissionAccountByUid(Integer uid);

	public List<UserMT4Account> getUserMT4AccountByGroup(Integer groupId);

	public UserMT4Account getUserMT4AccountByMt4Account(int mt4Account);

	public List<MT4TradeRecord> queryOrderByTimeAndIB(Map<String, Object> queryConditionMap,
			List<MT4TradeRecord> orderList);

	public UserMT4Account getUserMT4AccountByUidUserStatus(int uid,int userStatus);
	public List<UserMT4Account> getUserMT4AccountByUidCount(Integer userId);

	public List<UserMT4Account> getUserMT4AccountByGroupUidList(int queryGroupId, ArrayList<Integer> userIdList);

	public PageIterator<UserMT4Account> pageQueryByConditiontByStatus(int userMT4AccountType, int status, Pagination pagination);

	public UserMT4Account getByUidAndUserStaus(int uid,int userStatus);

	public int selectCountByStatus(int uid,int userStatus);

    public List<UserMT4Account> selectMt4AccountByStatus(Integer userId, int status, int dataSource);

	public List<UserMT4Account> selectMt4AccountByStatus2(Integer userId, int status, int dataSource,int mt4Account2);
    public Integer deleteByUid(Integer userId);

	public int selectCountByIsIbId(int ibid);

	public List<UserMT4Account> selectAllAndNoDel();

	public void updautomatedReview(int id,int auto);

	public List<UserMT4Account> getUserMT4AccountByIbidListAndUid(
			Integer userId, List<Integer> ibidList, int mt4DatasourceType);

	public UserMT4Account getUserMT4AccountByMt4Account(Integer login,
			int dataSourceId);
	public UserMT4Account getUserMT4AccountByMt4Account2(Integer login,
														int dataSourceId,String mt4Account2);
	public List<UserMT4Account> getUserMT4AccountByIbId(int ib_id);

	public List<UserMT4Account> getUserMT4AccountByUidAndStatus(Integer userId,
			int value);


	public PageIterator<UserMT4Account> pageQueryByConditiontByUid(
			List<Integer> userList,int userid, Pagination pagination);


	public UserMT4Account getUserCommissionUserMt4AccountByUid(Integer uid);

    public List<UserMT4Account> getUserMT4AccountByDataSourceId(int dataSourceId);

	/**
	 * 分页查询所有账号
	 * @param userId
	 * @param dataSourceId
	 * @param pagination
	 * @return
	 */
	public PageIterator<UserMT4Account> pageQueryUserMT4AccountByUidCount(
			Integer userId, int dataSourceId, Pagination pagination);

	public List<UserMT4Account> getUserCommissionAccountsByUidAndDataSourceId(
			Integer id, int mt4DataSourceId);
			
	public List<UserMT4Account> getUserMT4AccountByUserUserStatus(Integer id,
			int value);

	/**
	 * 分页查询出ib的所有返佣账号
	 * @param userId
	 * @return
	 */
	public PageIterator<UserMT4Account> pageQueryCommissionByUid(Integer userId,Pagination pagination);
	
	/**
	 * 分页查询出返佣账户下所有的的交易账户
	 * @param userId
	 * @param parseInt
	 * @param pagination
	 * @return
	 */
	public PageIterator<UserMT4Account> pageQueryByConditiontByIbidAndAgentAccount(
			Integer userId, int parseInt, Pagination pagination);

	public UserMT4Account getByMt4Account(int login);

	public UserMT4Account getByMt4Account(int account, Integer dataSourceId);

	
	/**
	 * 按照数据源，上级关系查询出所有已有的mt4账号
	 * @param params
	 * @return
	 */
	public List<Integer> findLoginsByCondition(HashMap<String, Object> params);

	public List<Integer> findLoginsByNewNameAccountCondition(HashMap<String, Object> params);

    public int getCountByEmailAndDataSourceType(int userId);

	public List<UserMT4Account> getuserMt4AccountByUserStatus(
			HashMap<String, Object> params);
	
	/**
	 * 根据返佣账号
	 * @param mt4Account
	 * @param dataSourceId
	 * @return
	 */
	public List<UserMT4Account> getUserMT4AccountByAgentAccount(int mt4Account, Integer dataSourceId);
	//查询全部符合自动入金的账号   （至少有过一次交易成功记录）
	public List<UserMT4Account> selectAllAndSuccDeposit(String mt4Account);

	public UserMT4Account getMT4AccountByUidAccount(Integer userId,
			int mt4account, int mt4DatasourceType);

	UserMT4Account getByMt4AccountComp(int login);

	public UserMT4Account getUserMT4AccountByMt4Accounts(Integer login,
			Integer id);

	public List<UserMT4Account> getUserMT4AccountByBingStatus(
			UserMT4Account userMT4Account);


    public List<UserMT4Account> getUserMT4AccountByUserIdAndIbId(Integer userId, int ib_id);

	public int queryCountByConditionBingStatus(UserMT4Account userMT4Account);

	public List<Integer> findLoginsByConditionTrader(HashMap<String, Object> paramsUserMt4Account);

	List<UserMT4Account> getuserMt4AccountByUserStatusTrader(HashMap<String, Object> paramter);

    public int getMT4AccountPassCountByUid(int uid);

    public int getMT4AccountCountByUid(int uid);

    public List<UserMT4Account> getUserMT4AccountByUidOrderByAudit(Integer userId);

	public List<UserMT4Account> getUserMt4AccountByNOAgent();

    public PageIterator<UserMT4Account> pageQueryByConditionByAgentAccount(int agentMt4Account, Pagination pagination);

    public List<UserMT4Account> getUserMT4AccountByUIdAndUserStatus(Integer uid, int userStatus);

	public List<UserMT4Account> getAllCommissionMT4();

    public List<UserMT4Account> getUserMT4AccountByIbIdLoop(Integer userId);

    public List<UserMT4Account> getUserMT4AccountByMt4AccountLoop(Map<String, Object> params);

    public PageIterator<UserMT4Account> pageQueryByConditiontByUserId(Integer userId, Pagination pagination);

    public PageIterator<UserMT4Account> pageQueryByConditionByMT4Account(int mt4account, Pagination pagination);


	List<UserMT4Account> getuserMt4AccountByUserStatusTraderSymbol(HashMap<String, Object> paramter);

	List<UserMT4Account> getUserMT4AccountByUidList(List<UserRegister> uidList);

	public List<UserMT4Account> findUidListByCondition(TaskManage taskManage);

	public List<UserMT4Account> getUserMT4AccountByUIdAndUserStatusAll(int uid, int value);

	public List<UserMT4Account> getUserMT4AccountByLoginList(
			Map<String,Object> params);

	List<UserMT4Account> getUserMT4AccountListByUid(Integer id);

    public UserMT4Account getBeanByMt4Account(int agentMt4Account, int dataSourceId);
}