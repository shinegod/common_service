package com.fx.cache.redis;

import com.fx.cache.Cache;
import com.fx.cache.CacheException;
import com.fx.cache.CacheManager;
import com.fx.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Redis的CacheManager实现
 * Created by bei2love@gmail.com on 15/9/8.
 */
public class RedisCacheManager implements CacheManager{

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    private static final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();

    private Redis redisManager;

    public static String keyPrefix = "redis_cache:";



    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        keyPrefix = Config.getConfig(Config.CACHE_SYSTEM_PREFIX, keyPrefix);
        keyPrefix = HackDevelopEnv(keyPrefix);
        name = keyPrefix + name;
        logger.debug("获取名称为: {} 的RedisCache实例", name);
        Cache<K, V> cache = caches.get(keyPrefix);

        if (cache == null) {
            redisManager.init();
            cache = new RedisCache<K, V>(redisManager, keyPrefix);
            caches.put(name, cache);
        }

        return cache;
    }

    private String HackDevelopEnv(String keyPrefix) {
        if(StringUtils.indexOf(keyPrefix, "dev") != -1){
            keyPrefix = keyPrefix + MacUtils.getMac() + Constants.CACHE_KEY.DEFAULT_KEY_SPLIT;
        }
        return keyPrefix;
    }

    public static <K, V> void removeCache(Cache<K, V> cache) {
        RedisCacheManager cacheManager = SpringUtils.getBean(RedisCacheManager.class);
        caches.remove(cache.getCacheName());

    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public Redis getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(Redis redisManager) {
        this.redisManager = redisManager;
    }


}
