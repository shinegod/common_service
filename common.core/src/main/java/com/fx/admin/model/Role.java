package com.fx.admin.model;

import com.fx.crm.sys.permission.model.Permission;
import mybatis.framework.core.model.BaseValueObject;

import java.util.List;

public class Role extends BaseValueObject {
    private Integer id;

    private String name;

    private int status;

    private List<RolePower> powerItemList;

    private String createUser;

    private String createTime;

    private String createIp;

    private String updateUser;

    private String updateTime;

    private String updateIp;

    private List<Permission> permission;

    private int isDel;

    private String commissions;

    private String roletype;

    private int level;

    private List<String> dataRules;

    private Integer isInnerSales=0;

    private Integer hierarchy;

    private Integer orgId=1;

    private String dataScope;

    private int superiorId;

    public int getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(int superiorId) {
        this.superiorId = superiorId;
    }

    public String getDataScope() {
        return dataScope;
    }

    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getIsInnerSales() {
        return isInnerSales;
    }

    public void setIsInnerSales(Integer isInnerSales) {
        this.isInnerSales = isInnerSales;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public List<String> getDataRules() {
        return dataRules;
    }

    public void setDataRules(List<String> dataRules) {
        this.dataRules = dataRules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<RolePower> getPowerItemList() {
		return powerItemList;
	}

	public void setPowerItemList(List<RolePower> powerItemList) {
		this.powerItemList = powerItemList;
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

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

	public String getCommissions() {
		return commissions;
	}

	public void setCommissions(String commissions) {
		this.commissions = commissions;
	}

	public String getRoletype() {
		return roletype;
	}

	public void setRoletype(String roletype) {
		this.roletype = roletype;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}


}