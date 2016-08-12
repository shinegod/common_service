package com.fx.cache;

import com.fx.cache.redis.RedisCacheManager;
import com.fx.util.SpringUtils;
import com.wandoulabs.jodis.JedisResourcePool;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

/**
 * Created by bei2love@gmail.com on 15/9/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class RedisCacheTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheTest.class);

    private RedisCacheManager cacheManager;

    @Before
    public void init() {
        cacheManager = SpringUtils.getBean(RedisCacheManager.class);
    }

    @Test
    public void testCachePut() {
        Cache cache = cacheManager.getCache("shiro");
        cache.put("test_0908111", "hello santos111");
        logger.info("缓存服务存存放的内容:key:{}， value:{}", "test_0908111", cache.get("test_0908111"));
    }

    @Test
    public void testCacheGetKeys() {
        Cache cache = cacheManager.getCache("shiro");
        Set<String> keys = cache.keys();
        logger.info("缓存服务存在的缓存数量:{}", keys.size() );
        Collection c = cache.values();
        logger.info("缓存服务存在的值数量:{}", c.size());
    }


    @Test
    public void testCacheKey() {
        Cache cache = cacheManager.getCache("new-cache1");
        cache.put("test_0908111", "hello santos111");
        cache.put("test_0908112", "hello santos111");
        cache = cacheManager.getCache("new-cache2");
        cache.put("test_0908111", "hello santos111");
        cache.put("test_0908112", "hello santos111");
        cacheManager.setKeyPrefix("new-config");
        cache.put("test_0908111", "hello santos111");
        cache.put("test_0908112", "hello santos111");
        cache = cacheManager.getCache("new-cache2");
        cache.put("test_0908111", "hello santos111");
        cache.put("test_0908112", "hello santos111");

        cache.put(Integer.valueOf(1000),"test integ");

        cache.put(Integer.valueOf(1000),"test inte123123123123121g");

        logger.info("缓存服务存存放的内容:key:{}， value:{}", "test_0908111", cache.get(Integer.valueOf(1000)));
    }

    @Test
    public void testCacheDel(){
        Cache cache = cacheManager.getCache("new-cache1");
        cache.put(Integer.valueOf(10001), "hello santos111");
        logger.info("缓存服务存存放的内容:key:{}， value:{}", "test_0908111", cache.get(Integer.valueOf(10001)));
        cache.remove(Integer.valueOf(10001));
        logger.info("缓存服务存存放的内容:key:{}， value:{}", "test_0908111", cache.get(Integer.valueOf(10001)));
        cache.put("test_0908112", "hello 存放的内");
        logger.info("缓存服务存存放的内容:key:{}， value:{}", "test_0908111", cache.get("test_0908112"));
        cache.clear();
        logger.info("缓存服务存存放的内容:size:{}", cache.keys().size());
    }

    @Test
    public void testCachePutMap() {
        Map<Integer, Set<String>> groupMap = new HashMap<>();
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put("ASDASDF_ASDFADS", "ASDASDF_123ASDFADS");
        keyMap.put("ASDASDF_ASDF12ADS", "ASDASDF_A123SDFADS");
        keyMap.put("ASDASDF_AS44DFADS", "ASDASDF_ASD123FADS");
        keyMap.put("ASDASDF_AS12D12FADS", "ASDASDF_ASD123FADS");
        keyMap.put("ASDASDF_ASD12FADS", "ASDASDF_ASDF123ADS");
        groupMap.put(1, new HashSet<String>(keyMap.keySet()));

        Cache cache = cacheManager.getCache("shiro");
        cache.put("group", groupMap);
    }

    @Test
    public void testCodis(){
        JedisResourcePool jedisPool = new RoundRobinJedisPool("RedisA.ty.com:2181,RedisB.ty.com:2181,RedisC.ty.com:2181", 30000, "/zk/codis/db_redis/proxy", new JedisPoolConfig());
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("foo", "bar");
            String value = jedis.get("foo");
            System.out.println(value);
        }
    }
}
