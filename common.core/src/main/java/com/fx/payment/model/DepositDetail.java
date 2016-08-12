package com.fx.payment.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class DepositDetail extends BaseValueObject {

    private Integer uid;
    
    private String bankName;

    private BigDecimal operMoney;
    
    private String branchName;
    
    private String swiftCode;
    
    private String bakName;
    
    private String cardNumber;
    
    private int mt4Account;
    
    private BigDecimal rate;
    private String date;
    
    

	public DepositDetail(Integer uid, BigDecimal operMoney, String bankName,
			String swiftCode, String bakName, String cardNumber,String branchName,
			int mt4Account, BigDecimal rate,String date) {
		super();
		this.uid = uid;
		this.branchName=branchName;
		this.bankName = bankName;
		this.operMoney = operMoney;
		this.swiftCode = swiftCode;
		this.bakName = bakName;
		this.cardNumber = cardNumber;
		this.mt4Account = mt4Account;
		this.rate = rate;
		this.date=date;
	}
	
	public DepositDetail(Integer uid, BigDecimal operMoney, String bankName,
			String swiftCode, String bakName, String cardNumber,String branchName,
			int mt4Account, BigDecimal rate) {
		super();
		this.uid = uid;
		this.branchName=branchName;
		this.bankName = bankName;
		this.operMoney = operMoney;
		this.swiftCode = swiftCode;
		this.bakName = bakName;
		this.cardNumber = cardNumber;
		this.mt4Account = mt4Account;
		this.rate = rate;
	}

	public DepositDetail(Integer uid, BigDecimal operMoney,
			int mt4Account, BigDecimal rate) {
		super();
		this.uid = uid;
		this.operMoney = operMoney;
		this.mt4Account = mt4Account;
		this.rate = rate;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getOperMoney() {
		return operMoney;
	}

	public void setOperMoney(BigDecimal operMoney) {
		this.operMoney = operMoney;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}

	public String getBakName() {
		return bakName;
	}

	public void setBakName(String bakName) {
		this.bakName = bakName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public int getMt4Account() {
		return mt4Account;
	}

	public void setMt4Account(int mt4Account) {
		this.mt4Account = mt4Account;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
    
    
    
}