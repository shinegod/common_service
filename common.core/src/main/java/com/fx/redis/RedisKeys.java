package com.fx.redis;

/**
 * Redis 缓存key生成规则
 * 
 * @author jason.jiang
 */
public class RedisKeys {
	
	public static final String KEY_ROOT = "fx";
	
	public static final String KEY_ITEM = "item";
	
	/**
	 * mt4组缓存的key
	 */
	public static final String KEY_MT4GROUPID = "mt4groupid";
	
	/**
	 * 某一数据源的mt4货币对缓存的key
	 */
	public static final String KEY_DATASOURCE_MT4CURRENCY = "datasource_mt4currency";
	
	/**
	 * @return 返回redis缓存业务唯一key
	 */
	public static String getBussionKey(Integer id) {
        StringBuffer sb = new StringBuffer(KEY_ROOT +"—"+ KEY_ITEM);
        return sb.append("—").append(id).toString();
	}
	
}
