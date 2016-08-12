package com.fx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 非spring项目调用项目中的bean时,使用此工具类
 *
 */
public class BeanFactoryUtil
{
	static Logger logger = LoggerFactory.getLogger(BeanFactoryUtil.class);
	
	private static ClassPathXmlApplicationContext context = null;

	private static ClassPathXmlApplicationContext getContext()
	{
		if (context == null)
		{
			synchronized (BeanFactoryUtil.class)
			{
				if (context == null)
				{
					initContext();
				}
			}
		}
		return context;
	}

	private static void initContext()
	{
		try
		{
			long startTime = System.currentTimeMillis();
			logger.info("[corp2]Initializing Spring Context ......");
			String[] path = {
					"classpath*:config/corp2ApplicationContext.xml"
			};
			context = new ClassPathXmlApplicationContext(path);
			logger.info("[corp2]Initialize complete, time=" + (System.currentTimeMillis() - startTime) + " milliseconds!");
			context.start();
		}
		catch (Exception e)
		{
			logger.error("[demo]初始化Spring上下文异常", e);
		}
	}

	/**
	 * 
	 * @param beanName
	 * @return
	 */
	public static Object getBean(String beanName)
	{
		if (getContext() != null)
		{
			return getContext().getBean(beanName);
		}
		return null;
	}

	/**
	 * 
	 * @param <T>
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType)
	{
		if (getContext() != null)
		{
			return getContext().getBean(requiredType);
		}
		return null;
	}
	
	/**
	 * 容器关闭时,最好手动关闭spring上下文
	 */
	public static void stopContext()
	{
		if (context != null)
		{
			try
			{
				context.stop();
				context.close();
				context = null;
			}
			catch (Exception e)
			{
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	
}
