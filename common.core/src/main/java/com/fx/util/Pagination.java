package com.fx.util;

import java.util.List;
import java.util.Map;

/**
 * Created by Michael on 2015/5/15.
 */
public class Pagination<T> {

    private String order;

    private int limit = 10;

    private int offset;

    private int ibId;

    private int ibId_all;  //查询下级所有

    private String orgId;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    private int dataSource;

    private String search;

    private Integer custCaId;

    private String interest;

    private String lead_status;

    private Integer ibUserType;

    private Integer commissionType;

    public int getIbId_all() {
        return ibId_all;
    }

    public void setIbId_all(int ibId_all) {
        this.ibId_all = ibId_all;
    }

    public Integer getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    public Integer getIbUserType() {
        return ibUserType;
    }

    public void setIbUserType(Integer ibUserType) {
        this.ibUserType = ibUserType;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLead_status() {
        return lead_status;
    }

    public void setLead_status(String lead_status) {
        this.lead_status = lead_status;
    }

    private Integer returnTime;

    private int total;

    private List<?> rows;

    private T entity;

    private String startData;

    private String endData;

    private String queryConfig;

    private Integer mt4Type;

    /**
     * 表格合计行数据
     */
    private List<Map<String, String>> totalRow;

    private int depositStatus;

    private String operationMod;

    private String operationType;

    private String openaccounttype;

    private String agentuserQuery;

    public String getAgentuserQuery() {
        return agentuserQuery;
    }

    public void setAgentuserQuery(String agentuserQuery) {
        this.agentuserQuery = agentuserQuery;
    }

    public String getOpenaccounttype() {
        return openaccounttype;
    }

    public void setOpenaccounttype(String openaccounttype) {
        this.openaccounttype = openaccounttype;
    }

    public String getOperationMod() {
        return operationMod;
    }

    public void setOperationMod(String operationMod) {
        this.operationMod = operationMod;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(int depositStatus) {
        this.depositStatus = depositStatus;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getIbId() {
        return ibId;
    }

    public void setIbId(int ibId) {
        this.ibId = ibId;
    }

    public int getDataSource() {
        return dataSource;
    }

    public void setDataSource(int dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getCustCaId() {
        return custCaId;
    }

    public void setCustCaId(Integer custCaId) {
        this.custCaId = custCaId;
    }

    public Integer getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Integer returnTime) {
        this.returnTime = returnTime;
    }

    public List<Map<String, String>> getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(List<Map<String, String>> totalRow) {
        this.totalRow = totalRow;
    }

    public String getStartData() {
        return startData;
    }

    public void setStartData(String startData) {
        this.startData = startData;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getQueryConfig() {
        return queryConfig;
    }

    public void setQueryConfig(String queryConfig) {
        this.queryConfig = queryConfig;
    }

    public Integer getMt4Type() {
        return mt4Type;
    }

    public void setMt4Type(Integer mt4Type) {
        this.mt4Type = mt4Type;
    }
}
