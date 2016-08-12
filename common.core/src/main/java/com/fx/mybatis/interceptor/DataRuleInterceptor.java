package com.fx.mybatis.interceptor;


import com.fx.crm.sys.datarule.model.DataRuleDefine;
import com.fx.mybatis.SQLSegment;
import com.fx.mybatis.builder.DataRuleSqlBuilder;
import com.fx.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/5/20.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class DataRuleInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(DataRuleInterceptor.class);

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();


    private static final Set<String> noInterceptSql = new HashSet<>();


    static {
        noInterceptSql.add("com.fx.admin.dao.IAdminDao.selectByName");
        noInterceptSql.add("com.fx.admin.dao.IAdminDao.updateByPrimaryKey");
        noInterceptSql.add("com.fx.admin.dao.IAdminDao.selectByPrimaryKey");
        noInterceptSql.add("com.fx.admin.dao.IRoleDao.selectByPrimaryKey");
        noInterceptSql.add("com.fx.admin.dao.IRoleDao.getMenuPermissions");
        noInterceptSql.add("com.fx.crm.sys.menu.dao.IMenuDao.getMenusById");
        noInterceptSql.add("com.fx.crm.sys.menu.dao.IMenuDao.getChildrenMenus");
        noInterceptSql.add("com.fx.crm.sys.menu.dao.IMenuDao.getMenuPermissions");
        noInterceptSql.add("com.fx.crm.sys.datarule.dao.IDataRuleDefineDao.selectAll");
        noInterceptSql.add("com.fx.admin.dao.IRoleDao.getMenuDataRules");
        noInterceptSql.add("com.fx.crm.sys.menu.dao.IMenuDao.getMenusByRoleId");
        noInterceptSql.add("com.fx.admin.dao.IAdminDao.getRoleName");
        noInterceptSql.add("com.fx.admin.dao.IRoleDao.getPowerItemList");
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation
                .getTarget();
        MetaObject metaStatementHandler = MetaObject.forObject(
                statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY);
        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object,
                    DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        String oldSql = (String) metaStatementHandler
                .getValue("delegate.boundSql.sql");
        Configuration configuration = (Configuration) metaStatementHandler
                .getValue("delegate.configuration");

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
                .getValue("delegate.mappedStatement");
        String sqlId = mappedStatement.getId();
        logger.debug(">>>>>>>>>>>>>>>>>{}原sql:{}", new Object[]{sqlId, oldSql});


        if(noInterceptSql.contains(sqlId)){
            return invocation.proceed();
        }
        if(!DataRuleUtil.isInterceptor(sqlId)){
            return invocation.proceed();
        }

        Object obj = statementHandler.getParameterHandler().getParameterObject();
        Map dataRuleMap = null;
        if(obj!=null){
            dataRuleMap = (HashMap)obj;
        }

        List menuIdlist = DataRuleUtil.AllmenuId();
        if(menuIdlist!=null&&dataRuleMap.get("menuId")!=null){
            if(!menuIdlist.contains(dataRuleMap.get("menuId"))){
                return invocation.proceed();
            }
        } else {
            return invocation.proceed();
        }
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");
        BoundSql boundSql = delegate.getBoundSql();

        //缓存读取配置过数据权限的sql
        DataRuleDefine currRule = getCurrentDataRule(sqlId);
        if(currRule == null){
            return invocation.proceed();
        }
        DataRuleLevel currLevel = null;

        String dataRule ="";
        if(dataRuleMap.get(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY)!=null){
            currLevel= new DataRuleLevel();
            dataRule =  dataRuleMap.get(DataRuleUtil.DATA_RULE_SQL_PARAM_KEY).toString();
            currLevel.setLevelByString(dataRule);
        } else {
            currLevel = getCurrentDataRuleLevel(currRule.getMenuId());
        }
        // dataRule = "UNDER_LEVEL";

        //TODO:后续需要确认的逻辑:如果没有配置权限默认不进行数据权限验证
        if(currLevel == null){
            return invocation.proceed();
        }
        //如果是查询全部数据的权限，放过
        if(DataRuleLevel.Level.All_DATA.equals(currLevel.getLevel())){
            return invocation.proceed();
        }
        Map<String, Object> addParams = new HashMap<String, Object>();
//        Object obj = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
//        MapperMethod.ParamMap parameterObject = null ;
//        if(obj == null){
//            parameterObject = new MapperMethod.ParamMap();
//        }else{
//            parameterObject = (MapperMethod.ParamMap)obj;
//        }
        String sqlExp = getCurrentDataRuleExp(currRule.getSqlExp(), currLevel.getLevel());
        SQLSegment originalSql = new SQLSegment(oldSql);
        String dataRuleSql = DataRuleSqlBuilder.buildDataRuleSql(sqlExp, originalSql, currLevel.getLevel());
        logger.debug(">>>>>>>>>>>>>>>>>原sql:{}", oldSql);
        logger.debug(">>>>>>>>>>>>>>>>>带有数据权限的sql:{}", dataRuleSql);
        ReflectUtil.setFieldValue(boundSql, "sql", dataRuleSql);
//        SqlSource sqlSource = buildSqlSource(configuration, dataRuleSql, parameterObject.getClass(), addParams);
//        metaStatementHandler.setValue("delegate.boundSql.sql", sqlSource.getBoundSql(parameterObject).getSql());
//        metaStatementHandler.setValue("delegate.boundSql.parameterMappings", sqlSource.getBoundSql(parameterObject).getParameterMappings());
        return invocation.proceed();
    }


    private SqlSource buildSqlSource(Configuration configuration, String originalSql, Class<?> parameterType, Map<String, Object> addParams) {
        SqlSourceBuilder builder = new SqlSourceBuilder(configuration);
        return builder.parse(originalSql, parameterType, addParams);
    }

    /**
     * 获取到当前查询规则的sql拼接字符串
     * @param sqlExp
     * @param level
     * @return
     */
    private String getCurrentDataRuleExp(String sqlExp, DataRuleLevel.Level level) {
        Gson gson = new Gson();
        Map<String, String> sqlExpMap = gson.fromJson(sqlExp, new TypeToken<Map<String, String>>(){}.getType());
        return sqlExpMap.get(level.name());
    }


    /**
     * 获取当前用户的针对当前页面的权限设置
     * @param menuId
     * @return
     */
    private DataRuleLevel getCurrentDataRuleLevel(String menuId) {
        Map<String, DataRuleLevel> dataRuleLevelMap = UserUtil.getCurrDataRules();
        if(dataRuleLevelMap == null){
            return null;
        }
        return dataRuleLevelMap.get(menuId);
    }

    /**
     * 获取当前用户对应此查询的数据权限设置
     * @param sqlId
     * @return
     */
    private DataRuleDefine getCurrentDataRule(String sqlId) {
        Map<String, DataRuleDefine> allDataRule = DataRuleUtil.getAllDataRuleDefineBySqlKey();
        return allDataRule.get(sqlId);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
