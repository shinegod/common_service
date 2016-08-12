package com.fx.mt4TradeRecord.model;

/**
 * Created by Administrator on 2015/10/15.
 */
//目前用来封装昨天交易量的对象
public class Mt4Volume {
    private String type;

    private String date;

    private String bbookFx;

    private String bbookIndex;

    private String sbookFx;

    private String sbookIndex;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBbookFx() {
        return bbookFx;
    }

    public void setBbookFx(String bbookFx) {
        this.bbookFx = bbookFx;
    }

    public String getBbookIndex() {
        return bbookIndex;
    }

    public void setBbookIndex(String bbookIndex) {
        this.bbookIndex = bbookIndex;
    }

    public String getSbookFx() {
        return sbookFx;
    }

    public void setSbookFx(String sbookFx) {
        this.sbookFx = sbookFx;
    }

    public String getSbookIndex() {
        return sbookIndex;
    }

    public void setSbookIndex(String sbookIndex) {
        this.sbookIndex = sbookIndex;
    }
}
