package com.fx.bonus.model;

import com.fx.user.model.UserRegister;
import mybatis.framework.core.model.BaseValueObject;

import java.math.BigDecimal;
import java.util.Date;

public class IbcommissionPaymentLog extends BaseValueObject {
    private Integer id;

    private String type;

    private String operator;

    private Integer operatorId;

    private Date startTime;

    private BigDecimal amount;

    private Date createTime;

    private Date updateTime;

    private String updateUser;

    private Integer updateUserId;

    private Date endTime;

    private String comment;

    private Integer mt4account;

    private Integer uid;

    private Integer ibId;

    private String commissionIds;

    private Integer status;

    private BigDecimal applyAmount;

    private BigDecimal totalVolume;

    private Integer timesMonth;

    private BigDecimal fee;

    private String refuseReason;

    private String applyComment;

    public String getApplyComment() {
        return applyComment;
    }

    public void setApplyComment(String applyComment) {
        this.applyComment = applyComment;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    private UserRegister userRegister;

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
    }

    public Integer getTimesMonth() {
        return timesMonth;
    }

    public void setTimesMonth(Integer timesMonth) {
        this.timesMonth = timesMonth;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTotalVolume() {
        return totalVolume;
    }

    public void setTotalVolume(BigDecimal totalVolume) {
        this.totalVolume = totalVolume;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 回显支付时间段
     * @return
     */
    private String timerange;

    public String getCommissionIds() {
        return commissionIds;
    }

    public void setCommissionIds(String commissionIds) {
        this.commissionIds = commissionIds;
    }

    public String getTimerange() {
        return timerange;
    }

    public void setTimerange(String timerange) {
        this.timerange = timerange;
    }

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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getMt4account() {
        return mt4account;
    }

    public void setMt4account(Integer mt4account) {
        this.mt4account = mt4account;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIbId() {
        return ibId;
    }

    public void setIbId(Integer ibId) {
        this.ibId = ibId;
    }
}