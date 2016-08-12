package com.fx.util;

/**
 * 项目中的常量定义
 * 按照大的功能分块，可以定义自己的静态类
 *
 * Created by bei2love@gmail.com on 15/8/26.
 */
public class Constants {

    // Default diagram image extension, when name cannot be deducted from
    // resource name
    public static final String DEFAULT_DIAGRAM_IMAGE_EXTENSION = "png";

    /**
     * 数据权限条件在sqlMap中的key
     */
    public static final String SQLMAP_DATASCOPE_KEY = "dsf";
    /**
     * 机构类型公司
     */
    public static final String ORG_TYPE_COMPANY = "Company";

    /**
     * 超级管理员的id
     */
    public static final int DEFAULT_AGENT_ID = 1;

    /**
     * 更新用户心跳时间的时间间隔
     */
    public static final java.lang.String USER_HEARTBEAT = "system.user.heartbeat";

    /**
     * crm后台系统标识
     */
    public static final String SYSTEM_CRM = "crm";

    /**
     * crm后台系统标识
     */
    public static final String SYSTEM_TRADER = "trader";



    public static class ORG{

        public static final String COMPANY = "Company";

        public static final String DEPT = "Department";

        public static final String WORKGROUP = "WorkGroup";
    }

    /**
     * 字典表code的常量定义
     */
    public static class DICT{

        /**
         * 字典表的根的code
         */
        public static final String ROOT_CODE = "CRM_DICT_ROOT_DEFINE";

        /**
         * 字典表缓存key的前缀
         */
        public static final String DICT_CACHE_KEY_PREFIX = "DICT_CACHE";

    }

    /**
     * 角色相关常量
     */
    public static class ROLE{

        // 数据范围（1:所有数据；2:所在公司及以下数据；3:所在公司数据；4:所在部门及以下数据；5:所在部门数据；8:仅本人数据；9:按明细设置）
        public static final String DATA_SCOPE_ALL = "all";
        public static final String DATA_SCOPE_COMPANY_AND_CHILD = "cc";
        public static final String DATA_SCOPE_COMPANY = "c";
        public static final String DATA_SCOPE_OFFICE_AND_CHILD = "dc";
        public static final String DATA_SCOPE_OFFICE = "dd";
        public static final String DATA_SCOPE_SELF = "sd";
        public static final String DATA_SCOPE_CUSTOM = "d";

    }
    /**
     * 工作流相关常量定义
     */
    public static class BPM{

        /**
         * 业务实体名
         */
        public static final String VAR_ENTITY = "entity";
        /**
         * 流程实体类型变量
         */
        public static final String VAR_ENTITY_TYPE = "entityType";
    }

    /**
     * 缓存key定义
     */
    public static class CACHE_KEY{

        /**
         * 缓存key的默认分隔符号
         */
        public static final String DEFAULT_KEY_SPLIT = "_";

        public static final String MT4_CACHE = "mt4_cache";
        /**
         * mt4组缓存的key
         */
        public static final String KEY_MT4GROUPID = "mt4groupid";
        //包含IB
        public static final String KEY_MT4GROUPANDIB = "mt4GroupAndIb";

        /**
         * 某一数据源的mt4货币对缓存的key
         */
        public static final String KEY_DATASOURCE_MT4CURRENCY = "datasource_mt4currency";

        /**
         * 日志信息对应缓存key
         */
        public static final String LOGGER_OPERATION_REFERENCE = "logger_operation_reference";

        /**
         * 所有部门的缓存key
         */
        public static final String ALL_ORG_CACHE = "all_org_cache";
        /**
         * 机构类型的缓存key
         */
        public static final String DICT_ORG_TYPE_KEY = "ORG_TYPE";
        /**
         * 数据权限缓存key
         */
        public static final String DICT_DATA_SCOPE_KEY = "DATA_SCOPE";
        /**
         * 在线用户的缓存对象key
         */
        public static final String ONLINE_USER_CACHE_NAME = "USERCACHE";
        /**
         * 在线用户缓存前缀
         */
        public static final String ONLINE_USER_KEY = "ONLINE";
        /**
         * 国家缓存
          */
        public static final String COUNTRY_KEY = "COUNTRY";

        /**
         * 角色对应的权限缓存KEY
         */
        public static final String CACHE_ROLE_PERSSION = "cahce_role_perssion";
    }

    public static final String QUERYCONFIG = "QUERYCONFIG";
}
