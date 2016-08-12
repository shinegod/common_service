package com.fx.cache;

/**
 * CacheManager接口定义
 * Created by bei2love@gmail.com on 15/9/8.
 */
public interface CacheManager extends org.apache.shiro.cache.CacheManager{

    public <K, V> Cache<K, V> getCache(String name) throws CacheException;
}
