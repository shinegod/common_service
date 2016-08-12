package com.fx.util;

import com.fx.MT4.util.DLLAgent;
import com.fx.MT4.util.MT4GroupUtil;
import com.fx.cache.Cache;
import com.fx.cache.CacheManager;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.multidatasource.DataSourceContextHolder;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 设置系统中需要使用的缓存
 */
public class MT4CacheUtil {


    private static CacheManager cacheManager = SpringUtils.getBean(CacheManager.class);

    public static final Logger logger = LoggerFactory.getLogger(MT4CacheUtil.class);


    private static Cache<String, Object> cache;
    //mt4 group id 缓存 存活时间
    private static final int MT4GROUPID_CACHE_EXPIRE = 0;
    //所有数据源mt4 货币对  缓存 存活时间
    private static final int ALL_MT4CURRENCY_CACHE_EXPIRE = 0;
    //某一数据源mt4 货币对  缓存 存活时间
    private static final int DATASOURCE_MT4CURRENCY_CACHE_EXPIRE = 0;


    static {
        cache = cacheManager.getCache(Constants.CACHE_KEY.MT4_CACHE);
    }

    /**
     * 获取mt4groupid缓存map
     */
    public static Map<String, String> getMt4GroupIdsCacheMap() {

        Object obj = cache.get(Constants.CACHE_KEY.KEY_MT4GROUPID);
        if (obj == null) {
            obj = refreshMt4GroupIdsCacheMap();
        }

        return (Map<String, String>) obj;
    }
    //包含ib
    public static Map<String, String> getMt4GroupAndIBCacheMap() {

        Object obj = cache.get(Constants.CACHE_KEY.KEY_MT4GROUPANDIB);
        if (obj == null) {
            obj = refreshMt4GroupAndIbCacheMap();
        }

        return (Map<String, String>) obj;
    }

    /**
     * 存入MT4 GroupId 缓存中
     */
    public static Map<String, String> refreshMt4GroupIdsCacheMap() {
        Map<String, String> mt4GroupIdsCacheMap = new HashMap<>();
        Map<Integer, DataSourceBean> dataSourceBeanMap = DataSourceContextHolder.getDATASOURCEBEAN_MAP();
        for (Entry<Integer, DataSourceBean> entry : dataSourceBeanMap.entrySet()) {
            Set<String> mt4groupSet = MT4GroupUtil.getGroupList(false,
                    entry.getValue());
            mt4GroupIdsCacheMap.put(entry.getKey().toString(), mt4groupSet.toString());
        }
        cache.put(Constants.CACHE_KEY.KEY_MT4GROUPID, mt4GroupIdsCacheMap);
        return mt4GroupIdsCacheMap;
    }

    /**
     * 获取mt4groupid缓存map  包含ib组
     */
        public static String getMt4GroupAndIbCacheMap(Integer dataSourceId) {
            Map<String, String> mt4GroupIdsCacheMap = getMt4GroupAndIBCacheMap();
            if (mt4GroupIdsCacheMap == null) {
                mt4GroupIdsCacheMap = refreshMt4GroupAndIbCacheMap();
                if (mt4GroupIdsCacheMap == null) {
                    return null;
                }
            }
            return mt4GroupIdsCacheMap.get(String.valueOf(dataSourceId));
        }

    /**
     * 存入MT4 GroupId 缓存中 包含ib组
     */
    public static Map<String, String> refreshMt4GroupAndIbCacheMap() {
        Map<String, String> mt4GroupIdsCacheMap = new HashMap<>();
        Map<Integer, DataSourceBean> dataSourceBeanMap = DataSourceContextHolder.getDATASOURCEBEAN_MAP();
        for (Entry<Integer, DataSourceBean> entry : dataSourceBeanMap.entrySet()) {
            Set<String> mt4groupSet = MT4GroupUtil.getGroupListAndIb(false,
                    entry.getValue());
            mt4GroupIdsCacheMap.put(entry.getKey().toString(), mt4groupSet.toString());
        }
        cache.put(Constants.CACHE_KEY.KEY_MT4GROUPANDIB, mt4GroupIdsCacheMap);
        return mt4GroupIdsCacheMap;
    }

    /**
     * 根据数据源id取得对应的所有mt4 group
     *
     * @param dataSourceId
     * @return
     */
    public static String getMt4GroupIdByDataSourceId(Integer dataSourceId) {
        Map<String, String> mt4GroupIdsCacheMap = getMt4GroupIdsCacheMap();
        if (mt4GroupIdsCacheMap == null) {
            mt4GroupIdsCacheMap = refreshMt4GroupIdsCacheMap();
            if (mt4GroupIdsCacheMap == null) {
                return null;
            }
        }
        return mt4GroupIdsCacheMap.get(dataSourceId);
    }

    /**
     * 获得某一数据源所有group组对应的mt4货币对缓存map
     *
     * @return
     */
    public static Map<String, String> getCurrencyDataSourceCacheMap(Integer dataSourceId, String group) {
        Object obj = cache.get(Constants.CACHE_KEY.KEY_DATASOURCE_MT4CURRENCY);
        if (obj == null) {
            cache.put(Constants.CACHE_KEY.KEY_DATASOURCE_MT4CURRENCY, new HashMap<>());
            obj = refreshCurrencyDataSourceCacheMap(dataSourceId, group);
        }
        return (Map<String, String>) obj;
    }

    /**
     * 设置某一数据源 mt4货币对缓存
     */
    public static Map<String, String> refreshCurrencyDataSourceCacheMap(Integer dataSourceId, String group) {
        Object obj = cache.get(Constants.CACHE_KEY.KEY_DATASOURCE_MT4CURRENCY);
        if (obj == null) {
            cache.put(Constants.CACHE_KEY.KEY_DATASOURCE_MT4CURRENCY, new HashMap<>());
            obj = new HashMap<>();
        }
        Map<String, String> currencyDataSourceCacheMap = (Map<String, String>) obj;
        Map<Integer, DataSourceBean> dataSourceBeanMap = DataSourceContextHolder.getDATASOURCEBEAN_MAP();
        String currency = DLLAgent.getDepositCurrency(group.trim(),
                dataSourceBeanMap.get(dataSourceId).getMt4User(),
                dataSourceBeanMap.get(dataSourceId).getMt4Pass(),
                dataSourceBeanMap.get(dataSourceId).getMt4LiveIp());
        String key = group.trim() + "_" + dataSourceId;
        currencyDataSourceCacheMap.put(key, currency);
        cache.put(Constants.CACHE_KEY.KEY_DATASOURCE_MT4CURRENCY, currencyDataSourceCacheMap);
        return currencyDataSourceCacheMap;
    }

    /**
     * 在某一数据源的货币对缓存map下根据数据源id与mt4组查找货币对
     *
     * @param dataSourceId
     * @param group
     * @return
     */
    public static String getCurrencyThroughDataSourceCurrencyCacheMap(Integer dataSourceId, String group) {
        Map<String, String> currencyDataSourceCacheMap =
        currencyDataSourceCacheMap = getCurrencyDataSourceCacheMap(dataSourceId, group);
        if (currencyDataSourceCacheMap == null) {
            return null;
        }
        return currencyDataSourceCacheMap.get(group + "_" + dataSourceId);
    }

    private static final String MT4_GROUP_CACHE = "m4t_grp_cache";

    public static final String DS_GROUP_KEY = "grp";

    public static Set<String> getGroupCache(boolean isDemoServer, DataSourceBean dataSourceBean) {
        Cache groupCache = cacheManager.getCache(MT4_GROUP_CACHE);
        String currCacheKey = DS_GROUP_KEY + Constants.CACHE_KEY.DEFAULT_KEY_SPLIT + isDemoServer + Constants.CACHE_KEY.DEFAULT_KEY_SPLIT + dataSourceBean.getId();
        Object currObj = groupCache.get(currCacheKey);
        Object[] arrays = (Object[]) currObj;
        if (currObj == null || arrays== null || arrays.length == 0) {
            groupCache.remove(currCacheKey);
            Set<String> groupSet = MT4GroupUtil.getGroupListByDll(isDemoServer, dataSourceBean);
            groupCache.put(currCacheKey, groupSet.toArray(), 3600);
            return groupSet;
        }

        Set<String> groupset = Sets.newHashSet();
        for(int i=0; i<arrays.length; i++){
            groupset.add(arrays[i].toString());
        }
        return groupset;
    }
}
