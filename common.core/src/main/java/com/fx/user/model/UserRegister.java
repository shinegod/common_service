package com.fx.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRegister extends BaseValueObject {
    private Integer id;

    private String password = "";

    private String email = "";

    private String phoneNum = "";

    private int adminType;
    
    private Date lastLoginTime;
    
    private int loginNum;

    private int status;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private int isDel;
    
    private int is_ib;

    private int orgId=1;

    private String orgName;
    
    private int websiteUserType;
    
    private String enName;//英文全名
    private String cnName;//中文全名
    private String firstLanguage;//首选语言
    private String whereKown;//从哪里知道我们的
    private String lead_status;
    private String interest;
    private String exExpression;//外汇经验
    private int is_ibId;//是否是IB推荐的
    private int is_ibName;//
    private String ibName ;
    private int is_supervision;
    private int is_trading;
    private int levelNum;
    private int leadsFlag;
    private String volume;
    private String pipComm;
    private List<Integer> webSiteUserTypeList;
    private int tradingNum;

    private int salesId;
    private String salesName;

    private String custcateId;
    private String saleproId;
    private String custsourId;

    private int mt4DatasourceType;

    private int mt4DataSourceId;

    private int agent_mt4Account;

    private int sellId;

    private String title;

    private String updateEdocTime;

    private String comment;//备注字段

    private Enum userType; //用来存储用户类型的枚举

    // 用来显示上级用户名
    private String parentUsername;

    private String roleName;

    UserRegister userRegister;

    UserContacts userContacts;

    SalesInfo salesInfo;

    Admin admin;

    User user;

    Role role;

    private int commissionType;

    String userAccount;
    //用于显示客户归属
    String agentName;

    private Integer ibUserType;

    private String refuseReason;

    // 有没有下级标识
    private boolean subUserFlag;

    private String salt;

    // 用来标识内部用户上级
    private int superior_id;

    private String customer_Id;

    // 有效期
    private Date indate;

    // sales备注
    private String salesComment;

    // supprot备注
    private String supportComment;

    // 角色id
    private int roleId;

    // 客户类型
    private String custType;

    //TODO 后续添加的数据
    private UserRegister  parentObj; //父节点对象
    private BigDecimal tVolume=new BigDecimal(0.0);
    private BigDecimal tPipCommission =new BigDecimal(0.0);
    //查看佣金
    private int showCommission;

    public int getShowCommission() {
        return showCommission;
    }

    public void setShowCommission(int showCommission) {
        this.showCommission = showCommission;
    }

    public int getLeadsFlag() {
        return leadsFlag;
    }

    public void setLeadsFlag(int leadsFlag) {
        this.leadsFlag = leadsFlag;
    }

    public int getLevelNum() {
        return levelNum;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public BigDecimal gettVolume() {
		return tVolume;
	}

	public void settVolume(BigDecimal tVolume) {
		this.tVolume = tVolume;
	}

	public BigDecimal gettPipCommission() {
		return tPipCommission;
	}

	public void settPipCommission(BigDecimal tPipCommission) {
		this.tPipCommission = tPipCommission;
	}

	private List<UserRegister> underlingUsers ;

    public String getLead_status() {
        return lead_status;
    }

    public void setLead_status(String lead_status) {
        this.lead_status = lead_status;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPipComm() {
        return pipComm;
    }

    public void setPipComm(String pipComm) {
        this.pipComm = pipComm;
    }


    private List<UserRegister> childrenList = new ArrayList<UserRegister>();    // 子节点


    public UserRegister getParentObj() {
        return parentObj;
    }

    public void setParentObj(UserRegister parentObj) {
        this.parentObj = parentObj;
    }

    public List<UserRegister> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<UserRegister> childrenList) {
        this.childrenList = childrenList;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getUpdateEdocTime() {
        return updateEdocTime;
    }

    public void setUpdateEdocTime(String updateEdocTime) {
        this.updateEdocTime = updateEdocTime;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getSalesComment() {
        return salesComment;
    }

    public void setSalesComment(String salesComment) {
        this.salesComment = salesComment;
    }

    public String getSupportComment() {
        return supportComment;
    }

    public void setSupportComment(String supportComment) {
        this.supportComment = supportComment;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    public int getSalesId() {
        return salesId;
    }

    public void setSalesId(int salesId) {
        this.salesId = salesId;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
    }

    @JsonIgnore
    public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

    public String getOrgName() {
        return orgName;
    }

    public int getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(int commissionType) {
		this.commissionType = commissionType;
	}

	public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getAgent_mt4Account() {
		return agent_mt4Account;
	}

	public void setAgent_mt4Account(int agent_mt4Account) {
		this.agent_mt4Account = agent_mt4Account;
	}

	public Integer getIbUserType() {
        return ibUserType;
    }

    public void setIbUserType(Integer ibUserType) {
        this.ibUserType = ibUserType;
    }

    public String getParentUsername() {
        return parentUsername;
    }

    public void setParentUsername(String parentUsername) {
        this.parentUsername = parentUsername;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public int getAdminType() {
        return adminType;
    }

    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }

    public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
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

	public int getIs_ib() {
		return is_ib;
	}

	public void setIs_ib(int is_ib) {
		this.is_ib = is_ib;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getFirstLanguage() {
		return firstLanguage;
	}

	public void setFirstLanguage(String firstLanguage) {
		this.firstLanguage = firstLanguage;
	}

	public String getWhereKown() {
		return whereKown;
	}

	public void setWhereKown(String whereKown) {
		this.whereKown = whereKown;
	}

	public String getExExpression() {
		return exExpression;
	}

	public void setExExpression(String exExpression) {
		this.exExpression = exExpression;
	}

	public int getIs_ibId() {
		return is_ibId;
	}

	public void setIs_ibId(int is_ibId) {
		this.is_ibId = is_ibId;
	}

	public int getIs_ibName() {
		return is_ibName;
	}

	public void setIs_ibName(int is_ibName) {
		this.is_ibName = is_ibName;
	}

	public String getIbName() {
		return ibName;
	}

	public void setIbName(String ibName) {
		this.ibName = ibName;
	}

	public int getIs_supervision() {
		return is_supervision;
	}

	public void setIs_supervision(int is_supervision) {
		this.is_supervision = is_supervision;
	}

	public int getIs_trading() {
		return is_trading;
	}

	public void setIs_trading(int is_trading) {
		this.is_trading = is_trading;
	}

	public int getTradingNum() {
		return tradingNum;
	}

	public void setTradingNum(int tradingNum) {
		this.tradingNum = tradingNum;
	}

	public int getWebsiteUserType() {
		return websiteUserType;
	}

	public void setWebsiteUserType(int websiteUserType) {
		this.websiteUserType = websiteUserType;
	}


    public UserContacts getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(UserContacts userContacts) {
        this.userContacts = userContacts;
    }

    public SalesInfo getSalesInfo() {
        return salesInfo;
    }

    public void setSalesInfo(SalesInfo salesInfo) {
        this.salesInfo = salesInfo;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getCustcateId() {
        return custcateId;
    }

    public void setCustcateId(String custcateId) {
        this.custcateId = custcateId;
    }

    public String getSaleproId() {
        return saleproId;
    }

    public void setSaleproId(String saleproId) {
        this.saleproId = saleproId;
    }

    public String getCustsourId() {
        return custsourId;
    }

    public void setCustsourId(String custsourId) {
        this.custsourId = custsourId;
    }

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Enum getUserType() {
        return userType;
    }

    public void setUserType(Enum userType) {
        this.userType = userType;
    }

    public int getMt4DatasourceType() {
        return mt4DatasourceType;
    }

    public void setMt4DatasourceType(int mt4DatasourceType) {
        this.mt4DatasourceType = mt4DatasourceType;
    }

    public int getMt4DataSourceId() {
        return mt4DataSourceId;
    }

    public void setMt4DataSourceId(int mt4DataSourceId) {
        this.mt4DataSourceId = mt4DataSourceId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public boolean isSubUserFlag() {
        return subUserFlag;
    }

    public void setSubUserFlag(boolean subUserFlag) {
        this.subUserFlag = subUserFlag;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getComment() { return comment;}

    public void setComment(String comment) { this.comment = comment;}

    public List<UserRegister> getUnderlingUsers() {
        return underlingUsers;
    }

    public void setUnderlingUsers(List<UserRegister> underlingUsers) {
        this.underlingUsers = underlingUsers;
    }

    public List<Integer> getWebSiteUserTypeList() {
        return webSiteUserTypeList;
    }

    public void setWebSiteUserTypeList(List<Integer> webSiteUserTypeList) {
        this.webSiteUserTypeList = webSiteUserTypeList;
    }
}