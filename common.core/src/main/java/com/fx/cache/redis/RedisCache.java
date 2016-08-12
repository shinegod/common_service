package com.fx.cache.redis;

import com.fx.cache.Cache;
import com.fx.util.SerializeUtils;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

/**
 * Redis的Cache实现
 * Created by bei2love@gmail.com on 15/9/8.
 */
public class RedisCache<K, V> implements Cache<K, V> {


    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private Redis cache;

    private String keyPrefix;

    public RedisCache(Redis cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    public RedisCache(Redis cache, String keyPrefix) {
        this(cache);
        this.keyPrefix = keyPrefix;
    }

    /**
     * 获取byte[]型的key
     * @param key
     * @return
     */
    public byte[] getByteKey(K key) {
        String keyStr = keyPrefix + ":";
        if (key instanceof String) {
            String preKey = keyStr + key;
            return preKey.getBytes();
        }else {
            return byteMerger(keyStr.getBytes(), SerializeUtils.serialize(key));
        }
    }

    public static byte[] byteMerger(byte[] byteArray1, byte[] byteArray2){
        byte[] resultArray = new byte[byteArray1.length+byteArray2.length];
        System.arraycopy(byteArray1, 0, resultArray, 0, byteArray1.length);
        System.arraycopy(byteArray2, 0, resultArray, byteArray1.length, byteArray2.length);
        return resultArray;
    }

    @Override
    public V get(K key) throws CacheException {
        if (key == null) {
            return null;
        }
        try {
            byte[] rawValue = cache.get(getByteKey(key));
            @SuppressWarnings("unchecked")
            V value = (V) SerializeUtils.deserialize(rawValue);
            logger.debug("从redis服务器获取缓存:key:{}， value:{}", key, value);
            return value;
        } catch (Throwable throwable) {
            logger.debug("从redis服务器获取缓存:key:{} ,异常 ", key);
            throw new CacheException(throwable);
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        if (key == null) {
            throw new CacheException("Cache key not null");
        }
        try {
            cache.set(getByteKey(key), SerializeUtils.serialize(value));
            logger.debug("向redis设置缓存:key:{}， value:{}", key, value);
            return value;
        } catch (Throwable throwable) {
            throw new CacheException(throwable);
        }
    }

    @Override
    public V put(K key, V value, int expire) throws com.fx.cache.CacheException {
        if (key == null) {
            throw new CacheException("Cache key not null");
        }
        try {
            cache.set(getByteKey(key), SerializeUtils.serialize(value), expire);
            logger.debug("向redis设置缓存:key:{}， value:{}", key, value);
            return value;
        } catch (Throwable throwable) {
            throw new CacheException(throwable);
        }
    }

    @Override
    public V remove(K key) throws CacheException {
        if (key == null) {
            throw new CacheException("Cache key not null");
        }
        try {
            V previous = get(key);
            cache.remove(getByteKey(key));
            logger.debug("从redis中删除 key:{}， value:{}", key, previous);
            return previous;
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public void clear() throws CacheException {
        logger.debug("多服务共用Codis，赞不支持此方法。");
//        try {
//            cache.clear(this.keyPrefix);
//            RedisCacheManager.removeCache(this);
//        } catch (Throwable t) {
            throw new CacheException("多服务共用Codis，赞不支持此方法。");
//        }
    }

    @Override
    public int size() {
        logger.debug("当前采用codis实现，不支持此方法。");
        throw new CacheException("当前采用codis实现，不支持此方法。");
//        Long size = new Long(cache.dbSize());
//        return size.intValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<K> keys() {
        logger.debug("当前采用codis实现，不支持此方法。");
        throw new CacheException("当前采用codis实现，不支持此方法。");
//        try {
//            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
//            if (CollectionUtils.isEmpty(keys)) {
//                return Collections.emptySet();
//            }else{
//                Set<K> newKeys = new HashSet<K>();
//                for(byte[] key:keys){
//                    newKeys.add((K)key);
//                }
//                return newKeys;
//            }
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
    }

    @Override
    public Collection<V> values() {
        logger.debug("当前采用codis实现，不支持此方法。");
        throw new CacheException("当前采用codis实现，不支持此方法。");
//        try {
//            Set<byte[]> keys = cache.keys(this.keyPrefix + "*");
//            if (!CollectionUtils.isEmpty(keys)) {
//                List<V> values = new ArrayList<V>(keys.size());
//                for (byte[] key : keys) {
//                    @SuppressWarnings("unchecked")
//                    V value = get((K) key);
//                    if (value != null) {
//                        values.add(value);
//                    }
//                }
//                return Collections.unmodifiableList(values);
//            } else {
//                return Collections.emptyList();
//            }
//        } catch (Throwable t) {
//            throw new CacheException(t);
//        }
    }

    @Override
    public String getCacheName() {
        return keyPrefix;
    }
}
