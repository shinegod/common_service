package com.fx.bonus.model;



import java.math.BigDecimal;
import java.util.Date;

import mybatis.framework.core.model.BaseValueObject;

public class TradingGroup extends BaseValueObject {
    private Integer id;

    private String groupName;

    private String tradingCategory;
    //删除标识 0未删除 1已删除
    private int status;

    private String updateUser;

    private Date updateDate;

    //用来存储数据源id
    private int dataSourceId;

    //用来显示对应数据源名称
    private String dataSourceName;
    
    //mt4账号
    private Integer mt4Account;
    
    //交易手数
    private BigDecimal volume;
    
    //返佣（单位和上级的IB返金账号一样）
    private BigDecimal amount;
    
    private String email;
    
    //品种/佣金报表
    private BigDecimal totleLots;
    
    private BigDecimal totleTreatments;
    
    private BigDecimal totleCommissions;
    
    private BigDecimal swaps;
    
    private BigDecimal totalProfit;
    
    private Integer totleAccounts;
    
    
    public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public BigDecimal getTotleLots() {
		return totleLots;
	}

	public void setTotleLots(BigDecimal totleLots) {
		this.totleLots = totleLots;
	}

	public BigDecimal getTotleTreatments() {
		return totleTreatments;
	}

	public void setTotleTreatments(BigDecimal totleTreatments) {
		this.totleTreatments = totleTreatments;
	}

	public BigDecimal getTotleCommissions() {
		return totleCommissions;
	}

	public void setTotleCommissions(BigDecimal totleCommissions) {
		this.totleCommissions = totleCommissions;
	}

	public BigDecimal getSwaps() {
		return swaps;
	}

	public void setSwaps(BigDecimal swaps) {
		this.swaps = swaps;
	}

	public Integer getTotleAccounts() {
		return totleAccounts;
	}

	public void setTotleAccounts(Integer totleAccounts) {
		this.totleAccounts = totleAccounts;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(Integer mt4Account) {
		this.mt4Account = mt4Account;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getTradingCategory() {
        return tradingCategory;
    }

    public void setTradingCategory(String tradingCategory) {
        this.tradingCategory = tradingCategory == null ? null : tradingCategory.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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