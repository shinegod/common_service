package com.fx.util;

import com.fx.crm.sys.datarule.model.DataRuleDefine;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/5/25.
 */
public class DataRuleUtil {

    private static final String DATA_RULE_DEFINE_BY_SQL = "DATA_RULE_DEFINE_BY_SQL";
    public static final String DATA_RULE_SQL_PARAM_KEY="DATA_RULE_SQL_PARAM_KEY";

    /**
     * 将数据权限的集合转换成sqlId为key的map
     * 这里还会处理sqlId中对方法的拼装规则如下:
     *  拼接的:com.fx.admin.dao.IAdminDao.getQueryCount|queryByCondition
     *  简单的:com.fx.admin.dao.IAdminDao.queryByCondition
     * @return
     */
    public static Map<String, DataRuleDefine> getAllDataRuleDefineBySqlKey() {
        Object objSqlRuleDefine = CacheMgr.get(DataRuleUtil.DATA_RULE_DEFINE_BY_SQL);
        if(objSqlRuleDefine != null){
            return (Map<String, DataRuleDefine>)objSqlRuleDefine;
        }
        List<DataRuleDefine> allDataDefine = CacheMgr.getAllDataDefine();
        if(allDataDefine == null){
            return new HashMap<>();
        }
        Map<String, DataRuleDefine> dataRuleDefineMap = new HashMap<>();
        for(DataRuleDefine define : allDataDefine){
            if(StringUtils.indexOf(define.getSqlId(), "|") != -1){
                dataRuleDefineMap.putAll(getMultiSqlIdMap(define));
            }else{
                dataRuleDefineMap.put(define.getSqlId(), define);
            }
        }
        CacheMgr.put(DATA_RULE_DEFINE_BY_SQL, dataRuleDefineMap);
        return dataRuleDefineMap;
    }

    private static Map<? extends String, ? extends DataRuleDefine> getMultiSqlIdMap(DataRuleDefine define) {
        Map<String, DataRuleDefine> map = new HashMap<>();
        String[] sqlArray = StringUtils.split(define.getSqlId(), "|");
        String sqlPrefix = StringUtils.substring(sqlArray[0], 0, StringUtils.lastIndexOf(sqlArray[0], ".") + 1);
        String sqlId = null;
        for(int j=0; j < sqlArray.length; j++){
            if(j == 0){
                sqlId = sqlArray[j];
            }else{
                sqlId = sqlPrefix + sqlArray[j];
            }
            map.put(sqlId, define);
        }
        return map;
    }

    /**
     * 将数据权限的集合转换成menuid为key的map
     */
    public static Map<String, DataRuleDefine> getAllDataRuleDefineByMenuKey(List<DataRuleDefine> allDataDefine) {
        Map<String, DataRuleDefine> dataRuleDefineMap = new HashMap<>();
        for(DataRuleDefine define : allDataDefine){
            dataRuleDefineMap.put(define.getMenuId(), define);
        }
        return dataRuleDefineMap;
    }

    /**
     * 获取最大的权限，返回值以menuid为key的数据权限描述对象
     * @param allDataRule
     * @return
     */
    public static Map<String, DataRuleLevel> getAuthDataRuleLevel(List<String> allDataRule) {
        Map<String, DataRuleLevel> sqlAuth = new HashMap<>();
        String menuId = "";
        for(String mixId : allDataRule){
            menuId = StringUtils.substring(mixId, 0, mixId.indexOf("!"));
            if(sqlAuth.containsKey(menuId)){
                sqlAuth.get(menuId).setLevelByString(StringUtils.substring(mixId, mixId.indexOf("!") + 1, mixId.length()));
            }else{
                sqlAuth.put(menuId, new DataRuleLevel(mixId));
            }
        }
        return sqlAuth;
    }


    /**
     * 判断该sql是否配置了拦截规则
     * @param sqlId
     * @return
     */
    public static boolean isInterceptor(String sqlId) {
        Map<String, DataRuleDefine> dataRuleDefineMap = getAllDataRuleDefineBySqlKey();
        return dataRuleDefineMap.containsKey(sqlId);
    }

    //返回所有menuId的集合
    public static List AllmenuId() {
        List<DataRuleDefine> dataRuleDefineList = CacheMgr.getAllDataDefine();
        List menuIdList = new ArrayList();
        if(dataRuleDefineList!=null){
            for(DataRuleDefine dataRuleDefine : dataRuleDefineList){
                menuIdList.add(dataRuleDefine.getMenuId());
            }
        }
        return menuIdList;
    }

}
