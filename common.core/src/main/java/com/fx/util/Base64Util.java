package com.fx.util;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	/**    
	* BASE64解密   
	* @param key          
	* @return          
	* @throws Exception          
	*/              
	public static byte[] decryptBASE64(String key) throws Exception {               
		return (new BASE64Decoder()).decodeBuffer(key);               
	}               
	/**         
	* BASE64加密   
	* @param key          
	* @return          
	* @throws Exception          
	*/              
	public static String encryptBASE64(byte[] key) throws Exception {               
		return (new BASE64Encoder()).encodeBuffer(key);               
	}  
	public static void main(String[] args) {
		try {
//			System.out.println(Base64Util.encryptBASE64("986487186@qq.com".getBytes()));
			Base64 base =  new Base64();
			System.out.println(base.encode("q12345".getBytes()));
			System.out.println(new String(base.decode(base.encode("q12345".getBytes()))));
//			System.out.println(Base64Util.encryptBASE64("q12345".getBytes()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
