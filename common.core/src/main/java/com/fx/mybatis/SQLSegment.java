package com.fx.mybatis;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/5/27.
 */
public class SQLSegment {

    public static final String SQL_SPACE = " ";
    public static final String SQL_WHERE = " WHERE ";
    public static final String SQL_WHERE_EMPTY_CONDITION = " 1=1 ";
    public static final String SQl_FROM =" FROM ";
    public static final String SQL_AND = " AND ";

    private String selectWord;

    private String originalSql;

    private String selectField;

    private String selectPart;

    private String selectFromPart;

    private String fromPart;

    private boolean multiTable;

    private boolean existWhere;

    private String wherePart;

    private String orderPart;

    private String tablePart;

    public SQLSegment(String oldSql) {
        this.originalSql = oldSql;
        this.existWhere = false;
        parser(originalSql);
    }

    /**
     * 解析sql并拆分各部分
     * 目前的解析拆分只支持单表和多表关联的select，where后支持有order by.目前不支持group by 和having等其他子句
     * @param originalSql
     */
    private void parser(String originalSql) {
        String sqlUpper = StringUtils.upperCase(originalSql);
        this.selectWord  = " SELECT ";
        this.selectFromPart = originalSql.substring(0, sqlUpper.indexOf("WHERE "));
        this.selectPart = originalSql.substring(0, sqlUpper.lastIndexOf("FROM"));
        if(sqlUpper.indexOf("SELECT") != -1){
            this.selectField = originalSql.substring(sqlUpper.indexOf("SELECT") + 6, sqlUpper.indexOf("FROM"));
        }
        if(sqlUpper.indexOf("ORDER") != -1){
            this.orderPart = originalSql.substring(sqlUpper.lastIndexOf("ORDER"), sqlUpper.length());
        }
        this.tablePart = originalSql.substring(sqlUpper.indexOf("FROM") + 4, sqlUpper.indexOf("WHERE "));
        int whereEnd = sqlUpper.length();
        if(sqlUpper.lastIndexOf("ORDER BY") != -1){
            whereEnd = sqlUpper.lastIndexOf("ORDER BY");
        }
        int fromPartEnd = originalSql.length();
        if(sqlUpper.indexOf("WHERE ") != -1){
            this.existWhere = true;
            this.wherePart = originalSql.substring(sqlUpper.indexOf("WHERE "), whereEnd);
            fromPartEnd = sqlUpper.lastIndexOf("WHERE ");
        }
        this.fromPart = originalSql.substring(sqlUpper.indexOf("FROM"), fromPartEnd);

    }

    public Map<String, Object> toParamsMap(String prefix) {
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(prefix)){
            prefix += ".";
        }
        map.put(prefix + "selectField", this.getSelectField());
        map.put(prefix + "orderPart", this.getOrderPart());
        map.put(prefix + "selectWord", this.getSelectWord());
        map.put(prefix + "fromPart", this.getFromPart());
        map.put(prefix + "originalSql", this.getOriginalSql());
        map.put(prefix + "selectFromPart", this.getSelectFromPart());
        map.put(prefix + "selectPart", this.getSelectPart());
        map.put(prefix + "tablePart", this.getTablePart());
        map.put(prefix + "wherePart", this.getWherePart());
        return map;
    }

    public String getOriginalSql() {
        return originalSql;
    }

    public String getSelectField() {
        return selectField;
    }

    public boolean isMultiTable() {
        return getTablePart().indexOf(",") != -1;
    }

    public boolean isExistWhere() {
        return StringUtils.isNotBlank(getWherePart());
    }

    public String getWherePart() {
        return wherePart;
    }

    public String getOrderPart() {
        return orderPart;
    }

    public String getSelectWord() {
        return selectWord;
    }

    public String getTablePart() {
        return tablePart;
    }

    public void setTablePart(String tablePart) {
        this.tablePart = tablePart;
    }

    public String getSelectPart() {
        return selectPart;
    }

    public String getFromPart() {
        return fromPart;
    }

    public String getSelectFromPart() {
        return selectFromPart;
    }
}
