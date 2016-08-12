package com.fx.crm.comm.model;

import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

import com.fx.bonus.model.TradingGroup;
import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.payment.model.UserMT4Account;
import com.fx.user.model.UserRegister;

public class CommissionBase extends BaseValueObject {
    private String guid;

    private String userId;

    private String ibId;

    private String ibIdType;

    private BigDecimal handCommission;

    private BigDecimal pipCommission;

    private BigDecimal mt4Commission;

    private BigDecimal mt4CommissionAgent;

    private Date closeTime;

    private String ticket;

    private String comment;

    private BigDecimal volume;

    private String source = "((0))";

    private String status;

    private Integer mt4account;

    private String symbol;

    private BigDecimal swaps;

    private BigDecimal profit;

    private Date settledate;

    private Date createdate;

    private Date updatedate;
    
    private Integer dataSourceId;
    
    private Integer mt4DataSourceType;
    
    private BigDecimal unitPrice;//单价
    
    private BigDecimal rate; //汇率
    
    private BigDecimal parRate; //向上级返金账号的汇率
    
    private String settMode; //结算方式
    
    private int tradeCateId; //品种组
    
    private BigDecimal amount;
    
    private int agentAccount;
    
    private BigDecimal porgation;
    
    private String specialId;
    
    private String agentName;

    private String mt4AccountGroup;//账号组

    private Integer rules;//计算的规则
    
    /**
     * 存取对象，用于展示  
     * @return
     */
    private String tradingName;
    private UserRegister userRegister;
    
    private UserMT4Account userMT4Account;
    
    private Mt4Users mt4Users;
    
    private TradingGroup tradingGroup;
    
    public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getSpecialId() {
		return specialId;
	}

	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public TradingGroup getTradingGroup() {
		return tradingGroup;
	}

	public void setTradingGroup(TradingGroup tradingGroup) {
		this.tradingGroup = tradingGroup;
	}

	public UserRegister getUserRegister() {
		return userRegister;
	}

	public void setUserRegister(UserRegister userRegister) {
		this.userRegister = userRegister;
	}

	public UserMT4Account getUserMT4Account() {
		return userMT4Account;
	}

	public void setUserMT4Account(UserMT4Account userMT4Account) {
		this.userMT4Account = userMT4Account;
	}

	public Mt4Users getMt4Users() {
		return mt4Users;
	}

	public void setMt4Users(Mt4Users mt4Users) {
		this.mt4Users = mt4Users;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getParRate() {
		return parRate;
	}

	public void setParRate(BigDecimal parRate) {
		this.parRate = parRate;
	}

	public String getSettMode() {
		return settMode;
	}

	public void setSettMode(String settMode) {
		this.settMode = settMode;
	}

	public int getTradeCateId() {
		return tradeCateId;
	}

	public void setTradeCateId(int tradeCateId) {
		this.tradeCateId = tradeCateId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Integer dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public Integer getMt4DataSourceType() {
		return mt4DataSourceType;
	}

	public void setMt4DataSourceType(Integer mt4DataSourceType) {
		this.mt4DataSourceType = mt4DataSourceType;
	}

	public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIbId() {
        return ibId;
    }

    public void setIbId(String ibId) {
        this.ibId = ibId == null ? null : ibId.trim();
    }

    public String getIbIdType() {
        return ibIdType;
    }

    public void setIbIdType(String ibIdType) {
        this.ibIdType = ibIdType == null ? null : ibIdType.trim();
    }

    public BigDecimal getHandCommission() {
        return handCommission;
    }

    public void setHandCommission(BigDecimal handCommission) {
        this.handCommission = handCommission;
    }

    public BigDecimal getPipCommission() {
        return pipCommission;
    }

    public void setPipCommission(BigDecimal pipCommission) {
        this.pipCommission = pipCommission;
    }

    public BigDecimal getMt4Commission() {
        return mt4Commission;
    }

    public void setMt4Commission(BigDecimal mt4Commission) {
        this.mt4Commission = mt4Commission;
    }

    public BigDecimal getMt4CommissionAgent() {
        return mt4CommissionAgent;
    }

    public void setMt4CommissionAgent(BigDecimal mt4CommissionAgent) {
        this.mt4CommissionAgent = mt4CommissionAgent;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public BigDecimal getSwaps() {
        return swaps;
    }

    public void setSwaps(BigDecimal swaps) {
        this.swaps = swaps;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Date getSettledate() {
        return settledate;
    }

    public void setSettledate(Date settledate) {
        this.settledate = settledate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

	public int getAgentAccount() {
		return agentAccount;
	}

	public void setAgentAccount(int agentAccount) {
		this.agentAccount = agentAccount;
	}

	public BigDecimal getPorgation() {
		return porgation;
	}

	public void setPorgation(BigDecimal porgation) {
		this.porgation = porgation;
	}

    public String getMt4AccountGroup() {
        return mt4AccountGroup;
    }

    public void setMt4AccountGroup(String mt4AccountGroup) {
        this.mt4AccountGroup = mt4AccountGroup;
    }

    public Integer getRules() {
        return rules;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }
}