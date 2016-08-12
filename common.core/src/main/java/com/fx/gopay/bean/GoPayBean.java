package com.fx.gopay.bean;

import java.io.Serializable;

/**
 * gopay 的实体bean
 * Created by Administrator on 2015/11/30.
 */
public class GoPayBean implements Serializable {

    private String version;
    private String tranCode;
    private String merchantID;
    private String merOrderNum;
    private String tranAmt;
    private String feeAmt;
    private String goodsName;
    private String buyerName;
    private String tranDateTime;
    private String frontMerUrl;
    private String backgroundMerUrl;
    private String orderId;
    private String gopayOutOrderId;
    private String tranIP;
    private String respCode;
    private String tranFinishTime;
    private String msgExt;
    private String virCardNoIn;
    private String merRemark1;//国际化语言
    private String merRemark2;//真实金额
    private String VerficationCode;
    private String gopayServerTime;
    private String signValue;
    private String gopaySubmitUrl;

    public String getGopaySubmitUrl() {
        return gopaySubmitUrl;
    }

    public void setGopaySubmitUrl(String gopaySubmitUrl) {
        this.gopaySubmitUrl = gopaySubmitUrl;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getVirCardNoIn() {
        return virCardNoIn;
    }

    public void setVirCardNoIn(String virCardNoIn) {
        this.virCardNoIn = virCardNoIn;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getVerficationCode() {
        return VerficationCode;
    }

    public void setVerficationCode(String verficationCode) {
        VerficationCode = verficationCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getMerOrderNum() {
        return merOrderNum;
    }

    public void setMerOrderNum(String merOrderNum) {
        this.merOrderNum = merOrderNum;
    }

    public String getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(String tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getFeeAmt() {
        return feeAmt;
    }

    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    public String getTranDateTime() {
        return tranDateTime;
    }

    public void setTranDateTime(String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }

    public String getFrontMerUrl() {
        return frontMerUrl;
    }

    public void setFrontMerUrl(String frontMerUrl) {
        this.frontMerUrl = frontMerUrl;
    }

    public String getBackgroundMerUrl() {
        return backgroundMerUrl;
    }

    public void setBackgroundMerUrl(String backgroundMerUrl) {
        this.backgroundMerUrl = backgroundMerUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGopayOutOrderId() {
        return gopayOutOrderId;
    }

    public void setGopayOutOrderId(String gopayOutOrderId) {
        this.gopayOutOrderId = gopayOutOrderId;
    }

    public String getTranIP() {
        return tranIP;
    }

    public void setTranIP(String tranIP) {
        this.tranIP = tranIP;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getTranFinishTime() {
        return tranFinishTime;
    }

    public void setTranFinishTime(String tranFinishTime) {
        this.tranFinishTime = tranFinishTime;
    }

    public String getMsgExt() {
        return msgExt;
    }

    public void setMsgExt(String msgExt) {
        this.msgExt = msgExt;
    }

    public String getMerRemark1() {
        return merRemark1;
    }

    public void setMerRemark1(String merRemark1) {
        this.merRemark1 = merRemark1;
    }

    public String getMerRemark2() {
        return merRemark2;
    }

    public void setMerRemark2(String merRemark2) {
        this.merRemark2 = merRemark2;
    }

    public String getSignValue() {
        return signValue;
    }

    public void setSignValue(String signValue) {
        this.signValue = signValue;
    }

    public String getGopayServerTime() {
        return gopayServerTime;
    }

    public void setGopayServerTime(String gopayServerTime) {
        this.gopayServerTime = gopayServerTime;
    }
}
