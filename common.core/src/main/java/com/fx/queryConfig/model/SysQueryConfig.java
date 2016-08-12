package com.fx.queryConfig.model;

import com.fx.freemarker.tags.SelectOption;
import mybatis.framework.core.model.BaseValueObject;

import java.util.List;

public class SysQueryConfig extends BaseValueObject {
    private Integer id;

    private Integer menuid;

    private String fieldName;

    private String fieldCode;

    private String type;

    private Integer isDel;

    private String contents;

    private String queryCondition;

    private List<SelectOption> queryConditionList;

    private List<SelectOption> selectData;

    private List<SysQueryConfig> fields ;

    public List<SelectOption> getSelectData() {
        return selectData;
    }

    public void setSelectData(List<SelectOption> selectData) {
        this.selectData = selectData;
    }

    public List<SelectOption> getQueryConditionList() {
        return queryConditionList;
    }

    public void setQueryConditionList(List<SelectOption> queryConditionList) {
        this.queryConditionList = queryConditionList;
    }

    public List<SysQueryConfig> getFields() {
        return fields;
    }

    public void setFields(List<SysQueryConfig> fields) {
        this.fields = fields;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName == null ? null : fieldName.trim();
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode == null ? null : fieldCode.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition == null ? null : queryCondition.trim();
    }
}