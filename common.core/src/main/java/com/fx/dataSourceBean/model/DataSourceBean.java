package com.fx.dataSourceBean.model;

import mybatis.framework.core.model.BaseValueObject;

public class DataSourceBean extends BaseValueObject {
    private Integer id;

    private String dataName;

    private String dataIp;

    private String dataUser;

    private String dataPass;

    private String mt4Group;

    private Integer status;

    private Integer isDel;

    private String databaseType;

    private String comment;

    private String mt4DemoIp;

    private String mt4LiveIp;

    private int mt4User;

    private String mt4Pass;

    private String dataBaseName;

    private int sourceWebSideType;

    private String databasePort;

    private int mt4DatasourceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName == null ? null : dataName.trim();
    }

    public String getDataIp() {
        return dataIp;
    }

    public void setDataIp(String dataIp) {
        this.dataIp = dataIp == null ? null : dataIp.trim();
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser == null ? null : dataUser.trim();
    }

    public String getDataPass() {
        return dataPass;
    }

    public void setDataPass(String dataPass) {
        this.dataPass = dataPass == null ? null : dataPass.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType == null ? null : databaseType.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getMt4Pass() {
        return mt4Pass;
    }

    public void setMt4Pass(String mt4Pass) {
        this.mt4Pass = mt4Pass;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getMt4Group() {
        return mt4Group;
    }

    public void setMt4Group(String mt4Group) {
        this.mt4Group = mt4Group;
    }

    public String getMt4DemoIp() {
        return mt4DemoIp;
    }

    public void setMt4DemoIp(String mt4DemoIp) {
        this.mt4DemoIp = mt4DemoIp;
    }

    public String getMt4LiveIp() {
        return mt4LiveIp;
    }

    public void setMt4LiveIp(String mt4LiveIp) {
        this.mt4LiveIp = mt4LiveIp;
    }

    public int getMt4User() {
        return mt4User;
    }

    public void setMt4User(int mt4User) {
        this.mt4User = mt4User;
    }

    public String getDatabasePort() {
        return databasePort;
    }

    public void setDatabasePort(String databasePort) {
        this.databasePort = databasePort;
    }

    public int getSourceWebSideType() {
        return sourceWebSideType;
    }

    public void setSourceWebSideType(int sourceWebSideType) {
        this.sourceWebSideType = sourceWebSideType;
    }

    public int getMt4DatasourceType() {
        return mt4DatasourceType;
    }

    public void setMt4DatasourceType(int mt4DatasourceType) {
        this.mt4DatasourceType = mt4DatasourceType;
    }
}