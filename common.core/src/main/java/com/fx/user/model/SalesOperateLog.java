package com.fx.user.model;

import java.util.Date;
import mybatis.framework.core.model.BaseValueObject;

public class SalesOperateLog extends BaseValueObject {
    private Integer id;

    private String operator;

    private Integer uid;

    private Integer operatorId;

    private String operating;

    private String description;

    private String pic;

    private Integer picId;

    private Date operationTime;

    private Date createTime;

    private String fromModel;

    private String lifecycleType;

    public String getLifecycleType() {
        return lifecycleType;
    }

    public void setLifecycleType(String lifecycleType) {
        this.lifecycleType = lifecycleType;
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperating() {
        return operating;
    }

    public void setOperating(String operating) {
        this.operating = operating == null ? null : operating.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFromModel() {
        return fromModel;
    }

    public void setFromModel(String fromModel) {
        this.fromModel = fromModel == null ? null : fromModel.trim();
    }
}