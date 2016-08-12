package mybatis.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 

 */
public class BeanMapCovertor
{
	static Logger logger = LoggerFactory.getLogger(BeanMapCovertor.class);
	
	/**
	 * 
	 * @return
	 */
	public static <T> T mapToBean(Class<T> type, Map<String, String> map)
	{
		if (map == null || map.isEmpty())
		{
			return null;
		}
		T bean = null;
		try
		{
			bean = type.newInstance();
		}
		catch (Exception e)
		{
			return null;
		}
		Class<? extends Object> c = bean.getClass();
		for (String key : map.keySet())
		{
			Field field = null;
			try
			{
				field = c.getDeclaredField((String) key);
				String value = map.get(key);
				if (value != null)
				{
					field.setAccessible(true);

					if (field.getType() == String.class)
					{
						field.set(bean, value);
					}
					else if (field.getType() == Date.class)
					{
						long time = Long.valueOf(value);
						Date date = new Date();
						date.setTime(time);
						field.set(bean, date);
					}
					else if (field.getType() == int.class || field.getType() == Integer.class)
					{
						field.set(bean, Integer.valueOf(value));
					}
					else if (field.getType() == long.class || field.getType() == Long.class)
					{
						field.set(bean, Long.valueOf(value));
					}
					else if (field.getType() == boolean.class || field.getType() == Boolean.class)
					{
						field.set(bean, Boolean.valueOf(value));
					}
					else if (field.getType() == byte.class || field.getType() == Byte.class)
					{
						field.set(bean, Byte.valueOf(value));
					}
					else if (field.getType() == Character.class || field.getType() == char.class)
					{
						field.set(bean, value.charAt(0));
					}
					else if (field.getType() == double.class || field.getType() == Double.class)
					{
						field.set(bean, Double.valueOf(value));
					}
					else if (field.getType() == float.class || field.getType() == Float.class)
					{
						field.set(bean, Float.valueOf(value));
					}
					else if (field.getType() == short.class || field.getType() == Short.class)
					{
						field.set(bean, Short.valueOf(value));
					}
					else if (field.getType() == BigDecimal.class)
					{
						field.set(bean, BigDecimal.valueOf(Double.valueOf(value)));
					}
					else
					{

						// field.setByte(obj, b);
						// field.setChar(obj, c);
						// field.setDouble(obj, d);
						// field.setFloat(obj, f);
						// // field.setInt(obj, i);
						// // field.setLong(obj, l);
						// field.setShort(obj, s);
					}

				}
			}
			catch (Exception e)
			{
				logger.error("mapToBean转换异常, type:<{}>, field:<{}>}", type, field == null ? "null":field.getName());
				logger.error(e.getMessage(), e);
			}
		}

		return bean;
	}

	/**
	 * 将一个对象转化成字符串的 键值对
	 */
	public static Map<String, String> beanToMap(Object bean)
	{
		Map<String, String> returnMap = new HashMap<String, String>();
		if (bean != null)
		{
			Class c = bean.getClass();
			Field[] fields = c.getDeclaredFields();
			for (Field field : fields)
			{
				try
				{
					if (field.getAnnotation(NoCached.class) != null
							|| Modifier.isFinal(field.getModifiers())
							|| Modifier.isStatic(field.getModifiers()))
					{
						continue;
					}
					String propertyName = field.getName();
					field.setAccessible(true);
					Object value = field.get(bean);
					if (value != null)
					{
						if (field.getType() == Date.class)
						{
							value = String.valueOf(((Date) value).getTime());
						}
						returnMap.put(propertyName, String.valueOf(value));
					}
				}
				catch (Exception e)
				{
					logger.error("beanToMap转换异常, type:<{}>, field:<{}>}", bean.getClass(), field == null ? "null":field.getName());
					logger.error(e.getMessage(), e);
				}
			}
		}
		return returnMap;
	}

}
