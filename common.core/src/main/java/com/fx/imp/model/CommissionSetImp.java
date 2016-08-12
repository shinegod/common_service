package com.fx.imp.model;

import mybatis.framework.core.model.BaseValueObject;

public class CommissionSetImp extends BaseValueObject {
    private Integer login;

    private String mt4Group;

    private String productGroup;

    private String closingForm;

    private String commissionVal;

    private String comms;

    private String tradeFlag;

    private String datasource;

    private String ud1;

    private String ud2;

    private String ud3;

    private String ud4;

    private String ud5;

    private String ud6;

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getMt4Group() {
        return mt4Group;
    }

    public void setMt4Group(String mt4Group) {
        this.mt4Group = mt4Group == null ? null : mt4Group.trim();
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup == null ? null : productGroup.trim();
    }

    public String getClosingForm() {
        return closingForm;
    }

    public void setClosingForm(String closingForm) {
        this.closingForm = closingForm == null ? null : closingForm.trim();
    }

    public String getCommissionVal() {
        return commissionVal;
    }

    public void setCommissionVal(String commissionVal) {
        this.commissionVal = commissionVal == null ? null : commissionVal.trim();
    }

    public String getComms() {
        return comms;
    }

    public void setComms(String comms) {
        this.comms = comms == null ? null : comms.trim();
    }

    public String getTradeFlag() {
        return tradeFlag;
    }

    public void setTradeFlag(String tradeFlag) {
        this.tradeFlag = tradeFlag == null ? null : tradeFlag.trim();
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource == null ? null : datasource.trim();
    }

    public String getUd1() {
        return ud1;
    }

    public void setUd1(String ud1) {
        this.ud1 = ud1 == null ? null : ud1.trim();
    }

    public String getUd2() {
        return ud2;
    }

    public void setUd2(String ud2) {
        this.ud2 = ud2 == null ? null : ud2.trim();
    }

    public String getUd3() {
        return ud3;
    }

    public void setUd3(String ud3) {
        this.ud3 = ud3 == null ? null : ud3.trim();
    }

    public String getUd4() {
        return ud4;
    }

    public void setUd4(String ud4) {
        this.ud4 = ud4 == null ? null : ud4.trim();
    }

    public String getUd5() {
        return ud5;
    }

    public void setUd5(String ud5) {
        this.ud5 = ud5 == null ? null : ud5.trim();
    }

    public String getUd6() {
        return ud6;
    }

    public void setUd6(String ud6) {
        this.ud6 = ud6 == null ? null : ud6.trim();
    }


    @Override
    public String toString() {
        return "CommissionSetImp{" +
                "login=" + login +
                ", mt4Group='" + mt4Group + '\'' +
                ", productGroup='" + productGroup + '\'' +
                ", closingForm='" + closingForm + '\'' +
                ", commissionVal='" + commissionVal + '\'' +
                ", comms='" + comms + '\'' +
                ", tradeFlag='" + tradeFlag + '\'' +
                ", datasource='" + datasource + '\'' +
                ", ud1='" + ud1 + '\'' +
                ", ud2='" + ud2 + '\'' +
                ", ud3='" + ud3 + '\'' +
                ", ud4='" + ud4 + '\'' +
                ", ud5='" + ud5 + '\'' +
                ", ud6='" + ud6 + '\'' +
                '}';
    }
}