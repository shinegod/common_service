package com.fx.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mybatis.framework.util.BeanMapCovertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.fx.util.Config;

/**
 * redis 客户端管理
 * 
 * @author jason.jiang
 */
public class RedisCacheManager {

	private static final Logger log = LoggerFactory.getLogger(RedisCacheManager.class);
	
	private static final String REDIS_SERVER_IP = Config.getConfig("redis_server_ip");
	
	private static final String REDIS_SERVER_PORT = Config.getConfig("redis_server_port");

	private static final String REDIS_POOL_MAXIDLE = Config.getConfig("redis_pool_maxIdle");
	
	private static final String REDIS_POOL_MAXNUM = Config.getConfig("redis_pool_maxNum");
	
	private static final String REDIS_POOL_MAXWAITMILLIS = Config.getConfig("redis_pool_maxWaitMillis");
	
	private static final int EXPIRE_HOUR_SECONDS = Integer.parseInt(Config.getConfig("redis_expire"));
	
	private static final int DB_INDEX = Integer.parseInt(Config.getConfig("redis_db_index"));
	
	private JedisPool jedisPool;
	
	private static final RedisCacheManager instance = new RedisCacheManager();
	
	public static RedisCacheManager getInstance() {
		return instance;
	}
	
	private RedisCacheManager() {
		initJedisPool();
	}

	private void initJedisPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(Integer.parseInt(REDIS_POOL_MAXIDLE));
		config.setMaxTotal(Integer.parseInt(REDIS_POOL_MAXNUM));
		config.setMaxWaitMillis(Integer.parseInt(REDIS_POOL_MAXWAITMILLIS));
		jedisPool = new JedisPool(config, REDIS_SERVER_IP, Integer.parseInt(REDIS_SERVER_PORT));
	}

	/**
	 * Get a source for jedis, if no more resouce exists, exception be throw
	 * 
	 * @return A jedis client instance
	 */
	protected Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
		} catch (Exception e) {
			log.error("Failed to get jedis resource, reason is " + e.getMessage());
		}
		return jedis;
	}

	/**
	 * 缓存存放字符串
	 * @param key
	 * @param value
	 * @return 返回存放缓存状态
	 */
	public String setString(String key, String value, int expire) {
		String state = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				state = jedis.set(key, value);
				if (expire != 0) {
					jedis.expire(key, expire);
				} else {
					jedis.expire(key, EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e) {
			log.error("缓存存放字符串错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return state;
	}

	/**
	 * 返回缓存存放字符串
	 * @param key
	 * @return 返回缓存中存放字符串
	 */
	public String getString(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				result = jedis.get(key);
			}
		} catch (Exception e) {
			log.error("返回缓存存放字符串错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return result;
	}
	
	/**
	 * 缓存存放List
	 * @param key
	 * @param list
	 * @return 返回缓存list
	 */
	public Long setList(String key, List<String> list, int expire) {
		long state = 0;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key) && list != null) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				for (int i = 0; i < list.size(); i++) {
					state = jedis.sadd(key, list.get(i));
				}
				if (expire != 0) {
					jedis.expire(key, expire);
				} else {
					jedis.expire(key, EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e) {
			log.error("缓存存放List错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return state;
	}
	
	/**
	 * 获取缓存Set
	 * @param key
	 * @return 返回缓存set
	 */
	public Set<String> getList(String key) {
		Set<String> set = null;
		try {
			if (key != null && !("").equals(key)) {
				Jedis jedis = getResource();
				jedis.select(DB_INDEX);
				set = jedis.smembers(key);
			}
		} catch (Exception e) {
			log.error("获取缓存Set错误!!", e);
		}
		return set;
	}	

	/**
	 * 缓存中放List<Object>
	 * @param key
	 * @param list
	 * @return 返回设置缓存状态
	 */
	public String setListObject(String key, List<Object> list, int expire) {
		String state = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key) && list != null) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				state = jedis.set(key.getBytes(), ObjectsTranscoder.serialize(list));
				if (expire != 0) {
					jedis.expire(key, expire);
				} else {
					jedis.expire(key, EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e) {
			log.error("缓存中放List错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return state;
	}
	
	/**
	 * 获取缓存中List<Object>
	 * @param key
	 * @return 返回缓存中存放的List<Object>
	 */
	public List<Object> getListObject(String key) {
		List<Object> objectList = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				byte[] in = jedis.get(key.getBytes());
				objectList = ObjectsTranscoder.getSerializeList(in);
			}
		} catch (Exception e) {
			log.error("缓存中放List错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return objectList;
	}
	
	/**
	 * 缓存中存放对象
	 * @param key
	 * @param object
	 * @return 返回缓存中存放状态
	 */
	public String setObject(String key, Object object, int expire) {
		String state = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				state = jedis.set(key.getBytes(), ObjectsTranscoder.serialize(object));
				if (expire != 0) {
					jedis.expire(key, expire);
				} else {
					jedis.expire(key, EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e) {
			log.error("缓存中存放对象错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return state;
	}
	
	/**
	 * 缓存中获取对象
	 * @param key
	 * @return 缓存中获取对象
	 */
	public Object getObject(String key) {
		Object object = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				byte[] in = jedis.get(key.getBytes());
				if (in != null && in.length > 0) {
					object = ObjectsTranscoder.getSerializeObject(in);
				}
			}
		} catch (Exception e) {
			log.error("缓存中存放对象错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return object;
	}
	
	/**
	 * 缓存中存放Map
	 * @param key
	 * @param map
	 * @return 返回缓存中Map
	 */
	public String setMap(String key, Map<String, String> map, int expire) {
		String state = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				state = jedis.hmset(key, map);
				if (expire != 0) {
					jedis.expire(key, expire);
				} else {
					jedis.expire(key, EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e){
			log.error("缓存中存放Map错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return state;
	}
	
	/**
	 * 缓存中获取Map
	 * @param key
	 * @param map
	 * @return 返回缓存中Map
	 */
	public Map<String, String> getMap(String key) {
		Map<String, String> map = null;
		Jedis jedis = null;
		try {
			if (key != null && !("").equals(key)) {
				jedis = getResource();
				jedis.select(DB_INDEX);
				map = jedis.hgetAll(key);
			}
		} catch (Exception e){
			log.error("缓存中获取Map错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return map;
	}
	
	/**
	 * 删除缓存中指定key的数据
	 * @param keys
	 * @return 删除指定key的数据(0-失败, 1-成功)
	 */
	public Long del(String... keys) {
		Long delstate = null;
		Jedis jedis = null;
		if (keys != null && keys.length > 0) {
			try {
				jedis = getResource();
				jedis.select(DB_INDEX);
				delstate = jedis.del(keys);
			} catch (Exception e) {
				log.error("删除缓存中指定key的数据错误!!", e);
			} finally {
				returnResource(jedis);
			}
		}
		return delstate;
	}
	
	/**
	 * 列表头部增加数据
	 * @param key
	 * @param values
	 */
	public void lpush(String key, String... values){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpush(key, values);
		} catch (Exception e) {
			log.error("列表头部增加数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 列表尾部增加数据
	 * @param key
	 * @param values
	 */
	public void rpush(String key, String... values){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.rpush(key, values);
		} catch (Exception e) {
			log.error("列表尾部增加数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 列表头部删除元素
	 * @param key
	 * @param values
	 */
	public void rpop(String key){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.rpop(key);
		} catch (Exception e){
			log.error("列表头部删除元素数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 列表尾部删除元素
	 * @param key
	 * @param values
	 */
	public void lpop(String key){
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.lpop(key);
		} catch (Exception e){
			log.error("列表尾部删除元素数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 获取List<String>
	 * @param keys
	 * @return
	 */
	public List<String> getStringList(String... keys){
		List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			list = jedis.mget(keys);
		} catch (Exception e) {
			log.error("获取List数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return list;
	}
	
	/**
	 * 获取指定区间数据
	 */
	public List<String> getListString(String key, long start, long end) {
		List<String> list = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			list = jedis.lrange(key, start, end);
		} catch (Exception e) {
			log.error("获取指定区间数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return list;
	}
	
	
	/**
	 * 删除List中指定下标数据
	 * @param key
	 * @param valueOf
	 */
	public void delFromList(String key, long count, String value) {
		Jedis jedis = null;
		try	{
			jedis = getResource();
			jedis.lrem(key, count, value);
		} catch (Exception e) {
			log.error("删除List中指定下标数据错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}
	
	/**
	 * 把一个bean装成一个map 然后存储
	 */
	public String setBean(String key,Object bean, int expire){
		String ret = null;
		Jedis jedis = null;
		Map<String, String> map = BeanMapCovertor.beanToMap(bean);
		try	{
			jedis = getResource();
			ret = jedis.hmset(key, map);
			if (expire != 0) {
				jedis.expire(key, EXPIRE_HOUR_SECONDS);
			} else {
				jedis.expire(key, EXPIRE_HOUR_SECONDS);
			}
		} catch (Exception e) {
			log.error("把一个bean装成一个map 然后存储错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return ret;
	}
	
	/**
	 * 把一个map转成一个bean 取出
	 */
	public <T> T getBean(String key, Class<T> bean){
		T obj = null;
		Jedis jedis = null;
		try	{
			jedis = getResource();
			Map<String, String> map= jedis.hgetAll(key);
			obj = (T) BeanMapCovertor.mapToBean(bean, map);
		} catch (Exception e)	{
			log.error("把一个map转成一个bean 取出错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return obj;
	}
	
	/**
	 * 取多个Bean
	 */
	public <T> List<T> getBeans(String [] keys, Class<T> bean){
		if(keys==null || keys.length == 0){
			return null;
		}
		T obj = null;
		Jedis jedis = null;
		List<T> list = null;
		try{
			list = new ArrayList<T>();
			jedis = getResource();
			for(String key : keys){
				Map<String, String> map= jedis.hgetAll(key);
				obj = (T) BeanMapCovertor.mapToBean(bean, map);
				if (obj != null){
					list.add(obj);
				}
			}
		} catch (Exception e){
			log.error("取多个Bean错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return list;
	}
	
	/**
	 * 存多个Bean
	 */
	public String setBeans(String [] keys,List<?> beans, int expire){
		if(keys==null || keys.length == 0){
			return null;
		}
		String ret = null;
		Jedis jedis = null;
		try{
			jedis = getResource();
			for(int i=0;i<keys.length;i++){
				if(beans.get(i)==null){
					continue;
				}
				Map<String, String> map = BeanMapCovertor.beanToMap(beans.get(i));
				ret = jedis.hmset(keys[i], map);
				if (expire != 0) {
					jedis.expire(keys[i], expire);
				} else {
					jedis.expire(keys[i], EXPIRE_HOUR_SECONDS);
				}
			}
		} catch (Exception e){
			log.error("存多个Bean错误!!", e);
		} finally {
			returnResource(jedis);
		}
		return ret;
	}
	
	public void cleanAll() {
		Jedis jedis = null;
		try {
			jedis = getResource();
			jedis.flushDB();
		} catch (Exception e) {
			log.error("cleanAll错误!!", e);
		} finally {
			returnResource(jedis);
		}
	}

	/**
	 * Return a resource
	 * 
	 * @param jedis
	 */
	@SuppressWarnings("deprecation")
	protected void returnResource(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		try {
			jedisPool.returnResource(jedis);
		} catch (Exception e) {
			log.error("Failed to return jedis resource, reason is "	+ e.getMessage());
		}
	}

	/**
	 * redis链接失效时调用 这个
	 * 
	 * @param jedis
	 * @author changyong.cai
	 * @date 2013-4-26 上午11:41:04
	 */
	@SuppressWarnings("deprecation")
	protected void returnBrokenResource(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		try {
			jedisPool.returnBrokenResource(jedis);
		} catch (Exception e) {
			log.error("Failed to return jedis resource, reason is "	+ e.getMessage());
		}
	}
	
}
