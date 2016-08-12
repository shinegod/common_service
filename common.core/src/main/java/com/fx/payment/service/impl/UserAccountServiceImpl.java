package com.fx.payment.service.impl;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.util.MT4AccountUtil;
import com.fx.MT4.util.MT4Configuration;
import com.fx.MT4.util.MT4DepositUtil;
import com.fx.MT4.vo.MT4User;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.fx.enums.IsDelEnum;
import com.fx.global.dao.IUidGeneratorDao;
import com.fx.global.dao.impl.UidGeneratorDaoImpl;
import com.fx.payment.dao.IUserAccountDao;
import com.fx.payment.enums.TradeStatusEnum;
import com.fx.payment.enums.UserAccountStatusEnum;
import com.fx.payment.exception.PayException;
import com.fx.payment.model.*;
import com.fx.payment.service.*;
import com.fx.user.dao.IUserRegisterDao;
import com.fx.user.model.InvestExperience;
import com.fx.user.model.MT4Transfer;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IInvestExperienceService;
import com.fx.user.service.IMT4TransferLogService;
import com.fx.user.service.IMT4TransferService;
import com.fx.user.service.IUserService;
import com.fx.user.util.UserUtils;
import com.fx.util.DateUtil;
import com.fx.util.Pagination;
import com.fx.util.RateUtil;

import mybatis.framework.core.service.BaseVOService;
import mybatis.framework.core.support.PageIterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserAccountServiceImpl extends BaseVOService<UserAccount> implements IUserAccountService {
	private static final Logger logger = LoggerFactory.getLogger(UserAccountServiceImpl.class);
    @Autowired
    private IUserAccountDao userAccountDao;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
	private IMT4TransferService mt4TransferService;
    
    @Autowired
    private IUserRegisterDao userRegisterDao;
    
    @Autowired
	private IMT4TransferLogService mt4TransferLogService;
    
    @Autowired
    private IInvestExperienceService investExperienceService;
    
    @Autowired
    private ITradeDetailService tradeDetailService;
    
    @Autowired
    private ITradeLogService tradeLogService;

	@Autowired
	private IApplyWithDrawMoneyService applyWithDrawMoneyService;

	@Autowired
	private IUserMT4AccountService userMT4AccountService;

	@Autowired
	private IDataSourceBeanService dataSourceBeanService;
    
    @Override
    public int createUserAccount(UserAccount userAccount) throws PayException{
    	if(null == userAccount){
    		throw new PayException("-1:创建个人帐户时参数为空");
    	}
    	if(null == userAccount.getUid() || userAccount.getUid() <= 0){
    		throw new PayException("-2:创建个人帐户时，uid为空");
    	}
    	if(StringUtils.isBlank(userAccount.getCreateUser())){
    		throw new PayException("-3:操作者为空");
    	}
    	if(StringUtils.isBlank(userAccount.getCreateIp())){
    		throw new PayException("-4:操作者IP为空");
    	}
    	UserAccount ua = new UserAccount();
    	ua.setUid(userAccount.getUid());
    	ua.setTradeCode(getTradeCode(userAccount.getUid()));	
    	ua.setAccountBalance(new BigDecimal(0.00D));
    	ua.setFreezeBalance(new BigDecimal(0.00D));
    	ua.setAllFundsInto(new BigDecimal(0.00D));
    	ua.setAllFundsOut(new BigDecimal(0.00D));
    	ua.setFundsIntoNumber(0);
    	ua.setFundsOutNumber(0);
		ua.setIb_id(userAccount.getIb_id());
    	ua.setMt4Account(userAccount.getMt4Account());
    	ua.setMt4AccountType(userAccount.getMt4AccountType());
    	ua.setMt4GroupId(userAccount.getMt4GroupId());
    	ua.setOperatePassword(null == userAccount.getOperatePassword()? "" : userAccount.getOperatePassword());
    	ua.setStatus(UserAccountStatusEnum.UN_ACTIVATE.getValue());
    	ua.setCreateUser(userAccount.getCreateUser());
    	ua.setCreateTime(DateUtil.getCurrentTime());
    	ua.setCreateIp(userAccount.getCreateIp());
    	ua.setUpdateUser(userAccount.getCreateUser());
    	ua.setUpdateTime(DateUtil.getCurrentTime());
    	ua.setUpdateIp(userAccount.getCreateIp());
    	ua.setIsDel(IsDelEnum.NO.getValue());
    	logger.debug("######userAccountDao.doInsert before");
    	int result = userAccountDao.doInsert("insert", ua);
    	logger.debug("######userAccountDao.doInsert success");
    	if(result <= 0){
    		throw new PayException("-5:创建用户账户出错");
    	}
    	return result;
    }

	@Override
	public UserAccount getUserAccountByUid(int uid) {
		return userAccountDao.getUserAccountByUid(uid);
	}
	
	@Override
	public List<UserAccount> getUserAccountByGroup(int groupId) {
		return userAccountDao.getByGroup(groupId);
	}
	
	@Override
	public int openVirtualMT4Account(UserAccount userAccount) throws PayException {
		/*
		 * 开通虚拟账户操作步骤:
		 * 	1、引入进入注册页面，成为网站用户
		 *  2、向MT4系统发开虚拟账号请求，并接受返回数据
		 *  3、建立一个个人账户，将MT4返回的虚拟账号信息保存起来
		 *  4、将新开通的MT4虚拟账户信息展示给网站用户
		 *  
		 *  进到这个方法表示已经完成了用户注册
		 */
		// 1、做数据校验
		if(null == userAccount){
			throw new PayException("-1:参数为无效数据");
		}
		if(null == userAccount.getUid() || userAccount.getUid() <= 0){
			throw new PayException("-2:没有开户人Uid");
		}
		User user = userService.getByUid(userAccount.getUid());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-3:开户人详细信息不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", userAccount.getUid());
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-3:开户人不存在");
		}
		if(userAccount.getMt4Account()==-1){
			throw new PayException("-4:生成MT4模拟账号失败");
		}
		if(StringUtils.isBlank(userAccount.getUpdateUser())){
			throw new PayException("-5:未填写修改人");
		}
		if(StringUtils.isBlank(userAccount.getUpdateIp())){
			throw new PayException("-6:未填写修改者IP");
		}
		// 2、向MT4发开通模拟账户的请求，完成开通模拟账户操作。并获取模拟账户的账号与密码
		// TODO zhangpj 与MT4对接，申请开通模拟用户
		// 3、将获取的模拟账户的账号与密码
		int virtualMT4Account = -1;
		UserAccount ua = getUserAccountByUid(userAccount.getUid());
		ua.setMt4Account(virtualMT4Account);
		ua.setMt4AccountType(ua.getMt4AccountType());
		ua.setUpdateUser(userAccount.getUpdateUser());
		ua.setUpdateIp(userAccount.getUpdateIp());
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
    		throw new PayException("-7:创建用户账户出错");
    	}
		return result;
	}

	@Override
	public int openMT4Account(UserAccount userAccount) throws PayException {
		/*
		 * 开通真实账户操作步骤:
		 * 	1、引入进入注册页面，成为网站用户。
		 *  2、完善个人资料
		 *  3、提示开通真实用户，不过需要用户上传各类信息凭证。以便完成用户审核
		 *  3、进入用户资料审核
		 *  4、审核通过后手动编辑MT4账户，然后邮件给网站用户。同时在网站上录入该用户的MT4账户信息
		 *  
		 *  
		 *  进到此方法时说明已经完成用户资料审核，手工录入在MT4中编辑的用户MT4账号信息的阶段
		 */
		// 1、做数据校验
		if(null == userAccount){
			throw new PayException("-1:参数为无效数据");
		}
		if(null == userAccount.getUid() || userAccount.getUid() <= 0){
			throw new PayException("-2:没有开户人Uid");
		}
		User user = userService.getByUid(userAccount.getUid());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-3:开户人详细信息不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", userAccount.getUid());
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-4:开户人不存在");
		}
		InvestExperience investExperience = investExperienceService.getByUid(userAccount.getUid());
		if(null == investExperience || investExperience.getIsDel() == 1){
			throw new PayException("-5:没有投资经验信息，用户信息不完整");
		}
		if(!UserUtils.checkInfoIsIntact(user, userRegister, investExperience)){
			throw new PayException("-6:用户信息不完整");
		}
		if(userAccount.getMt4Account()==-1){
			throw new PayException("-7:未录入MT4账号");
		}
		if(userAccount.getMt4AccountType() <= 0){
			throw new PayException("-8:未录入MT4账号类型");
		}
		if(StringUtils.isBlank(userAccount.getUpdateUser())){
			throw new PayException("-9:未填写修改人");
		}
		if(StringUtils.isBlank(userAccount.getUpdateIp())){
			throw new PayException("-10:未填写修改者IP");
		}
		UserAccount ua = getUserAccountByUid(userAccount.getUid());
		ua.setMt4AccountType(userAccount.getMt4AccountType());
		ua.setMt4Account(userAccount.getMt4Account());
		ua.setUpdateUser(userAccount.getUpdateUser());
		ua.setUpdateIp(userAccount.getUpdateIp());
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
			throw new PayException("-11:更新用户账户信息出错");
		}
		return result;
	}

	@Override
	public int addMoney(TradeDetail tradeDetail, String msg) throws PayException {
		/*
		 * 入金操作步骤:
		 * 	1、确定用户身份，判断用户资料是否完整。是否在审核状态。
		 *  2、查询下用户在MT4系统中的账户状态，确定是否为正常状态
		 *  3、用户选择相应的支付方式；比如网银或者其他支付平台。
		 *  4、跳转到网银或者支付平台页面，完成支付操作。
		 *  5、查询用户是否完成支付操作。一般根据银行的交易流水号。
		 *  6、如果完成了支付操作，则向MT4系统发出入金请求。
		 *  	这块是与MT4系统对接的事了，由于具体的方式方法还不清楚，所以无法深入
		 *  7、接收到MT4返回的成功请求后，再先MT4系统发起查询请求，确认入金操作成功。
		 *  8、返回用户操作成功提示
		 *  
		 *  进入这个方法，表示用户已经完成了银行转账，跳转回来后网站相应的处理
		 */
		// 1、数据校验
		if(null == tradeDetail){
			throw new PayException("-1:交易详情为空");
		}
		if(null == tradeDetail.getId() || tradeDetail.getId() <= 0){
			throw new PayException("-2:交易详情ID为空");
		}
		if( tradeDetail.getUid() <= 0){
			throw new PayException("-3:没有操作者Uid");
		}
		User user = userService.getByUid(tradeDetail.getUid().intValue());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-4:账户不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", tradeDetail.getUid());
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-6:账户不存在");
		}
//		InvestExperience investExperience = investExperienceService.getByUid(tradeDetail.getUid());
//		if(null == investExperience || investExperience.getIsDel() == 1){
//			throw new PayException("-7:没有投资经验信息，用户信息不完整");
//		}
//		if(!UserUtils.checkInfoIsIntact(user, userRegister, investExperience)){
//			throw new PayException("-8:用户信息不完整，须先完善用户信息");
//		}
		UserAccount ua = getUserAccountByUid(tradeDetail.getUid());
		BigDecimal balance = new BigDecimal(0D);
		if(null == ua.getAccountBalance()){
			balance = tradeDetail.getOperMoney();
		}else{
			balance = ua.getAccountBalance().add(tradeDetail.getOperMoney());
		}
		ua.setAccountBalance(balance);
		BigDecimal allFundsInto = ua.getAllFundsInto().add(tradeDetail.getOperMoney());
		ua.setAllFundsInto(allFundsInto);
		int fundIntoSize = (null == ua.getFundsIntoNumber() ? 0 : ua.getFundsIntoNumber().intValue());
		ua.setFundsIntoNumber(fundIntoSize + 1);
		ua.setUpdateUser(tradeDetail.getUpdateUser());
		ua.setUpdateIp(tradeDetail.getUpdateIp());
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
			throw new PayException("-9:更新用户账户表出错");
		}
		TradeDetail td = tradeDetailService.findById(tradeDetail.getId());
		
		td.setStatus(tradeDetail.getStatus());
		td.setUpdateUser(tradeDetail.getUpdateUser());
		td.setUpdateIp(tradeDetail.getUpdateIp());
		result = tradeDetailService.updateTradeStatusById(td);
		if(result <= 0){
			throw new PayException("-10:更新交易状态出错");
		}
		//TODO zhangpj 与MT4对接，发入金请求....
//		int mt4Uid = ua.getMt4Account();
//		BigDecimal bd = td.getOperMoney().setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//TODO updated by Dean
		//MT4DepositUtil.getInstance().deposit(mt4Uid, bd.doubleValue());
		
		// Record a comment on MT4 Server 

//		MT4DepositUtil.deposit(mt4Uid,bd.doubleValue(),msg,QueryMtT4GroupIdEnum.LIVE);

//		TradeDetail td2 = tradeDetailService.findById(tradeDetail.getId());
//		TradeLog tl2 = new TradeLog();
//		tl2.setTradeId(tradeDetail.getId());
//		tl2.setTradeStatus(td2.getStatus());
//		tl2.setReceiveMsg("the response message from mt4");
//		tl2.setOperUser(tradeDetail.getUpdateUser());
//		tl2.setOperTime(DateUtil.getCurrentTime());
//		tl2.setOperIp(tradeDetail.getUpdateIp());
//
//		td2.setStatus(TradeStatusEnum.ADD_MONEY_SUCCESS.getValue());
//		td2.setUpdateUser(tradeDetail.getUpdateUser());
//		td2.setUpdateIp(tradeDetail.getUpdateIp());
//		tradeDetailService.updateTradeStatusById(td2);
//		result = tradeLogService.doInsert(tl2);
//		if(result <= 0){
//			throw new PayException("-11:更新交易状态出现异常");
//		}
		return result;
	}
	
	@Override
	public int preparedForWithdrawMoney(TradeDetail tradeDetail, ApplyWithDrawMoney applyWithdrawMoeny) throws PayException {
		/*
		 * 准备内容:
		 * 	1、生成出金交易记录
		 *  2、保存出金申请单
		 *  
		 * 以后将会调整为的方式
		 *  1、生成出金交易记录
		 *  2、保存出金申请单
		 *  3、由人工完成出金审核操作
		 *  4、审核成功后，修改申请单状态。同时判断MT4系统账户是否可以进行出金操作。主要是账户状态与余额
		 *  5、自动从MT4中扣除相应的金额。
		 *  6、自动调用与银行的对接接口，完成转账业务
		 *  7、确认银行转账成功
		 *  8、修改出金交易记录的状态为出金操作成功
		 */
		TradeDetail detail = tradeDetailService.createTradeDetail4WithdrawMoney(tradeDetail);
		if(null == detail){
			throw new PayException("-1:保存交易详情单出错");
		}
		if(null == detail.getId() || detail.getId() <= 0){
			throw new PayException("-2:交易详情单ID为空");
		}
		addFreezeBlance4Withdraw(detail);	//这里不用返回值判断了，在方法内部已经判断了
		applyWithdrawMoeny.setTradeId(detail.getId());
		int result = applyWithDrawMoneyService.createApplyWithdrawMoney(applyWithdrawMoeny);
		if(result <= 0){
			throw new PayException("-3:创建出金申请单出错");
		}
		return result;
	}

	@Override
	public int withdrawMoney(TradeDetail tradeDetail) throws PayException {
		// TODO zhangpj
		/*
		 * 出金操作步骤:
		 * 	1、确定用户身份，判断用户资料是否完整。是否可进行出金操作
		 *  2、查询下用户在MT4系统中的账户状态，确定是否可进行出金操作。
		 *  3、用户填写出金申请
		 *  4、提示用户进入审核
		 *  5、进入审核后台，完成审核操作
		 *  6、审核成功，完成线下转账（之后会改为线上操作）；同时需要在网站上录入该用户的出金信息
		 *  7、查询到相关的出金信息后，给用户提示
		 *  
		 *  程序进入这里，表示线下转账完成，用户在网站上录入该用户的出金信息
		 */
		// 1、数据校验
		if(null == tradeDetail){
			throw new PayException("-1:交易详情为空");
		}
		if(null == tradeDetail.getId() || tradeDetail.getId() <= 0){
			throw new PayException("-2:交易详情ID为空");
		}
		if( tradeDetail.getUid() <= 0){
			throw new PayException("-3:没有操作者Uid");
		}
		User user = userService.getByUid(tradeDetail.getUid().intValue());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-4:账户不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", tradeDetail.getUid());
//		if(userRegister.getStatus() != UserRegisterStatusEnum.ACTIVATED.getValue()){
//			throw new PayException("-5:用户处于未激活状态");
//		}
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-6:账户不存在");
		}
		InvestExperience investExperience = investExperienceService.getByUid(tradeDetail.getUid());
		if(null == investExperience || investExperience.getIsDel() == 1){
			throw new PayException("-7:没有投资经验信息，用户信息不完整");
		}
		if(!UserUtils.checkInfoIsIntact(user, userRegister, investExperience)){
			throw new PayException("-8:用户信息不完整，不得须先完善用户信息");
		}
		UserAccount ua = getUserAccountByUid(tradeDetail.getUid());
		if(ua.getAccountBalance().compareTo(tradeDetail.getOperMoney()) < 0){
			throw new PayException("-9:余额不足");
		}
		BigDecimal balance = ua.getAccountBalance().subtract(tradeDetail.getOperMoney());
		ua.setAccountBalance(balance);
		BigDecimal allFundsOut = ua.getAllFundsOut().add(tradeDetail.getOperMoney());
		ua.setAllFundsOut(allFundsOut);
		int fundOutSize = (null == ua.getFundsOutNumber() ? 0 : ua.getFundsOutNumber().intValue());
		ua.setFundsOutNumber(fundOutSize + 1);
		ua.setUpdateUser(tradeDetail.getUpdateUser());
		ua.setUpdateIp(tradeDetail.getUpdateIp());
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
			throw new PayException("-10:更新用户账户表出错");
		}
		TradeDetail td = tradeDetailService.findById(tradeDetail.getId());
		td.setStatus(TradeStatusEnum.WITHDRAW_MONEY_SUCCESS.getValue());
		td.setUpdateUser(tradeDetail.getUpdateUser());
		td.setUpdateIp(tradeDetail.getUpdateIp());
		result = tradeDetailService.updateTradeStatusById(td);
		if(result <= 0){
			throw new PayException("-11:更新交易状态出现异常");
		}
		return result;
	}
	
	@Override
	public List<UserAccount> getByUidList(List<Integer> uidList){
		if(null == uidList || uidList.size() <= 0){
			return null;
		}
		return userAccountDao.getByUidList(uidList);
	}

	private static String ORDER_NO_PREFIX = "";
	
	private String getTradeCode(int uid){
    	if(uid <= 0){
    		return null;
    	}
    	String zeroStr = "0000000000";
    	String idStr = String.valueOf(uid);
    	String tradeCode = new StringBuilder("T-").append(zeroStr.substring(0, 10 - idStr.length())).append(idStr).toString();
    	return tradeCode;
    }
	
	private int addFreezeBlance4Withdraw(TradeDetail tradeDetail) throws PayException{
		UserAccount userAccount = getUserAccountByUid(tradeDetail.getUid());
		UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Account(tradeDetail.getMt4Account());
		DataSourceBean dataSourceBean = dataSourceBeanService.findById(userMT4Account.getDataSourceId());
		MT4User mt4user=MT4AccountUtil.getMT4ClientInfo(tradeDetail.getMt4Account(),QueryMtT4GroupIdEnum.LIVE,dataSourceBean);
		if(null == userAccount){
			throw new PayException("-1:未查询到用户账户信息");
		}
		/*if(tradeDetail.getOperMoney().compareTo(new BigDecimal(mt4user.getBalance())) > 0){
			throw new PayException("-2:出金申请单中金额大于账户余额");
		}*/
		BigDecimal freezeBalance = userAccount.getFreezeBalance();
		if(null == freezeBalance){
			freezeBalance = new BigDecimal(0.00D);
		}
		userAccount.setFreezeBalance(freezeBalance.add(tradeDetail.getOperMoney()));
		userAccount.setUpdateUser(tradeDetail.getCreateUser());
		userAccount.setUpdateTime(DateUtil.getCurrentTime());
		userAccount.setUpdateIp(tradeDetail.getCreateIp());
		int result = doUpdateById(userAccount);
		if(result <= 0){
			throw new PayException("-3:更新用户账户冻结金额操作出错");
		}
		return result;
	}
	
	@Override
	public void testDeposit(){
		IUidGeneratorDao UG=new UidGeneratorDaoImpl();
		System.out.print(UG.selectNextIdLive(MT4Configuration.companyId));
	}

	@Override
	public UserAccount getUserAccountByMT4Account(int mt4Account) {
		return userAccountDao.getUserAccountByMT4Account(mt4Account);
	}

	@Override
	public List<UserAccount> getUserAccountByGroupIbId(int queryGroupID,
			int ib_id) {
		return userAccountDao.getByGroupByIbId(queryGroupID, ib_id);
	}

	@Override
	public PageIterator<UserAccount> pageQueryByConditionMt4Type(
		 int pageNo, int pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		List<UserAccount> userAccountList = userAccountDao.pageQueryByConditionMt4Type(params);
		int offset = (pageNo -1) * pageSize;
		params.put("offset", offset);
		params.put("limit", pageSize);
		PageIterator<UserAccount> page = PageIterator.createInstance(pageNo, pageSize, userAccountList.size());
		page.setData(userAccountList);
		return page;
	}

	@Override
	public int addMT4AccountMoney(TradeDetail tradeDetail, String msg,
			int mt4Account) throws PayException {
		/*
		 * 入金操作步骤:
		 * 	1、确定用户身份，判断用户资料是否完整。是否在审核状态。
		 *  2、查询下用户在MT4系统中的账户状态，确定是否为正常状态
		 *  3、用户选择相应的支付方式；比如网银或者其他支付平台。
		 *  4、跳转到网银或者支付平台页面，完成支付操作。
		 *  5、查询用户是否完成支付操作。一般根据银行的交易流水号。
		 *  6、如果完成了支付操作，则向MT4系统发出入金请求。
		 *  	这块是与MT4系统对接的事了，由于具体的方式方法还不清楚，所以无法深入
		 *  7、接收到MT4返回的成功请求后，再先MT4系统发起查询请求，确认入金操作成功。
		 *  8、返回用户操作成功提示
		 *  
		 *  进入这个方法，表示用户已经完成了银行转账，跳转回来后网站相应的处理
		 */
		// 1、数据校验
		if(null == tradeDetail){
			throw new PayException("-1:交易详情为空");
		}
		if(null == tradeDetail.getId() || tradeDetail.getId() <= 0){
			throw new PayException("-2:交易详情ID为空");
		}
		if( tradeDetail.getUid() <= 0){
			throw new PayException("-3:没有操作者Uid");
		}
		User user = userService.getByUid(tradeDetail.getUid().intValue());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-4:账户不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", tradeDetail.getUid());
//		if(userRegister.getStatus() != UserRegisterStatusEnum.ACTIVATED.getValue()){
//			throw new PayException("-5:用户处于未激活状态");
//		}
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-6:账户不存在");
		}
		InvestExperience investExperience = investExperienceService.getByUid(tradeDetail.getUid());
//		if(null == investExperience || investExperience.getIsDel() == 1){
//			throw new PayException("-7:没有投资经验信息，用户信息不完整");
//		}
//		if(!UserUtils.checkInfoIsIntact(user, userRegister, investExperience)){
//			throw new PayException("-8:用户信息不完整，须先完善用户信息");
//		}
		UserAccount ua = getUserAccountByUid(tradeDetail.getUid());
		BigDecimal balance = new BigDecimal(0D);
		if(null == ua.getAccountBalance()){
			balance = tradeDetail.getOperMoney();
		}else{
			balance = ua.getAccountBalance().add(tradeDetail.getOperMoney());
		}
		ua.setAccountBalance(balance);
		BigDecimal allFundsInto = ua.getAllFundsInto().add(tradeDetail.getOperMoney());
		ua.setAllFundsInto(allFundsInto);
		int fundIntoSize = (null == ua.getFundsIntoNumber() ? 0 : ua.getFundsIntoNumber().intValue());
		ua.setFundsIntoNumber(fundIntoSize + 1);
		ua.setUpdateUser(tradeDetail.getUpdateUser());
		ua.setUpdateIp(tradeDetail.getUpdateIp());
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
			throw new PayException("-9:更新用户账户表出错");
		}
		TradeDetail td = tradeDetailService.findById(tradeDetail.getId());
		if(td.getStatus()==140){
			td.setStatus(TradeStatusEnum.ADD_MONEY_BANK_SUCCESS_WAIT_MT4.getValue());
			td.setUpdateUser(tradeDetail.getUpdateUser());
			td.setMt4Account(tradeDetail.getMt4Account());
			td.setRate(tradeDetail.getRate());
			td.setUpdateIp(tradeDetail.getUpdateIp());
			result = tradeDetailService.updateTradeStatusById(td);
			
			TradeLog tradeLog = tradeLogService.getByTradeId(tradeDetail.getId());
			if(null==tradeLog){
				tradeLog=new TradeLog();
				tradeLog.setTradeId(tradeDetail.getId());
				tradeLog.setTradeStatus(td.getStatus());
				tradeLog.setReceiveMsg("MT4 return status "+tradeDetail.getStatus());
				tradeLog.setOperUser(tradeDetail.getUpdateUser());
				tradeLog.setOperTime(DateUtil.getCurrentTime());
				tradeLog.setOperIp(tradeDetail.getUpdateIp());
				result = tradeLogService.doInsert(tradeLog);
			}else{
				tradeLog.setTradeId(tradeDetail.getId());
				tradeLog.setTradeStatus(td.getStatus());
				tradeLog.setReceiveMsg("the response message from mt4");
				tradeLog.setOperUser(tradeDetail.getUpdateUser());
				tradeLog.setOperTime(DateUtil.getCurrentTime());
				tradeLog.setOperIp(tradeDetail.getUpdateIp());
				result = tradeLogService.doUpdateById(tradeLog);
			}
			
			if(result <= 0){
				throw new PayException("-10:更新交易状态出错");
			}
		}
		//TODO zhangpj 与MT4对接，发入金请求....
		BigDecimal money = td.getActualMoney();
		BigDecimal bd = money.setScale(5, BigDecimal.ROUND_HALF_UP);
		UserMT4Account userMT4Account = userMT4AccountService.getUserMT4AccountByMt4Account(mt4Account);
		DataSourceBean dataSourceBean = dataSourceBeanService.findById(userMT4Account.getDataSourceId());

		int count = MT4DepositUtil.deposit(mt4Account,bd.doubleValue(),msg,QueryMtT4GroupIdEnum.LIVE,dataSourceBean);  //todo 真实入金时 要将数据源传过来

		TradeDetail td2 = tradeDetailService.findById(tradeDetail.getId());
        if(count!=10){
    		td2.setStatus(TradeStatusEnum.ADD_MONEY_FAIL.getValue());
        }else{
    		td2.setStatus(TradeStatusEnum.ADD_MONEY_SUCCESS.getValue());
        }
        td2.setMt4Account(mt4Account);
        td2.setRate(tradeDetail.getRate());
		td2.setUpdateUser(tradeDetail.getUpdateUser());
		td2.setUpdateIp(tradeDetail.getUpdateIp());
		tradeDetailService.updateTradeStatusById(td2);
		
		TradeLog tl2 = tradeLogService.getByTradeId(td2.getId());
		if(null==tl2){
			TradeDetail td3 = tradeDetailService.findById(tradeDetail.getId());
			tl2=new TradeLog();
			tl2.setTradeId(tradeDetail.getId());
			tl2.setReceiveMsg("the response message from mt4");
			tl2.setOperUser(tradeDetail.getUpdateUser());
			tl2.setOperTime(DateUtil.getCurrentTime());
			tl2.setOperIp(tradeDetail.getUpdateIp());
			tl2.setTradeStatus(td3.getStatus());
			result = tradeLogService.doInsert(tl2);
		}else{
			TradeDetail td3 = tradeDetailService.findById(tradeDetail.getId());
			tl2.setTradeId(tradeDetail.getId());
			tl2.setReceiveMsg("the response message from mt4");
			tl2.setOperUser(tradeDetail.getUpdateUser());
			tl2.setOperTime(DateUtil.getCurrentTime());
			tl2.setOperIp(tradeDetail.getUpdateIp());
			tl2.setTradeStatus(td3.getStatus());
			result = tradeLogService.doUpdateById(tl2);
		}
		if(result <= 0){
			throw new PayException("-11:更新交易状态出现异常");
		}
		return result;
	}

	@Override
	public int addDemoMoney(int uid, int mt4Account,BigDecimal money,DataSourceBean dataSourceBean) throws PayException {
		/*
		 * 入金操作步骤:
		 * 	1、确定用户身份，判断用户资料是否完整。是否在审核状态。
		 *  2、查询下用户在MT4系统中的账户状态，确定是否为正常状态
		 *  3、用户选择相应的支付方式；比如网银或者其他支付平台。
		 *  4、跳转到网银或者支付平台页面，完成支付操作。
		 *  5、查询用户是否完成支付操作。一般根据银行的交易流水号。
		 *  6、如果完成了支付操作，则向MT4系统发出入金请求。
		 *  	这块是与MT4系统对接的事了，由于具体的方式方法还不清楚，所以无法深入
		 *  7、接收到MT4返回的成功请求后，再先MT4系统发起查询请求，确认入金操作成功。
		 *  8、返回用户操作成功提示
		 *  
		 *  进入这个方法，表示用户已经完成了银行转账，跳转回来后网站相应的处理
		 */
		UserAccount ua = getUserAccountByUid(uid);
		if(null==ua){
			return -1;
		}
		BigDecimal balance = new BigDecimal(0D);
		if(null == ua.getAccountBalance()){
			balance = money;
		}else{
			balance = ua.getAccountBalance().add(money);
		}
		ua.setAccountBalance(balance);
		BigDecimal allFundsInto = ua.getAllFundsInto().add(money);
		ua.setAllFundsInto(allFundsInto);
		int fundIntoSize = (null == ua.getFundsIntoNumber() ? 0 : ua.getFundsIntoNumber().intValue());
		ua.setFundsIntoNumber(fundIntoSize + 1);
		ua.setUpdateUser("");
		ua.setUpdateIp("");
		ua.setUpdateTime(DateUtil.getCurrentTime());
		int result = doUpdateById(ua);
		if(result <= 0){
			throw new PayException("-9:更新用户账户表出错");
		}
		//TODO zhangpj 与MT4对接，发入金请求....
		BigDecimal bd = money.setScale(2, BigDecimal.ROUND_HALF_UP);

		MT4DepositUtil.deposit(mt4Account,bd.doubleValue(),"Demo入金",QueryMtT4GroupIdEnum.DEMO,dataSourceBean);

		if(result <= 0){
			throw new PayException("-11:更新交易状态出现异常");
		}
		return result;
	}

	@Override
	public int addMT4TransferMoney(
			String string, MT4Transfer mt4Transfer)throws PayException {
		//业务前的验证
		verification(mt4Transfer);
		BigDecimal money = mt4Transfer.getAmount();
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		String eps_amount = df.format(money);
		double amount = Double.parseDouble(eps_amount);
		int result = MT4DepositUtil.deposit(mt4Transfer.getTomt4account(),amount,string,QueryMtT4GroupIdEnum.LIVE,null);
		if(result != 10){
			throw new PayException("-11:mt4账号入金出现异常");
		}
		return result;
		
	}
	
	@Override
	public int addMT4TransferMoney(
			String string, MT4Transfer mt4Transfer,DataSourceBean dataSourceBean)throws PayException {
		//业务前的验证
		verification(mt4Transfer);
		BigDecimal money = mt4Transfer.getAudit_amount();
		if(mt4Transfer.getCurrency().contains("-")) {
			String fromCurrency = mt4Transfer.getCurrency().split("-")[0];
			String toCurrency = mt4Transfer.getCurrency().split("-")[1];
			BigDecimal currencyRate = RateUtil.getCurrencyRate(fromCurrency, toCurrency, dataSourceBean.getId());
			money = money.multiply(mt4Transfer.getAudit_rate());
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		String eps_amount = df.format(money);
		double amount = Double.parseDouble(eps_amount);
		int result = MT4DepositUtil.deposit(mt4Transfer.getTomt4account(),amount,string,QueryMtT4GroupIdEnum.LIVE,dataSourceBean);
		if(result != 10){
			throw new PayException("-11:mt4账号入金出现异常");
		}
		return result;
		
	}

	@Override
	public int mulMT4TransferMoney(MT4Transfer mt4Transfer, String string)throws PayException {
		verification(mt4Transfer);
		BigDecimal money = mt4Transfer.getAmount();
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		String eps_amount = df.format(money);
		double amount = Double.parseDouble(eps_amount);
		int result = MT4DepositUtil.deposit(mt4Transfer.getFrommt4account(), 0-amount, string,QueryMtT4GroupIdEnum.LIVE,null);
		if(result != 10){
			throw new PayException("-11:mt4账号出金出现异常");
		}
		return result;
	}
	@Override
	public int mulMT4TransferMoney(MT4Transfer mt4Transfer, String string,DataSourceBean dataSourceBean)throws PayException {
		verification(mt4Transfer);
		BigDecimal money = mt4Transfer.getAudit_amount();
		java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
		String eps_amount = df.format(money);
		double amount = Double.parseDouble(eps_amount);
		int result = MT4DepositUtil.deposit(mt4Transfer.getFrommt4account(), 0-amount, string,QueryMtT4GroupIdEnum.LIVE,dataSourceBean);
		if(result != 10){
			throw new PayException("-11:mt4账号出金出现异常");
		}
		return result;
	}
	
	@Override
	public PageIterator<UserAccount> pageQueryByConditiontByStatus(int status, Pagination pagination) {
		return null;
	}

	@Override
	public void deleteByUid(int uid) {
		userAccountDao.deleteByUid(uid);
	}

	public void verification(MT4Transfer mt4Transfer)throws PayException {
		if( mt4Transfer.getUid() <= 0){
			throw new PayException("-3:没有操作者Uid");
		}
		User user = userService.getByUid(mt4Transfer.getUid().intValue());
		if(null == user || user.getIsDel() == 1){
			throw new PayException("-4:账户不存在");
		}
		UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", mt4Transfer.getUid());
		if(null == userRegister || userRegister.getIsDel() == 1){
			throw new PayException("-6:账户不存在");
		}
	}

    @Override
    public int mt4AccountDeposit(TradeDetail tradeDetail, String msg, int mt4Account, boolean flag) throws PayException {
        if (null == tradeDetail) {
            throw new PayException("-1:交易详情为空");
        }
        if (null == tradeDetail.getId() || tradeDetail.getId() <= 0) {
            throw new PayException("-2:交易详情ID为空");
        }
        if (tradeDetail.getUid() <= 0) {
            throw new PayException("-3:没有操作者Uid");
        }
        User user = userService.getByUid(tradeDetail.getUid().intValue());
        if (null == user || user.getIsDel() == 1) {
            throw new PayException("-4:账户不存在");
        }
        UserRegister userRegister = userRegisterDao.findById("selectByPrimaryKey", tradeDetail.getUid());
        if (null == userRegister || userRegister.getIsDel() == 1) {
            throw new PayException("-6:账户不存在");
        }
        UserAccount userAccount = getUserAccountByUid(tradeDetail.getUid());
        BigDecimal balance = new BigDecimal(0D);
        // flag: true 第一笔入金金额  flag: false 手续费
        if (flag) {
            if (null == userAccount.getAccountBalance()) {
                balance = tradeDetail.getActualMoney();
            } else {
                balance = userAccount.getAccountBalance().add(tradeDetail.getActualMoney());
            }
        } else {
            if (null == userAccount.getAccountBalance()) {
                balance = tradeDetail.getActualFee();
            } else {
                balance = userAccount.getAccountBalance().add(tradeDetail.getActualFee());
            }
        }
        userAccount.setAccountBalance(balance);
        BigDecimal allFundsInto = userAccount.getAllFundsInto().add(tradeDetail.getOperMoney());
        userAccount.setAllFundsInto(allFundsInto);
        int fundIntoSize = (null == userAccount.getFundsIntoNumber() ? 0 : userAccount.getFundsIntoNumber().intValue());
        userAccount.setFundsIntoNumber(fundIntoSize + 1);
        userAccount.setUpdateUser(tradeDetail.getUpdateUser());
        userAccount.setUpdateIp(tradeDetail.getUpdateIp());
        userAccount.setUpdateTime(DateUtil.getCurrentTime());
        int result = doUpdateById(userAccount);
        if (result <= 0) {
            throw new PayException("-9:更新用户账户表出错");
        }
        TradeDetail td = tradeDetailService.findById(tradeDetail.getId());
        if (td.getStatus() == 140) {
            td.setStatus(TradeStatusEnum.ADD_MONEY_BANK_SUCCESS_WAIT_MT4.getValue());
            td.setUpdateUser(tradeDetail.getUpdateUser());
            td.setMt4Account(tradeDetail.getMt4Account());
            td.setRate(tradeDetail.getRate());
            td.setUpdateIp(tradeDetail.getUpdateIp());
            result = tradeDetailService.updateTradeStatusById(td);

            TradeLog tradeLog = tradeLogService.getByTradeId(tradeDetail.getId());
            if (null == tradeLog) {
                tradeLog = new TradeLog();
                tradeLog.setTradeId(tradeDetail.getId());
                tradeLog.setTradeStatus(td.getStatus());
                tradeLog.setReceiveMsg(msg);
                tradeLog.setOperUser(tradeDetail.getUpdateUser());
                tradeLog.setOperTime(DateUtil.getCurrentTime());
                tradeLog.setOperIp(tradeDetail.getUpdateIp());
                result = tradeLogService.doInsert(tradeLog);
            } else {
                tradeLog.setTradeId(tradeDetail.getId());
                tradeLog.setTradeStatus(td.getStatus());
                tradeLog.setReceiveMsg(msg);
                tradeLog.setOperUser(tradeDetail.getUpdateUser());
                tradeLog.setOperTime(DateUtil.getCurrentTime());
                tradeLog.setOperIp(tradeDetail.getUpdateIp());
                result = tradeLogService.doUpdateById(tradeLog);
            }

            if (result <= 0) {
                throw new PayException("-10:更新交易状态出错");
            }
        }
        //TODO zhangpj 与MT4对接，发入金请求....
        BigDecimal money = null;
        if (flag) {
            money = td.getRealMoney();
        } else {
            money = td.getActualFee();
        }
        BigDecimal bd = money.setScale(5, BigDecimal.ROUND_HALF_UP);

        DataSourceBean dataSourceBean = dataSourceBeanService.findById(6);

        int count = MT4DepositUtil.deposit(mt4Account, bd.doubleValue(), msg, QueryMtT4GroupIdEnum.LIVE, dataSourceBean);

        TradeDetail td2 = tradeDetailService.findById(tradeDetail.getId());
        if (count != 10) {
            td2.setStatus(TradeStatusEnum.ADD_MONEY_FAIL.getValue());
        } else {
            td2.setStatus(TradeStatusEnum.ADD_MONEY_SUCCESS.getValue());
        }
        td2.setMt4Account(mt4Account);
        td2.setRate(tradeDetail.getRate());
        td2.setUpdateUser(tradeDetail.getUpdateUser());
        td2.setUpdateIp(tradeDetail.getUpdateIp());
        tradeDetailService.updateTradeStatusById(td2);

        TradeLog tl2 = tradeLogService.getByTradeId(td2.getId());
        if (null == tl2) {
            TradeDetail td3 = tradeDetailService.findById(tradeDetail.getId());
            tl2 = new TradeLog();
            tl2.setTradeId(tradeDetail.getId());
            tl2.setReceiveMsg("MT4 return status " + tradeDetail.getStatus());
            tl2.setOperUser(tradeDetail.getUpdateUser());
            tl2.setOperTime(DateUtil.getCurrentTime());
            tl2.setOperIp(tradeDetail.getUpdateIp());
            tl2.setTradeStatus(td3.getStatus());
            result = tradeLogService.doInsert(tl2);
        } else {
            TradeDetail td3 = tradeDetailService.findById(tradeDetail.getId());
            tl2.setTradeId(tradeDetail.getId());
            tl2.setReceiveMsg("the response message from mt4");
            tl2.setOperUser(tradeDetail.getUpdateUser());
            tl2.setOperTime(DateUtil.getCurrentTime());
            tl2.setOperIp(tradeDetail.getUpdateIp());
            tl2.setTradeStatus(td3.getStatus());
            result = tradeLogService.doUpdateById(tl2);
        }
        if (result <= 0) {
            throw new PayException("-11:更新交易状态出现异常");
        }
        return result;
    }
}