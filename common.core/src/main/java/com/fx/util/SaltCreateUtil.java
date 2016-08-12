package com.fx.util;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class SaltCreateUtil {

	public static String createSimpleSalt(int length) {
		String strAll = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numAll = "0123456789";
		// 定义一个结果
		String result = "";
		// 实例化Random对象
		Random random = new Random();
		// 使用for循环得到6为字符
		for (int i = 0; i < length - 4; i++) {
			// 返回一个小于62的int类型的随机数
			int rd = random.nextInt(52);
			// 随机从指定的位置开始获取一个字符
			String oneChar = strAll.substring(rd, (rd + 1));
			// 循环加到6为
			result += oneChar;
		}
		for (int j = 0; j < 4; j++) {
			// 返回一个小于62的int类型的随机数
			int rd = random.nextInt(10);
			// 随机从指定的位置开始获取一个字符
			String oneChar = numAll.substring(rd, (rd + 1));
			// 循环加到6为
			result += oneChar;
		}
		return result;
	}
	public static String createSecureSalt() {
		Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[20];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
	}
	
	public static void main(String args[]) {
		/*Random ranGen = new SecureRandom();
        byte[] aesKey = new byte[20];
        ranGen.nextBytes(aesKey);
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < aesKey.length; i++) {
            String hex = Integer.toHexString(0xff & aesKey[i]);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
	        System.out.println(hexString.toString());*/
		
		String MD5Password = CodeUtil.encryptPassword("admin");//生成MD5密码
		String newSalt = createSecureSalt();
		String sha512Password = DigestUtils.sha512Hex(newSalt+MD5Password);
		System.out.println(sha512Password);
		System.out.println(newSalt);
	}
}