package com.fx.payment.model;

import mybatis.framework.core.model.BaseValueObject;

public class TradeLog extends BaseValueObject {
    private Integer id;

    private Integer tradeId;

    private int tradeStatus;

    private String sendMsg;

    private String receiveMsg;

    private String operUser;

    private String operTime;

    private String operIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg == null ? null : sendMsg.trim();
    }

    public String getReceiveMsg() {
        return receiveMsg;
    }

    public void setReceiveMsg(String receiveMsg) {
        this.receiveMsg = receiveMsg == null ? null : receiveMsg.trim();
    }
    
    public String getOperUser() {
		return operUser;
	}

	public void setOperUser(String operUser) {
		this.operUser = operUser;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOperIp() {
        return operIp;
    }

    public void setOperIp(String operIp) {
        this.operIp = operIp == null ? null : operIp.trim();
    }
}