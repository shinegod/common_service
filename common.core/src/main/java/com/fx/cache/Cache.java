package com.fx.cache;

/**
 * 定义缓存接口
 * Created by bei2love@gmail.com on 15/9/8.
 */
public interface Cache<K, V>  extends org.apache.shiro.cache.Cache<K, V> {

    public V put(K key, V value, int expire) throws CacheException;

    public String getCacheName();
}
