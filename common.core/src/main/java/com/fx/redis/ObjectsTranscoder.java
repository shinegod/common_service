package com.fx.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象序列化
 * 
 * @author jason.jiang
 */
public class ObjectsTranscoder {
	
	private static final Logger logger = LoggerFactory.getLogger(ObjectsTranscoder.class);

	/**
	 * 将对象序列化字节传输
	 * @param value
	 * @return 返回序列化后字节
	 */
	public static byte[] serialize(List<Object> list) {
		
		if (list == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for (Object object : list) {
				os.writeObject(object);
			}
			os.writeObject(null);
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			try {
				os.close();
				bos.close();
			} catch (IOException e) {
				logger.error("关闭连接错误!!", e);
			}
		}
		return rv;
	}
	
	/**
	 * 将对象序列化字节传输
	 * @param value
	 * @return 返回序列化后字节
	 */
	public static byte[] serialize(Object obj) {
		if (obj == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(obj);
			rv = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			try {
				os.close();
				bos.close();
			} catch (IOException e) {
				logger.error("关闭连接错误!!", e);
			}
		}
		return rv;
	}

	/**
	 * 反序列化对象List
	 * @param in
	 * @return 返回反序列对象List
	 */
	public static List<Object> getSerializeList(byte[] in) {
		List<Object> list = new ArrayList<Object>();
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				while (true) {
					Object object = (Object) is.readObject();
					if (object == null) {
						break;
					} else {
						list.add(object);
					}
				}
			}
		} catch (Exception e) {
			logger.error("反序列化对象List错误!!", e);
		} finally {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				logger.error("关闭连接错误!!", e);
			}
		}
		return list;
	}
	
	/**
	 * 反序列化对象
	 * @param in
	 * @return 返回反序列对象
	 */
	public static Object getSerializeObject(byte[] in) {
		Object object = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				object = (Object) is.readObject();
			}
		} catch (Exception e) {
			logger.error("反序列化对象错误!!", e);
		} finally {
			try {
				is.close();
				bis.close();
			} catch (IOException e) {
				logger.error("关闭连接错误!!", e);
			}
		}
		return object;
	}
}