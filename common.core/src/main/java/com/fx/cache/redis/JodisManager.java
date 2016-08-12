package com.fx.cache.redis;

import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by bei2love@gmail.com on 15/9/19.
 */
public class JodisManager implements Redis{


    private String haconfig;

    private String zkPath;

    private int expire = 0 ;

    private Integer sessionTimeout = 30000;

    public String getHaconfig() {
        return haconfig;
    }

    public void setHaconfig(String haconfig) {
        this.haconfig = haconfig;
    }

    public String getZkPath() {
        return zkPath;
    }

    public void setZkPath(String zkPath) {
        this.zkPath = zkPath;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Integer sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    private static JedisResourcePool jedisPool = null;

    private JedisPoolConfig jedisPoolConfig;

    private static final String KEY_WILDCARD_CHARACTER = "*";

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    /**
     * 初始化Jedis连接池
     */
    public void init() {
        initPoolConfig();
        jedisPool = new RoundRobinJedisPool(haconfig, sessionTimeout, zkPath, new JedisPoolConfig());


    }

    private void initPoolConfig() {
        if (jedisPoolConfig == null) {
            jedisPoolConfig = new JedisPoolConfig();
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
}
