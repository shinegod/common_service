package com.fx.RPQueryVolume.model;

import java.math.BigDecimal;
import mybatis.framework.core.model.BaseValueObject;

public class RPQueryVolume extends BaseValueObject {
    private Integer id;

    private String type;

    private String date;

    private String bbookfx;

    private String bbookindex;

    private String sbookfx;

    private String sbookindex;

    private String bbookfxprofit;

    private String bbookindexprofit;

    private String sbookfxprofit;

    private String sbookindexprofit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getBbookfx() {
        return bbookfx;
    }

    public void setBbookfx(String bbookfx) {
        this.bbookfx = bbookfx;
    }

    public String getBbookindex() {
        return bbookindex;
    }

    public void setBbookindex(String bbookindex) {
        this.bbookindex = bbookindex;
    }

    public String getSbookfx() {
        return sbookfx;
    }

    public void setSbookfx(String sbookfx) {
        this.sbookfx = sbookfx;
    }

    public String getSbookindex() {
        return sbookindex;
    }

    public void setSbookindex(String sbookindex) {
        this.sbookindex = sbookindex;
    }

    public String getBbookfxprofit() {
        return bbookfxprofit;
    }

    public void setBbookfxprofit(String bbookfxprofit) {
        this.bbookfxprofit = bbookfxprofit;
    }

    public String getBbookindexprofit() {
        return bbookindexprofit;
    }

    public void setBbookindexprofit(String bbookindexprofit) {
        this.bbookindexprofit = bbookindexprofit;
    }

    public String getSbookfxprofit() {
        return sbookfxprofit;
    }

    public void setSbookfxprofit(String sbookfxprofit) {
        this.sbookfxprofit = sbookfxprofit;
    }

    public String getSbookindexprofit() {
        return sbookindexprofit;
    }

    public void setSbookindexprofit(String sbookindexprofit) {
        this.sbookindexprofit = sbookindexprofit;
    }

}