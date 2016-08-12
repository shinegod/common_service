package com.fx.payment.model;

import com.fx.global.model.Group;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.mt4TradeRecord.service.IMt4UsersService;
import com.fx.user.model.InvestExperience;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.util.SpringUtils;
import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;

public class UserMT4Account extends BaseValueObject {
	private Integer id;

	private Integer uid;

	private int mt4Account;

	private int userMT4AccountType;

	private String mt4GroupId;

	private int status;

	private String createUser;

	private String createTime;

	private String createIp;

	private String updateUser;

	private String updateTime;

	private String updateIp;

	private int isDel;

	private int userStaus;

	private String refuseReason;

	private int ibId;

	private int mt4DatasourceType;

	private  int automatedReview;

    // 是否是同名账户标识  0不是 1是
    private int namesake;

	private Mt4Users mt4Users;
	
	User user;

	UserRegister userRegister;

	UserAccount userAccount;

	Group group;

	InvestExperience investExperience;

    private Integer isCommissionIb;

	private int agentAccount;
	
	private BigDecimal volume;

	//用来存储数据源id
	private int dataSourceId;

	private String dataSourceName;

	//用来显示客户姓名
	private  String accountName;

	private  String accountEmail;
	//用来存储返金账号的币种
	private String currency;
	
	//用于显示返佣
    private BigDecimal pipCommission;
    //用来存储上级mt4账号
    private int agentMt4Account;
    
    private BigDecimal lots;
    
    private int accountType=0;

    private String comment;
    
    private String reason;

    // 用来保存审核通过顺序
    private String auditSort;
    

    //回显已返未返佣金总数

	private BigDecimal totalNoReturnCommission;

	private BigDecimal totalReturnCommission;

	private BigDecimal totalCommission;

	private String openaccounttype;

	private String roleName;

	private String agentName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public BigDecimal getTotalNoReturnCommission() {
		return totalNoReturnCommission;
	}

	public void setTotalNoReturnCommission(BigDecimal totalNoReturnCommission) {
		this.totalNoReturnCommission = totalNoReturnCommission;
	}

	public BigDecimal getTotalReturnCommission() {
		return totalReturnCommission;
	}

	public void setTotalReturnCommission(BigDecimal totalReturnCommission) {
		this.totalReturnCommission = totalReturnCommission;
	}

	public BigDecimal getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(BigDecimal totalCommission) {
		this.totalCommission = totalCommission;
	}

	public String getOpenaccounttype() {
		return openaccounttype;
	}

	public void setOpenaccounttype(String openaccounttype) {
		this.openaccounttype = openaccounttype;
	}

	//TODO账户金额
    private BigDecimal accountBalance;

    public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

    // 显示账号归属人
    private String name;

    // 显示账号归属人的email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuditSort() {
        return auditSort;
    }

    public void setAuditSort(String auditSort) {
        this.auditSort = auditSort;
    }

    public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getAccountType() {
		return accountType;
	}

	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	public BigDecimal getLots() {
		return lots;
	}

	public void setLots(BigDecimal lots) {
		this.lots = lots;
	}

	public int getAgentMt4Account() {
		return agentMt4Account;
	}

	public void setAgentMt4Account(int agentMt4Account) {
		this.agentMt4Account = agentMt4Account;
	}

	public BigDecimal getPipCommission() {
		return pipCommission;
	}

	public void setPipCommission(BigDecimal pipCommission) {
		this.pipCommission = pipCommission;
	}
    public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getIsCommissionIb() {
        return isCommissionIb;
    }

    public Mt4Users getMt4Users() {
		return mt4Users;
	}

	public void setMt4Users(Mt4Users mt4Users) {
		this.mt4Users = mt4Users;
	}

	public void setIsCommissionIb(Integer isCommissionIb) {
		this.isCommissionIb = isCommissionIb;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public User getUser() {
		return user;
	}

	public int getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(int agentAccount) {
		this.agentAccount = agentAccount;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public InvestExperience getInvestExperience() {
		return investExperience;
	}

	public void setInvestExperience(InvestExperience investExperience) {
		this.investExperience = investExperience;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public void setMt4Account(int mt4Account) {
		this.mt4Account = mt4Account;
	}

	public int getMt4Account() {
		return this.mt4Account;
	}

	public String getMt4GroupId() {
		if((int)this.mt4Account>0){
			IMt4UsersService mt4UsersService = SpringUtils.getBean(IMt4UsersService.class);
			Mt4Users mt4Users = mt4UsersService.getMt4UsersByMt4Account(this.mt4Account, (int)this.dataSourceId == 0 ? 6 : this.dataSourceId);
			if (null ==mt4Users) {
				return null;
			}
			return mt4Users.getGroup();
		}else{
			return null;
		}
	}

	public void setMt4GroupId(String mt4GroupId) {
		this.mt4GroupId = mt4GroupId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getCreateIp() {
		return createIp;
	}

	public void setCreateIp(String createIp) {
		this.createIp = createIp == null ? null : createIp.trim();
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser == null ? null : updateUser.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String getUpdateIp() {
		return updateIp;
	}

	public void setUpdateIp(String updateIp) {
		this.updateIp = updateIp == null ? null : updateIp.trim();
	}

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}

	public int getUserMT4AccountType() {
		return userMT4AccountType;
	}

	public void setUserMT4AccountType(int userMT4AccountType) {
		this.userMT4AccountType = userMT4AccountType;
	}

	public int getUserStaus() {
		return userStaus;
	}

	public void setUserStaus(int userStaus) {
		this.userStaus = userStaus;
	}

	public String getRefuseReason() {
		return refuseReason;
	}

	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

	public int getIbId() {
		return ibId;
	}

	public void setIbId(int ibId) {
		this.ibId = ibId;
	}

	public int getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(int dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public int getMt4DatasourceType() {
		return mt4DatasourceType;
	}

	public void setMt4DatasourceType(int mt4DatasourceType) {
		this.mt4DatasourceType = mt4DatasourceType;
	}

	public String getDataSourceName() {
		return dataSourceName;
	}

	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	public int getAutomatedReview() {
		return automatedReview;
	}

	public void setAutomatedReview(int automatedReview) {
		this.automatedReview = automatedReview;
	}

    public int getNamesake() {
        return namesake;
    }

    public void setNamesake(int namesake) {
        this.namesake = namesake;
    }

    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

}