package com.fx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md5 {
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String getMD5ofStr(String str){
		return getMD5ofStr(str,"utf-8");
	}
	
	public static String getMD5ofStr(String str, String encode) {
		try{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes(encode));
		byte[] digest = md5.digest();

		StringBuffer hexString = new StringBuffer();
		String strTemp;
		for (int i = 0; i < digest.length; i++) {
			// byteVar &
			// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
			// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
			// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
			strTemp = Integer.toHexString(
					(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
			hexString.append(strTemp);
		}
			return hexString.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}

	}
	
	// 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }


	
	private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }
	
	public static String getMD5(String strObj) {
		String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;

	}
	
	public static void main(String[] args) {
		String ss= "MemberID=100000178~|~TerminalID=10000001~|~TransID=100000178480~|~Result=1~|~ResultDesc=01~|~FactMoney=6~|~AdditionalInfo=~|~SuccTime=20150302095952~|~Md5Sign=abcdefg";
		System.out.println(md5.getMD5ofStr(ss));
	}


}
