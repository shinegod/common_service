package com.fx.cache.redis;

import com.fx.cache.CacheException;
import com.fx.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.Set;

/**
 * Jedis连接Redis的实现
 * Created by bei2love@gmail.com on 15/9/8.
 */
public class RedisManager implements Redis{

    private String host ;

    private int port ;

    // 0 - never expire
    private int expire = 0 ;

    // timeout for jedis try to connect to redis server, not expire time!
    // In milliseconds
    private int timeout = 0 ;

    private String password ;

    private int maxIdel;

    private int maxTotal;

    private int maxWaitMillis;

    private static JedisPool jedisPool = null;

    private JedisPoolConfig jedisPoolConfig;

    private static final String KEY_WILDCARD_CHARACTER = "*";

    public RedisManager(){

    }

    /**
     * 初始化Jedis连接池
     */
    public void init() {
        initPoolConfig();
        if (jedisPool == null) {
            if (StringUtils.isBlank(host)) {
                throw new CacheException("Redis config error, host must be set.");
            }
            if (0 == port) {
                throw new CacheException("Redis config error, prot must be set.");
            }
            if (StringUtils.isBlank(password) && 0 == timeout) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port);
                return;
            }
            if (StringUtils.isNotBlank(password) && 0 == timeout) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout);
                return;
            }
            if (StringUtils.isNotBlank(password) && 0 != timeout) {
                jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
            }
            jedisPool = new JedisPool(host, port);
        }

    }

    private void initPoolConfig() {
        if (jedisPoolConfig == null) {
            jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdel);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        }
    }


    /**
     * get value from redis
     * @param key
     * @return
     */
    public byte[] get(byte[] key){
        byte[] value = null;
        Jedis jedis = jedisPool.getResource();
        try{
            value = jedis.get(key);
        }finally{
            jedis.close();
        }
        return value;
    }

    /**
     * set
     * @param key
     * @param value
     * @return
     */
    public byte[] set(byte[] key,byte[] value){
        Jedis jedis = jedisPool.getResource();
        try{
            jedis.set(key,value);
            if(this.expire != 0){
                jedis.expire(key, this.expire);
            }
        }finally{
            jedis.close();
        }
        return value;
    }

    /**
     * set
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public byte[] set(byte[] key,byte[] value,int expire){
        Jedis jedis = jedisPool.getResource();
        try{
            jedis.set(key,value);
            if(expire != 0){
                jedis.expire(key, expire);
            }
        }finally{
            jedis.close();
        }
        return value;
    }

    /**
     * del
     * @param key
     */
    public void remove(byte[] key){
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        }finally{
            jedis.close();
        }
    }

    /**
     * 删除所有前缀缓存
     */
    public void clear(String keyPrefix) {
        Jedis jedis = jedisPool.getResource();
        Set<byte[]> keys = this.keys(keyPrefix + KEY_WILDCARD_CHARACTER);
        Iterator<byte[]> iter = keys.iterator();
        while (iter.hasNext()) {
            remove(iter.next());
        }
    }

    private byte[][] byteArrayChange(Set<byte[]> keys){
        byte[][] result = new byte[][]{};
        Iterator<byte[]> iter = keys.iterator();
        int i = 0;
        while (iter.hasNext()) {
            result[i] = iter.next();
            i ++;
        }
        return  result;
    }

    /**
     * flush
     */
    public void flushDB(){
        Jedis jedis = jedisPool.getResource();
        try{
            jedis.flushDB();
        }finally{
            jedis.close();
        }
    }

    /**
     * size
     */
    public Long dbSize(){
        Long dbSize = 0L;
        Jedis jedis = jedisPool.getResource();
        try{
            dbSize = jedis.dbSize();
        }finally{
            jedis.close();
        }
        return dbSize;
    }

    /**
     * keys
     * @param pattern
     * @return
     */
    public Set<byte[]> keys(String pattern){
        Set<byte[]> keys = null;
        Jedis jedis = jedisPool.getResource();
        try{
            keys = jedis.keys(pattern.getBytes());
        }finally{
            jedis.close();
        }
        return keys;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMaxIdel(int maxIdel) {
        this.maxIdel = maxIdel;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public int getExpire() {
        return expire;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxIdel() {
        return maxIdel;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }



}
