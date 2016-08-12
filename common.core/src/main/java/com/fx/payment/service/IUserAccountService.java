package com.fx.payment.service;

import java.math.BigDecimal;
import java.util.List;

import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.util.Pagination;

import mybatis.framework.core.service.IValueObjectService;
import mybatis.framework.core.support.PageIterator;

import com.fx.payment.exception.PayException;
import com.fx.payment.model.ApplyWithDrawMoney;
import com.fx.payment.model.TradeDetail;
import com.fx.payment.model.UserAccount;
import com.fx.user.model.MT4Transfer;
import com.fx.user.model.MT4TransferLog;

/** 
* @ClassName: IUserAccountService 
* @Description: 用户账户类相关业务操作接口
* @author zhangpj
* @date 2013-12-1 上午10:21:19 
*  
*/
public interface IUserAccountService extends IValueObjectService<UserAccount> {
	
	/**
	 * createUserAccount	为某用户创建账户信息
	 * @param userAccount	uid,create_user,create_time,creat_ip为必填项
	 * @return int	1:成功		0:fail
	 * @exception 
	*/
	public int createUserAccount(UserAccount userAccount) throws PayException;
	
	/**
	 * getUserAccount	根据uid获取用户账户信息
	 * 
	 * @param uid	用户uid
	 * @return UserAccount	用户账户信息
	 * @exception 
	 * 
	*/
	public UserAccount getUserAccountByUid(int uid) throws PayException;
	
	/**
	 * 
	 * <p>Title: getUserAccountByMT4Account</p>
	 * <p>Description: </p>
	 * @exception:根据mt4账号查询UserAccount
	 * @author:alan.ma
	 * @time:2014-12-23 下午1:26:45
	 * UserAccount
	 * @param mt4Account
	 * @return
	 */
	public UserAccount getUserAccountByMT4Account(int mt4Account);
	
	/**
	 * openVirtualMT4Account	开通MT4虚拟账户
	 * @return int	返回操作结果
	 * @exception 
	*/
	public int openVirtualMT4Account(UserAccount userAccount) throws PayException;
	
	/**
	 * openMT4Account	开通MT4真实账户
	 * @return int	返回操作结果
	 * @exception 
	*/
	public int openMT4Account(UserAccount userAccount) throws PayException;
	
	/**
	 * addMoney	入金操作
	 * 
	 * @param tradeDetail	入金相关的交易信息
	 * @return int	1:success	0:fail
	 * @exception 
	*/
	public int addMoney(TradeDetail tradeDetail, String msg) throws PayException;
	
	/**
	 * addMoney	入金操作
	 * 
	 * @param tradeDetail	Demo入金相关的交易信息
	 * @return int	1:success	0:fail
	 * @exception 
	*/
	public int addDemoMoney(int uid,int mt4Account,BigDecimal money,DataSourceBean dataSourceBean) throws PayException;

	/**
	 * addMoney	入金操作
	 * 
	 * @param tradeDetail	入金相关的交易信息
	 * @return int	1:success	0:fail
	 * @exception 
	*/
	public int addMT4AccountMoney(TradeDetail tradeDetail, String msg,int mt4Account) throws PayException;
	
	/**
	 * preparedForWithdrawMoney	出金准备操作
	 * @param tradeDetail	出金相关的交易信息
	 * @return int	1:success	0:fail
	 * @exception 
	*/
	public int preparedForWithdrawMoney(TradeDetail tradeDetail, ApplyWithDrawMoney applyWithdrawMoeny) throws PayException ;
	
	/**
	 * withdrawMoney	出金操作
	 * @param tradeDetail	出金相关的交易信息
	 * @return int	1:success	0:fail
	 * @exception 
	*/
	public int withdrawMoney(TradeDetail tradeDetail) throws PayException;
	
	/**
	 * getByUidList	管理后台使用，根据用户UID批量获取用户账户信息
	 * @param uidList
	 * @return List<UserAccount>
	 * @exception 
	*/
	public List<UserAccount> getByUidList(List<Integer> uidList);
	
	public void testDeposit();

	public List<UserAccount> getUserAccountByGroup(int queryGroupID);
	
	/**
	 * 
	 * <p>Title: getUserAccountByGroupIbId</p>
	 * <p>Description: 根据组id和ib_id查询UserAccount</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 上午11:53:17
	 * List<UserAccount>
	 * @param queryGroupID  查询的组id
	 * @param ib_id  用户的推荐人id
	 * @return
	 */
	public List<UserAccount> getUserAccountByGroupIbId(int queryGroupID,int ib_id);
	
	/**
	 * 
	 * <p>Title: pageQueryByConditionMt4Type</p>
	 * <p>Description: 查询所有的demo用户根据国家</p>
	 * @exception:
	 * @author:alan
	 * @time:2015-1-9 下午4:16:04
	 * PageIterator<UserAccount>
	 * @param countryCode 国家
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageIterator<UserAccount> pageQueryByConditionMt4Type(int pageNo, int pageSize);

	public int addMT4TransferMoney(String string, MT4Transfer mt4Transfer)throws PayException;

	public int addMT4TransferMoney(String string, MT4Transfer mt4Transfer,DataSourceBean dataSourceBean)throws PayException;
	
	public int mulMT4TransferMoney(MT4Transfer mt4Transfer, String string)throws PayException;
	
	public int mulMT4TransferMoney(MT4Transfer mt4Transfer, String string,DataSourceBean dataSourceBean)throws PayException;

	public PageIterator<UserAccount> pageQueryByConditiontByStatus(int status, Pagination pagination);

	public void deleteByUid(int uid);

    /**
     * mt4AccountDeposit 入金操作（免收手续费）
     *
     * @param tradeDetail
     * @param msg
     * @param mt4Account
     * @return int 1: success 0: fail
     * @throws PayException
     */
    public int mt4AccountDeposit(TradeDetail tradeDetail, String msg, int mt4Account, boolean flag) throws PayException;
}