package com.fx.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PayerInfoProperties {
	
	protected static Properties payerInfoProperties = new Properties();
	
	static {
		FileInputStream fileinput=null;
		try {
			Class cls = Class.forName(DBConstant.class.getName());
			DBConstant gl = (DBConstant) cls.newInstance();
			java.net.URL abspath=gl.getClass().getClassLoader().getResource("");
			String path=(abspath.getPath()).toString();
			System.out.println("----"+path);
			fileinput = new FileInputStream(path+"/payerInfo.properties");
			payerInfoProperties.load(fileinput);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fileinput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key){
		if( key == null ){
			return null;
		}else{
			return payerInfoProperties.getProperty(key);
		}
	}
	
	public static void main(String[] args){
		String s = getProperty("PRIVATE_PRIVATE_KEY_PATH");
		System.out.println(s);
	}
}
