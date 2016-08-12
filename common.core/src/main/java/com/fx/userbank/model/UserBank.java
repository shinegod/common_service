package com.fx.userbank.model;

import mybatis.framework.core.model.BaseValueObject;

public class UserBank extends BaseValueObject {
   
	
	private Integer id;

    private Integer uid;

    private String bankName;

    private String bankNameKey;

    private String bankBranch;

    private String bankAccountName;

    private String swiftCode;

    private String bankAccountNumber;

    private Integer mt4Account;

    private String edocId;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankNameKey() {
        return bankNameKey;
    }

    public void setBankNameKey(String bankNameKey) {
        this.bankNameKey = bankNameKey == null ? null : bankNameKey.trim();
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode == null ? null : swiftCode.trim();
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber == null ? null : bankAccountNumber.trim();
    }

    public Integer getMt4Account() {
        return mt4Account;
    }

    public void setMt4Account(Integer mt4Account) {
        this.mt4Account = mt4Account;
    }

    public String getEdocId() {
        return edocId;
    }

    public void setEdocId(String edocId) {
        this.edocId = edocId == null ? null : edocId.trim();
    }
}