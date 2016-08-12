package com.fx.mybatis.builder;

import com.fx.mybatis.SQLSegment;
import com.fx.util.DataRuleLevel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.parsing.GenericTokenParser;
import org.apache.ibatis.parsing.TokenHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/5/27.
 */
public class DataRuleSqlBuilder {

    public static final String ORIGINAL_PARAMS_KEY = "originalSql";

    public static String buildDataRuleSql(String sqlExp, SQLSegment originalSql, DataRuleLevel.Level level) {
        if(DataRuleLevel.Level.UNDER_LEVEL.equals(level)){
            return buildUnderLevelSql(sqlExp, originalSql);
        }else if(DataRuleLevel.Level.ALL_LEVEL.equals(level)){
            return buildAllLevelSql(sqlExp, originalSql);
        }
        throw new RuntimeException("cannot support DataRuleLevel, please check.");
    }

    /**
     * 拼装所有下级的sql
     * @param sqlExp
     * @param originalSql
     * @return
     */
    private static String buildAllLevelSql(String sqlExp, SQLSegment originalSql) {
        Map<String, Object> paramsMap = getAllParams(originalSql);
        return parse(sqlExp, paramsMap);
    }

    private static Map<String, Object> getAllParams(SQLSegment originalSql) {
        Map<String, Object> paramsMap = originalSql.toParamsMap(ORIGINAL_PARAMS_KEY);
        paramsMap.putAll(getCurrentUser2Map());
        return paramsMap;
    }

    private static Map<? extends String, ?> getCurrentUser2Map() {
        Map<String, Object> userMap = new HashMap<>();
        //TODO: sql拦截器调用代码，目前拦截器已经停用
//        Admin admin = UserUtil.getCurrAdmin();
//        userMap.put("admin.ib_id", admin.getIb_Id());
//        userMap.put("admin.id", admin.getId());
//        userMap.put("admin.userId", admin.getUserId());
//        userMap.put("admin.role.id", admin.getRoleId());
//        userMap.put("admin.role.level", admin.getRole().getLevel());
//        userMap.put("admin.role.roletype", admin.getRole().getRoletype());
        return userMap;
    }

    /**
     * 拼装直属下级的sql
     * @param sqlExp
     * @param originalSql
     * @return
     */
    private static String buildUnderLevelSql(String sqlExp, SQLSegment originalSql) {
        StringBuffer sql = new StringBuffer();
        sql.append(originalSql.getSelectPart()).append(SQLSegment.SQL_SPACE);
        sql.append(originalSql.getFromPart()).append(SQLSegment.SQL_SPACE);
        if(originalSql.isExistWhere()){
            sql.append(originalSql.getWherePart()).append(SQLSegment.SQL_SPACE);

        }else{
            sql.append(SQLSegment.SQL_WHERE).append(SQLSegment.SQL_SPACE);
            sql.append(SQLSegment.SQL_WHERE_EMPTY_CONDITION).append(SQLSegment.SQL_SPACE);
        }
        sql.append(parse(sqlExp, getAllParams(originalSql))).append(SQLSegment.SQL_SPACE);
        if(originalSql.getOrderPart()!=null){
            sql.append(originalSql.getOrderPart()).append(SQLSegment.SQL_SPACE);
        }
        return sql.toString();
    }

    public static String parse(String sqlExp, Map<String, Object> paramsMap) {
        ParameterDataRuleMappingTokenHandler handler = new ParameterDataRuleMappingTokenHandler(paramsMap);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String sql = parser.parse(sqlExp);
        return sql;
    }

    private static class ParameterDataRuleMappingTokenHandler implements TokenHandler {

        private Map<String, Object> paramsMap;

        public ParameterDataRuleMappingTokenHandler(Map<String, Object> paramsMap){
            this.paramsMap = paramsMap;
        }

        @Override
        public String handleToken(String content) {
            //这里需要根据对象类型进行处理，目前先只处理简单类型
            Object obj = paramsMap.get(content);
            if(obj == null){
                return "";
            }
            return obj.toString();
        }

    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        String al = "WITH data_level AS ( SELECT * FROM #{originalSql.tablePart} WHERE is_del = 0 AND sell_id = #{admin.id} UNION ALL SELECT L.* FROM data_level l, " +
                "#{originalSql.tablePart} u WHERE u.is_ibid = l.id ) #{originalSql.selectPart} FROM data_level WHERE #{originalSql.wherePart} #{originalSql.orderPart}";
        map.put("ALL_LEVEL", al);
        String ul = " and ib_id = #{admin.user_id}";
        map.put("UNDER_LEVEL", ul);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(map);
        System.out.println(json);
        Map<String,String> jsonMap = gson.fromJson(json, new TypeToken<Map<String, String>>(){}.getType());
        Iterator<String> keySet = jsonMap.keySet().iterator();
        while(keySet.hasNext()){
            String key = keySet.next();
            System.out.println(key + ":" + jsonMap.get(key));
        }

        System.out.println(DataRuleLevel.Level.All_DATA.name());
    }

}
