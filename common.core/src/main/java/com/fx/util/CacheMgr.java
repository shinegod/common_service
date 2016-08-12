package com.fx.util;

import com.fx.MT4.util.MT4GroupUtil;
import com.fx.admin.model.Admin;
import com.fx.admin.model.Role;
import com.fx.admin.service.IAdminService;
import com.fx.admin.service.IRoleService;
import com.fx.cache.Cache;
import com.fx.cache.CacheManager;
import com.fx.common.model.City;
import com.fx.common.model.Country;
import com.fx.common.model.Dictionary;
import com.fx.common.model.Province;
import com.fx.common.service.ICityService;
import com.fx.common.service.ICountryService;
import com.fx.common.service.IDictionaryService;
import com.fx.common.service.IProvinceService;
import com.fx.crm.sys.datarule.model.DataRuleDefine;
import com.fx.crm.sys.datarule.service.IDataRuleDefineService;
import com.fx.crm.sys.log.model.OperationReference;
import com.fx.crm.sys.log.service.IOperationReferenceService;
import com.fx.crm.sys.org.model.Organization;
import com.fx.crm.sys.org.service.IOrganizationService;
import com.fx.crm.sys.permission.model.Permission;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.payment.model.UserMT4Account;
import com.fx.payment.service.IUserMT4AccountService;
import com.fx.shiro.UserCacheEntity;
import com.fx.user.model.User;
import com.fx.user.model.UserRegister;
import com.fx.user.service.IUserRegisterService;
import com.fx.user.service.IUserService;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存操作工具类
 * Created by bei2love@gmail.com on 15/4/30.
 */
public class CacheMgr {

    public static final String DEFAULT_CACHE_KEY = "commonCache";

    private static final String CONST_ONLINE_USER_KEY = "online_user";

    private static final String CACHE_KEY_MT4ACCOUNT = "cache_key_mt4account";

    private static final Object CACHE_KEY_USER = "cache_key_user";

    private static final Object CACHE_KEY_ADMIN = "cache_key_admin";

    private static final Object CACHE_KEY_COMM_RULE = "cache_key_comm_rule";

    private static final Object CACHE_KEY_USER_REGISTER = "cache_key_user_register";

    private static Map<String, String> onlineUserMap = new ConcurrentHashMap<String, String>();

    public static final String ALL_DATARULEDEFINE_KEY = "all_dataruledefine_key";

    public static final String MT4GROUP_KEY = "mt4group_key";

    public static final String ADMINUSERID_KEY = "ADMINUSERID_KEY";

    private static CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);

    private static final Map<String, UserCacheEntity<?>> ONLINE_USER_MAP = Maps.newConcurrentMap();

    /**
     * 获取指定名称的缓存对象
     *
     * @param name
     * @param <T>
     * @return
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(String name) throws BeansException {
        return (T) cacheManager.getCache(DEFAULT_CACHE_KEY).get(name);
    }


    /**
     * 向系统默认缓存中添加对象
     *
     * @param key
     * @param val
     */
    public static void put(Object key, Object val) {
        cacheManager.getCache(DEFAULT_CACHE_KEY).put(key, val);
    }


    /**
     * 移除默认缓存中缓存的对象
     *
     * @param key
     */
    public static void remove(Object key) {
        cacheManager.getCache(DEFAULT_CACHE_KEY).remove(key);
    }

    /**
     * 添加在线用户
     *
     * @param ov
     */
    public static void addUserOnline(UserCacheEntity<?> ov) {
        //TODO: 修改在线用户缓存
        onlineUserMap = getOnlineUser();
        String key = getOnlineUserKey(ov.getUserId());
        onlineUserMap.put("" + ov.getUserId(), key);
        getOnlineUserCache().put(key, ov);
//        putOnlineUser();
    }

    /**
     * 获取在线用户缓存key前缀
     * @param userId
     * @return
     */
    public static String getOnlineUserKey(Integer userId) {
        return Constants.CACHE_KEY.ONLINE_USER_KEY + Constants.CACHE_KEY.DEFAULT_KEY_SPLIT + userId ;
    }

    /**
     * 获取所有在线用户
     *
     * @return
     */
    public static Map<String, String> getOnlineUser() {
//        Object obj = getDefaultCache().get(CONST_ONLINE_USER_KEY);
//        if (obj != null) {
//            onlineUserMap = (Map<String, String>) obj;
//        } else {
//            onlineUserMap = Maps.newConcurrentMap();
//            putOnlineUser();
//        }
        return onlineUserMap;
    }


    private static void putOnlineUser() {
        getDefaultCache().put(CONST_ONLINE_USER_KEY, onlineUserMap);
    }

    private static <K, V> Cache<K, V> getDefaultCache() {
        return cacheManager.getCache(DEFAULT_CACHE_KEY);
    }

    /**
     * 获取指定key的Cache
     *
     * @param cacheKey
     * @param <K>
     * @param <V>
     * @return
     */
    private static <K, V> Cache<K, V> getCache(String cacheKey) {
        return cacheManager.getCache(cacheKey);
    }

    /**
     * 获取指定用户id的在线用户
     *
     * @param key
     * @return
     */
    public static UserCacheEntity<?> getUserOnLine(String key) {
        onlineUserMap = getOnlineUser();
        if (onlineUserMap == null) {
            return null;
        }
        if(onlineUserMap.keySet().contains(key)){
            return (UserCacheEntity<?>) getOnlineUserCache().get(onlineUserMap.get(key));
        }
        return null;
    }

    /**
     * 通过在线用户缓存map的key获取
     * @param key
     * @return
     */
    public static UserCacheEntity getUserOnlineByKey(String key) {
        return (UserCacheEntity<?>) getOnlineUserCache().get(key);
    }

    public static Map<String, UserCacheEntity<?>> getOnlineUserCache() {

        return ONLINE_USER_MAP;
//        return getCache(Constants.CACHE_KEY.ONLINE_USER_CACHE_NAME);
    }

    /**
     * 删除在线用户
     * @param key 在线用户的Key，可以通过CacheMgr.getOnlineUser和用户id获取
     */
    public static void offlineUserByKey(String key) {
        onlineUserMap = getOnlineUser();
        for (Map.Entry<String, String> entry : onlineUserMap.entrySet()) {
            if(entry.getValue().equals("" + key)) {
                onlineUserMap.remove(entry.getKey());
                break;
            }
        }
        getOnlineUserCache().remove(key);
        putOnlineUser();
    }

    /**
     * 删除在线用户
     * @param userId 用户的userId
     */
    public static void offlineUser(String userId) {
        onlineUserMap = getOnlineUser();
        if (onlineUserMap.keySet().contains("" + userId)) {
//            getOnlineUserCache().remove(onlineUserMap.get("" + userId));
            onlineUserMap.remove("" + userId);
//            putOnlineUser();
        }
    }

    /**
     * 获取系统缓存的
     *
     * @return
     */
    public static List<DataRuleDefine> getAllDataDefine() {
        Object obj = getDefaultCache().get(ALL_DATARULEDEFINE_KEY);
        if (obj == null) {
            IDataRuleDefineService dataRuleDefineService = SpringUtils.getBean(IDataRuleDefineService.class);
            List<DataRuleDefine> allDataRuleDefine = dataRuleDefineService.findAll();
            CacheMgr.put(CacheMgr.ALL_DATARULEDEFINE_KEY, allDataRuleDefine);
            return allDataRuleDefine;
        }
        return (List<DataRuleDefine>) obj;
    }

    /**
     * MT4用户组
     */
    public static Set<String> getMT4Group(DataSourceBean dataSourceBean) {
        Object obj = getDefaultCache().get(MT4GROUP_KEY);
        Set<String> mt4groupSet = new HashSet<String>();
        if (obj == null) {
            try {
                mt4groupSet = MT4GroupUtil.getGroupList(false, dataSourceBean);
                CacheMgr.put(MT4GROUP_KEY, mt4groupSet);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return mt4groupSet;
            }

        }
        return (Set<String>) obj;
    }

    /**
     * 获取MT4账户的缓存（注意新增时需要刷新）
     *
     * @return
     */
    public static Map<String, UserMT4Account> getMT4AccountCache(int dataSourceId) {
        Object obj = getDefaultCache().get(CACHE_KEY_MT4ACCOUNT);
        if (obj == null) {
            obj = refreshMT4AccountCache(dataSourceId);
            getDefaultCache().put(CACHE_KEY_MT4ACCOUNT, obj);
        }
        return (Map<String, UserMT4Account>) obj;
    }

    /**
     * 刷新userMt4Account缓存数据
     * key为mt4account
     *
     * @return
     */
    public static Map<String, UserMT4Account> refreshMT4AccountCache(int dataSourceId) {
        IUserMT4AccountService mt4TradesService = SpringUtils.getBean(IUserMT4AccountService.class);
        List<UserMT4Account> userMT4Accounts = mt4TradesService.getUserMT4AccountByDataSourceId(dataSourceId);
        Map<String, UserMT4Account> map = new HashMap<>();
        for (UserMT4Account uma : userMT4Accounts) {
            map.put("" + uma.getMt4Account(), uma);
        }
        return map;
    }

    /**
     * 获取外部用户缓存
     *
     * @return
     */
    public static Map<String, User> getUserCache() {
        Object obj = getDefaultCache().get(CACHE_KEY_USER);
        if (obj == null) {
            obj = refreshUserCache();
        }
        return (Map<String, User>) obj;
    }


    /**
     * 刷新外部用户缓存
     *
     * @return
     */
    public static Map<String, User> refreshUserCache() {
        IUserService service = SpringUtils.getBean(IUserService.class);
        List<User> list = service.findAll();
        Map<String, User> map = new HashMap<>();
        for (User u : list) {
            map.put("" + u.getUserId(), u);
        }
        return map;
    }

    /**
     * 获取外部用户缓存
     *
     * @return
     */
    public static Map<String, UserRegister> getUserRegisterCache() {
        Object obj = getDefaultCache().get(CACHE_KEY_USER_REGISTER);
        if (obj == null) {
            obj = refreshUserRegisterCache();
        }
        return (Map<String, UserRegister>) obj;
    }


    /**
     * 刷新外部用户缓存
     *
     * @return
     */
    public static Map<String, UserRegister> refreshUserRegisterCache() {
        IUserRegisterService service = SpringUtils.getBean(IUserRegisterService.class);
        List<UserRegister> list = service.findAll();
        Map<String, UserRegister> map = new HashMap<>();
        for (UserRegister u : list) {
            map.put("" + u.getId(), u);
        }
        return map;
    }


    /**
     * 获取内部用户缓存
     *
     * @return
     */
    public static Map<String, Admin> getAdminCache() {
        Object obj = getDefaultCache().get(CACHE_KEY_ADMIN);
        if (obj == null) {
            obj = refreshAdminCache();
        }
        return (Map<String, Admin>) obj;
    }


    /**
     * 刷新内部用户缓存
     *
     * @return
     */
    public static Map<String, Admin> refreshAdminCache() {
        IAdminService service = SpringUtils.getBean(IAdminService.class);
        List<Admin> list = service.findAll();
        Map<String, Admin> map = new HashMap<>();
        for (Admin u : list) {
            map.put("" + u.getId(), u);
        }
        return map;
    }

    /**
     * 获取佣金设置缓存
     * @return
     */
//    public static Map<String, List<CommissionRules>> getCommissionRuleCache() {
//        Object obj = getDefaultCache().get(CACHE_KEY_COMM_RULE);
//        if(obj == null){
//            obj = refreshCommissionRuleCache();
//        }
//        return (Map<String, List<CommissionRules>>)obj;
//    }

    /**
     * 刷新佣金设置缓存
     *
     * @return
     */
//    public static Map<String, List<CommissionRules>> refreshCommissionRuleCache() {
//        ICommissionRulesService service = SpringUtils.getBean(ICommissionRulesService.class);
//        List<CommissionRules> commLists = service.findAllCommissionRulesDetail();
//        Map<String, List<CommissionRules>> roleRuleMap = new HashMap<>();
//        for(CommissionRules rule : commLists){
//            if(roleRuleMap.containsKey(rule.getUserId())){
//                roleRuleMap.get(rule.getUserId()).add(rule);
//            }else {
//                List<CommissionRules> rulesList = new ArrayList<>();
//                rulesList.add(rule);
//                roleRuleMap.put(rule.getUserId(), rulesList);
//            }
//        }
//        return roleRuleMap;
//    }
    //admin 和 userid对应的map
    public static Map<String, Admin> getAdminUserIdMap() {
        Object obj = getDefaultCache().get(ADMINUSERID_KEY);
        if (obj == null) {
            obj = userIdAdminCache();
            getDefaultCache().put(ADMINUSERID_KEY,obj);
        }
        return (Map<String, Admin>) obj;
    }

    public static Map<String, Admin> userIdAdminCache() {
        IAdminService service = SpringUtils.getBean(IAdminService.class);
        List<Admin> list = service.findAll();
        Map<String, Admin> map = new HashMap<>();
        for (Admin u : list) {
            map.put("" + u.getUserId(), u);
        }
        return map;
    }

    //role 和 id对应的map
    public static Map<String, Admin> getRoleMap() {
        Object obj = getDefaultCache().get(ADMINUSERID_KEY);
        if (obj == null) {
            obj = RoleCache();
        }
        return (Map<String, Admin>) obj;
    }

    public static Map<String, Role> RoleCache() {
        IRoleService service = SpringUtils.getBean(IRoleService.class);
        List<Role> list = service.findAll();
        Map<String, Role> map = new HashMap<>();
        for (Role u : list) {
            map.put("" + u.getId(), u);
        }
        return map;
    }

    /**
     * 清空当前系统缓存
     */
    @Deprecated
    public static void cleanCache() {

    }

    public static boolean refreshDictionaryCache() {
        IDictionaryService dictService = SpringUtils.getBean(IDictionaryService.class);
        List<com.fx.common.model.Dictionary> dictUnderRoot = dictService.queryByParentCode(Constants.DICT.ROOT_CODE);

        for (com.fx.common.model.Dictionary dict : dictUnderRoot) {
            Map<String, com.fx.common.model.Dictionary> dictMap = transferList(dictService.queryByParentCode(dict.getDataCode()));
            CacheMgr.putDictCache(dict.getDataCode(), dictMap);
        }
        return true;
    }

    /**
     * 设置DataCode下所有字典数据的缓存
     *
     * @param dataCode
     * @param dictMap
     */
    public static void putDictCache(String dataCode, Map<String, Dictionary> dictMap) {
        Cache cache = getDefaultCache();
        cache.put(getDictKey(dataCode), dictMap);
    }

    public static Map<String, Dictionary> getDictCache(String dataCode) {
        Object obj = getDefaultCache().get(getDictKey(dataCode));
        if (obj != null) {
            return (Map<String, Dictionary>) obj;
        }
        return null;
    }


    private static String getDictKey(String key) {
        return Constants.DICT.DICT_CACHE_KEY_PREFIX + key;
    }

    private static Map<String, Dictionary> transferList(List<Dictionary> dictionaries) {
        Map<String, Dictionary> dictMap = Maps.newConcurrentMap();
        for (Dictionary dict : dictionaries) {
            dictMap.put(dict.getDataCode(), dict);
        }
        return dictMap;
    }

    public static Map<String, OperationReference> refreshOperationReference() {
        Map<String, OperationReference> orMap = Maps.newHashMap();
        IOperationReferenceService operationReferenceService = SpringUtils.getBean(IOperationReferenceService.class);
        List<OperationReference> list = operationReferenceService.findAll();
        for (OperationReference or : list) {
            orMap.put(or.getUri(), or);
        }
        getDefaultCache().put(Constants.CACHE_KEY.LOGGER_OPERATION_REFERENCE, orMap);
        return orMap;
    }

    /**
     *
     * @return
     */
    public static Map<String, OperationReference> getOperationReference(){
        Object obj = getDefaultCache().get(Constants.CACHE_KEY.LOGGER_OPERATION_REFERENCE);
        if (obj == null) {
            obj = refreshOperationReference();
        }
        return (Map<String, OperationReference>) obj;
    }

    /**
     * 获取所有部门的缓存
     * @return
     */
    public static Map<Integer, Organization> getAllOrgCache() {
        Object obj = getDefaultCache().get(Constants.CACHE_KEY.ALL_ORG_CACHE);
        if (obj == null) {
            obj = refreshAllOrgCache();
        }
        return (Map<Integer, Organization>) obj;
    }

    /**
     * 刷新部门缓存
     * @return
     */
    public static Map<Integer, Organization> refreshAllOrgCache() {
        IOrganizationService orgService = SpringUtils.getBean(IOrganizationService.class);
        List<Organization> orgList = orgService.findAll();
        Map<Integer, Organization> orgMap = Maps.newHashMap();

        for (Organization org : orgList) {
            orgMap.put(org.getId(), org);
        }
        return orgMap;
    }

    /**
     * 验证用户是否在线
     * @param userId
     * @return
     */
    public static boolean isUserOnline(String userId) {
        Map<String, String> userOnlineMap = getOnlineUser();
        return userOnlineMap.keySet().contains(userId);
    }

    /**
     * 更新在线用户的缓存
     * @param user
     */
    public static void refreshOnlineUserCache(UserCacheEntity<?> user) {
        getOnlineUserCache().put(getOnlineUserKey(user.getUserId()), user);
    }

    public static Map<Integer, Country> getCountryCache() {
        Object obj = getDefaultCache().get(Constants.CACHE_KEY.COUNTRY_KEY);
        if (obj == null) {
            obj = refreshCountryCache();
        }
        return (Map<Integer, Country>) obj;
    }

    public static Map<Integer, Country> refreshCountryCache() {
        ICountryService countryService = SpringUtils.getBean(ICountryService.class);
        List<Country> countryList = countryService.findAll();
        Map<Integer, Country> countryMap = Maps.newHashMap();
        for (Country country : countryList) {
            country.setProvinceList(getProvinceByCountryId(country.getId()));
            countryMap.put(country.getId(), country);
        }
        getDefaultCache().put(Constants.CACHE_KEY.COUNTRY_KEY, countryMap);
        return countryMap;
    }

    public static List<Province> getProvinceByCountryId(Integer id) {
        IProvinceService provinceService = SpringUtils.getBean(IProvinceService.class);
        List<Province> provinceList = provinceService.findByCountryId(id);
        for (Province province : provinceList) {
            province.setCityList(getCityByProvinceId(province.getProvinceid()));
        }
        return provinceList;
    }

    public static List<City> getCityByProvinceId(Integer provinceid) {
        ICityService cityService = SpringUtils.getBean(ICityService.class);
        List<City> cityList = cityService.findByPorvinceId(provinceid);
        return cityList;
    }

    public static final String ROLE_CACHE_KEY = "ROLE_C_KEY_TREE";

    public static final String TREE_CACHE_NAME = "TREE_C_CACHE";

    public  static Map<Integer, String> getRoleCache() {
        IRoleService roleService = SpringUtils.getBean(IRoleService.class);
        Object cacheObj = cacheManager.getCache(TREE_CACHE_NAME).get(ROLE_CACHE_KEY);
        if (cacheObj == null) {
            List<Role> roleList = roleService.findAll();
            Map<Integer, String> roleMap = Maps.newHashMap();
            for (Role role : roleList) {
                if (role.getIsDel() == 0) {
                    roleMap.put(role.getId(), role.getName());
                }
            }
            cacheManager.getCache(TREE_CACHE_NAME).put(ROLE_CACHE_KEY, roleMap, 36000);
            cacheObj = roleMap;
        }
        return (Map<Integer, String>) cacheObj;
    }

    public static void removeRoleCache() {
        cacheManager.getCache(TREE_CACHE_NAME).remove(ROLE_CACHE_KEY);
    }

    /**
     * 角色对应的权限缓存
     * @param roleId
     * @return
     */
    public static List<Permission> getRolePerssionCache(Integer roleId){
        Object cacheObj = cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).get(roleId);
        if(cacheObj == null ) {
            IRoleService roleService = SpringUtils.getBean(IRoleService.class);
            List<Permission> permissionList = roleService.getPermissionByRoleId(roleId);
            cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).put(roleId,permissionList,36000);
            cacheObj = permissionList;
        }
        return (List<Permission>)cacheObj;
    }

    /**
     * 刷新角色权限
     * @param roleId
     * @return
     */
    public static List<Permission> refreshRolePerssionCache(Integer roleId){
        cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).remove(roleId);
        IRoleService roleService = SpringUtils.getBean(IRoleService.class);
        List<Permission> permissionList = roleService.getPermissionByRoleId(roleId);
        cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).put(roleId,permissionList,36000);
        return (List<Permission>)cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).get(roleId);
    }

    /**
     * 移除角色权限
     * @param roleId
     */
    public static void removeRolePerssionCache(Integer roleId) {
        cacheManager.getCache(Constants.CACHE_KEY.CACHE_ROLE_PERSSION).remove(roleId);
    }
}
