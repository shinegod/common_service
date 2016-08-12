package com.fx.mt4TradeRecord.model;

import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class Mt4Users extends BaseValueObject {


    private String accountName;

    private String belongUser;

    private Integer login; //mt4账号
 
    private String group;  //组名

    private Integer enable; //

    private Integer enableChangePass; //

    private Integer enableReadonly; //

    private String passwordPhone; //

    private String name;  //英文名

    private String country;  //国家

    private String city; //城市

    private String state;  //省

    private String zipcode; //邮编

    private String address; //地址

    private String phone;  //电话

    private String email; //邮件

    private String comment; //备注

    private String id;

    private String status; //状态

    private Date regdate; //注册日期

    private Date lastdate; //最后登录日期

    private Integer leverage; //杠杆

    private Integer agentAccount; //

    private Integer timestamp;

    private Double balance; //余额

    private Double prevmonthbalance;

    private Double prevbalance;

    private Double credit;

    private Double interestrate;

    private Double taxes;

    private Integer sendReports;

    private Double mqid = 0d;

    private Integer userColor;

    private Double equity;

    private Double profit;

    private Double margin;

    private Double marginLevel;

    private Double marginFree;

    private String currency;

    private Date modifyTime;

    private byte[] apiData;

    private String dataSourceName;

    private int dataSourceId;

    // 用来区分mt4账号是返佣还是交易
    private int mt4AccountType;
    
    //浮动盈亏
    private Double floatingProfitLoss;
    
    private String strRegdate;

    private int uid;

    // 显示MT4组
    private String mt4Group;

    //客户月报表
    private String firstDepositDate;
    private Double firstDepositAmount;
    private Double deposit;
    private Double withDrawAmount;
    private String istraded;
    private Double adjustment;

    public Double getAdjustment() {
        return adjustment;
    }
    public void setAdjustment(Double adjustment) {
        this.adjustment = adjustment;
    }

    public String getFirstDepositDate() {
        return firstDepositDate;
    }

    public void setFirstDepositDate(String firstDepositDate) {
        this.firstDepositDate = firstDepositDate;
    }

    public Double getFirstDepositAmount() {
        return firstDepositAmount;
    }

    public void setFirstDepositAmount(Double firstDepositAmount) {
        this.firstDepositAmount = firstDepositAmount;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getWithDrawAmount() {
        return withDrawAmount;
    }

    public void setWithDrawAmount(Double withDrawAmount) {
        this.withDrawAmount = withDrawAmount;
    }


    public String getMt4Group() {
        return mt4Group;
    }

    public void setMt4Group(String mt4Group) {
        this.mt4Group = mt4Group;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    // MT4账号类型
    private int mt4ActType;

    public int getMt4ActType() {
        return mt4ActType;
    }

    public void setMt4ActType(int mt4ActType) {
        this.mt4ActType = mt4ActType;
    }

    public String getStrRegdate() {
		return strRegdate;
	}

	public void setStrRegdate(String strRegdate) {
		this.strRegdate = strRegdate;
	}

	public Double getFloatingProfitLoss() {
		return floatingProfitLoss;
	}

	public void setFloatingProfitLoss(Double floatingProfitLoss) {
		this.floatingProfitLoss = floatingProfitLoss;
	}

	public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getEnableChangePass() {
        return enableChangePass;
    }

    public void setEnableChangePass(Integer enableChangePass) {
        this.enableChangePass = enableChangePass;
    }

    public Integer getEnableReadonly() {
        return enableReadonly;
    }

    public void setEnableReadonly(Integer enableReadonly) {
        this.enableReadonly = enableReadonly;
    }

    public String getPasswordPhone() {
        return passwordPhone;
    }

    public void setPasswordPhone(String passwordPhone) {
        this.passwordPhone = passwordPhone == null ? null : passwordPhone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? null : zipcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getLastdate() {
        return lastdate;
    }

    public void setLastdate(Date lastdate) {
        this.lastdate = lastdate;
    }

    public Integer getLeverage() {
        return leverage;
    }

    public void setLeverage(Integer leverage) {
        this.leverage = leverage;
    }

    public Integer getAgentAccount() {
        return agentAccount;
    }

    public void setAgentAccount(Integer agentAccount) {
        this.agentAccount = agentAccount;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getPrevmonthbalance() {
        return prevmonthbalance;
    }

    public void setPrevmonthbalance(Double prevmonthbalance) {
        this.prevmonthbalance = prevmonthbalance;
    }

    public Double getPrevbalance() {
        return prevbalance;
    }

    public void setPrevbalance(Double prevbalance) {
        this.prevbalance = prevbalance;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(Double interestrate) {
        this.interestrate = interestrate;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public Integer getSendReports() {
        return sendReports;
    }

    public void setSendReports(Integer sendReports) {
        this.sendReports = sendReports;
    }

    public Double getMqid() {
        return mqid;
    }

    public void setMqid(Double mqid) {
        this.mqid = mqid;
    }

    public Integer getUserColor() {
        return userColor;
    }

    public void setUserColor(Integer userColor) {
        this.userColor = userColor;
    }

    public Double getEquity() {
        return equity;
    }

    public void setEquity(Double equity) {
        this.equity = equity;
    }

    public Double getMargin() {
        return margin;
    }

    public void setMargin(Double margin) {
        this.margin = margin;
    }

    public Double getMarginLevel() {
        return marginLevel;
    }

    public void setMarginLevel(Double marginLevel) {
        this.marginLevel = marginLevel;
    }

    public Double getMarginFree() {
        return marginFree;
    }

    public void setMarginFree(Double marginFree) {
        this.marginFree = marginFree;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public byte[] getApiData() {
        return apiData;
    }

    public void setApiData(byte[] apiData) {
        this.apiData = apiData;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public int getMt4AccountType() {
        return mt4AccountType;
    }

    public void setMt4AccountType(int mt4AccountType) {
        this.mt4AccountType = mt4AccountType;
    }
    public String getIstraded() {
        return istraded;
    }

    public void setIstraded(String istraded) {
        this.istraded = istraded;
    }

}