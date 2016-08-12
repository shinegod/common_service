package com.fx.bonus.model;

import com.fx.admin.model.Role;
import com.fx.common.model.Dictionary;
import com.fx.customer.model.CustCategory;
import com.fx.global.model.Group;
import mybatis.framework.core.model.BaseValueObject;

import java.util.Date;

public class CommissionRules extends BaseValueObject {
    private Integer id;

    private String ruleName;

    private Integer tradingGroupId;

    private Integer accountGroup;

    private String mt4Group;

    private String settleMode;

    private Integer roleId;
    
    private Integer status;

    private String settleUnit;

    private String commissionType;
    //用来判断返佣设置类型 1手数 2附加 3推荐
    private String userdefine1;

    private String userdefine2;

    private String updateUser;

    private Date updateDate;

    //用来存储数据源id
    private int dataSourceId;

    private String dataSourceName;

    TradingGroup tradingGroup;

    Group group;

    Dictionary dictionary;

    Dictionary jsfs;

    Dictionary jsdw;

    Dictionary mt4g;
    
    Dictionary returnType;

    Role role;

    CustCategory custCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public Integer getTradingGroupId() {
        return tradingGroupId;
    }

    public void setTradingGroupId(Integer tradingGroupId) {
        this.tradingGroupId = tradingGroupId;
    }

    public Integer getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(Integer accountGroup) {
        this.accountGroup = accountGroup;
    }

    public String getMt4Group() {
        return mt4Group;
    }

    public void setMt4Group(String mt4Group) {
        this.mt4Group = mt4Group;
    }


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }


    public String getSettleMode() {
		return settleMode;
	}

	public void setSettleMode(String settleMode) {
		this.settleMode = settleMode;
	}

	public String getSettleUnit() {
		return settleUnit;
	}

	public void setSettleUnit(String settleUnit) {
		this.settleUnit = settleUnit;
	}

	public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType == null ? null : commissionType.trim();
    }

    public String getUserdefine1() {
        return userdefine1;
    }

    public void setUserdefine1(String userdefine1) {
        this.userdefine1 = userdefine1 == null ? null : userdefine1.trim();
    }

    public String getUserdefine2() {
        return userdefine2;
    }

    public void setUserdefine2(String userdefine2) {
        this.userdefine2 = userdefine2 == null ? null : userdefine2.trim();
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public TradingGroup getTradingGroup() {
        return tradingGroup;
    }

    public void setTradingGroup(TradingGroup tradingGroup) {
        this.tradingGroup = tradingGroup;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public Dictionary getJsfs() {
        return jsfs;
    }

    public void setJsfs(Dictionary jsfs) {
        this.jsfs = jsfs;
    }

    public Dictionary getJsdw() {
        return jsdw;
    }

    public void setJsdw(Dictionary jsdw) {
        this.jsdw = jsdw;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Dictionary getMt4g() {
        return mt4g;
    }

    public void setMt4g(Dictionary mt4g) {
        this.mt4g = mt4g;
    }

	public Dictionary getReturnType() {
		return returnType;
	}

	public void setReturnType(Dictionary returnType) {
		this.returnType = returnType;
	}

    public CustCategory getCustCategory() {
        return custCategory;
    }

    public void setCustCategory(CustCategory custCategory) {
        this.custCategory = custCategory;
    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}