package com.fx.mt4TradeRecord.model;

import com.fx.payment.model.UserMT4Account;
import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

public class Mt4Trades extends BaseValueObject {

    private String belongUser;

    private String accountName;

    private Integer ticket;

    private Integer login;

    private String symbol;

    private Integer digits;

    private Integer cmd;

    private Integer volume;

    private Date openTime;

    private Double openPrice;

    private Double sl;

    private Double tp;

    private Date closeTime;

    private Date expiration;

    private Integer reason = 0;

    private Double convRate1;

    private Double convRate2;

    private Double commission;

    private Double commissionAgent;

    private Double swaps;

    private Double closePrice;

    private Double profit;

    private Double taxes;

    private String comment;

    private Integer internalId;

    private Double marginRate;

    private Integer timestamp;

    private Integer gwVolume = 0;

    private Integer gwOpenPrice = 0;

    private Integer gwClosePrice = 0;

    private Date modifyTime;

    private String strOpentime;

    private String strClosetime;

    //用来显示客户归属
    private String comm_attribution;

    //用来显示客户姓名
    private String comm_name;

    //用来显示类型名称
    private String type_name;

    //用来显示状态名称
    private String status_name;
    
    //手数
    private Double volumeView;
    
    private Mt4Users mt4Users;
    
    private UserMT4Account userMT4Account;
    

    //品种/佣金报表
    private BigDecimal tCommission;
    
    private BigDecimal tSwaps;
    
    private BigDecimal tLots;
    
    private BigDecimal tProfit;
    
    private BigDecimal tTreatments;
    
    private String symbolsGroup;
    
    private String agentName;
    
    private String customerName;
    
    
    public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal gettCommission() {
		return tCommission;
	}

	public void settCommission(BigDecimal tCommission) {
		this.tCommission = tCommission;
	}

	public BigDecimal gettSwaps() {
		return tSwaps;
	}

	public void settSwaps(BigDecimal tSwaps) {
		this.tSwaps = tSwaps;
	}

	public BigDecimal gettLots() {
		return tLots;
	}

	public void settLots(BigDecimal tLots) {
		this.tLots = tLots;
	}

	public BigDecimal gettProfit() {
		return tProfit;
	}

	public void settProfit(BigDecimal tProfit) {
		this.tProfit = tProfit;
	}

	public BigDecimal gettTreatments() {
		return tTreatments;
	}

	public void settTreatments(BigDecimal tTreatments) {
		this.tTreatments = tTreatments;
	}

	public String getSymbolsGroup() {
		return symbolsGroup;
	}

	public void setSymbolsGroup(String symbolsGroup) {
		this.symbolsGroup = symbolsGroup;
	}


    public Double getVolumeView() {
		return volumeView;
	}

	public void setVolumeView(Double volumeView) {
		this.volumeView = volumeView;
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

	public String getBelongUser() {
        return belongUser;
    }

    public void setBelongUser(String belongUser) {
        this.belongUser = belongUser;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getCmd() {
        return cmd;
    }

    public void setCmd(Integer cmd) {
        this.cmd = cmd;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getSl() {
        return sl;
    }

    public void setSl(Double sl) {
        this.sl = sl;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Integer getReason() {
        return reason;
    }

    public void setReason(Integer reason) {
        this.reason = reason;
    }

    public Double getConvRate1() {
        return convRate1;
    }

    public void setConvRate1(Double convRate1) {
        this.convRate1 = convRate1;
    }

    public Double getConvRate2() {
        return convRate2;
    }

    public void setConvRate2(Double convRate2) {
        this.convRate2 = convRate2;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getCommissionAgent() {
        return commissionAgent;
    }

    public void setCommissionAgent(Double commissionAgent) {
        this.commissionAgent = commissionAgent;
    }

    public Double getSwaps() {
        return swaps;
    }

    public void setSwaps(Double swaps) {
        this.swaps = swaps;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getInternalId() {
        return internalId;
    }

    public void setInternalId(Integer internalId) {
        this.internalId = internalId;
    }

    public Double getMarginRate() {
        return marginRate;
    }

    public void setMarginRate(Double marginRate) {
        this.marginRate = marginRate;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getGwVolume() {
        return gwVolume;
    }

    public void setGwVolume(Integer gwVolume) {
        this.gwVolume = gwVolume;
    }

    public Integer getGwOpenPrice() {
        return gwOpenPrice;
    }

    public void setGwOpenPrice(Integer gwOpenPrice) {
        this.gwOpenPrice = gwOpenPrice;
    }

    public Integer getGwClosePrice() {
        return gwClosePrice;
    }

    public void setGwClosePrice(Integer gwClosePrice) {
        this.gwClosePrice = gwClosePrice;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getComm_attribution() {
        return comm_attribution;
    }

    public void setComm_attribution(String comm_attribution) {
        this.comm_attribution = comm_attribution;
    }

    public String getComm_name() {
        return comm_name;
    }

    public void setComm_name(String comm_name) {
        this.comm_name = comm_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getStrOpentime() {
        return strOpentime;
    }

    public void setStrOpentime(String strOpentime) {
        this.strOpentime = strOpentime;
    }

    public String getStrClosetime() {
        return strClosetime;
    }

    public void setStrClosetime(String strClosetime) {
        this.strClosetime = strClosetime;
    }
}