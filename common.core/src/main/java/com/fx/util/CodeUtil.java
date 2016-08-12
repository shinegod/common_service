package com.fx.util;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

public class CodeUtil 
{
	/**
	 * in this class, method convertToMd5 will definitely return a String with length MD5_LENGTH
	 */
	public static final int MD5_LENGTH = 32;
	
	private static final String seed_prifex = "hadwins";
	
	private static final String seed_suffex = "@#GFhv478(7655jdHg";
	
	/**
	 * 生成管理后台密码
	 * @param password
	 * @return
	 */
	public static String encryptAdminPassword(String password){
		String entryPassword = CodeUtil.convertToMd5(password.trim());
		String finalPassword = CodeUtil.convertToMd5(seed_prifex+entryPassword+"admin");
		return finalPassword;
	}
	
	/**
	 * 生成最终密码
	 * @param password
	 * @return
	 */
	public static String encryptPassword(String password){
		String entryPassword = CodeUtil.convertToMd5(password.trim());
		//String finalPassword = CodeUtil.convertToMd5(entryPassword);
		return entryPassword;
	}
	
	public static String encryptMd5(String password){
		String entryPassword = CodeUtil.convertToMd5(password.trim());
		String finalPassword = CodeUtil.convertToMd5(entryPassword);
		return finalPassword;
	}
	
	public static String getMd5(byte[] buffer) throws NoSuchAlgorithmException{
	    String s  = null;
	    char hexDigist[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(buffer);
	    byte[] datas = md.digest(); //16个字节的长整数
	    char[] str = new char[2*16];
	    int k = 0;
	    for(int i=0;i<16;i++){
	      byte b   = datas[i];
	      str[k++] = hexDigist[b>>>4 & 0xf];//高4位
	      str[k++] = hexDigist[b & 0xf];//低4位
	    }
	    s = new String(str);
	    return s;
	  }
	
	 public static String md5s(String plainText) {
         try{
              MessageDigest md = MessageDigest.getInstance("MD5");
              md.update(plainText.getBytes("UTF-8"));    //問題主要出在這裏，Java的字符串是unicode編碼，不受源碼文件的編碼影響；而PHP的編碼是和源碼文件的編碼一致，受源碼編碼影響。
              StringBuffer buf=new StringBuffer();            
              for(byte b:md.digest()){
                   buf.append(String.format("%02x", b&0xff));        
              }
             return  buf.toString();
           }catch( Exception e ){
               e.printStackTrace();   
               return null;
            }
    } 
	 
	 private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5',
         '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', };

 public static String asHex(byte hash[]) {
     char buf[] = new char[hash.length * 2];
     for (int i = 0, x = 0; i < hash.length; i++) {
         buf[x++] = HEX_CHARS[(hash[i] >>> 4) & 0xf];
         buf[x++] = HEX_CHARS[hash[i] & 0xf];
     }
     return new String(buf);
 }
 
 public static String md5(String input) throws NoSuchAlgorithmException {
	    String result = input;
	    if(input != null) {
	        MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
	        md.update(input.getBytes());
	        BigInteger hash = new BigInteger(1, md.digest());
	        result = hash.toString(16);
	        while(result.length() < 32) { //40 for SHA-1
	            result = "0" + result;
	        }
	    }
	    return result;
	}

	
//	public static void main(String[] args) {
//		String MD5key = "]HjVS(XH";				//MD5私钥
//		String MerNo = "2622";					//商户号
//		//$his = date('his');
//		String BillNo = "150-1127823@qq.com-120014";		//[必填]订单号(商户自己产生:要求不重复)
//		String Currency = "1";					//[必填]交易币种1:代表美金2:欧元4:英镑
//		String Amount = "11.00";		//[必填]订单金额
//		String Language = "1";					//[必填]语言2:代表英文；1:代表中文
//		String ReturnURL = "http://www.dowellforex.com/deposit/account_depositCardReturn";
//		//[必填]返回数据给商户的地址(商户自己填写):::注意请在测试前将该地址告诉我方人员;否则测试通不过
//	    String ss = MerNo+BillNo+Currency+Amount+Language+ReturnURL+MD5key;	
//		String aa;
//		try {
//			aa = md5(ss);
//			System.out.println("aa==>"+aa);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//ae7818ea2f6946c244d17c000eb74769
		
//		double dd = 123.45;
//		String str=String.valueOf(dd).replaceAll("\\d+\\.", "");
//		System.out.println(str);
		
//	}
	
	public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

	
	/*
     * 将字符数组转换为16进制字符串
     */
	public static final String bytesToHexStr(
			byte[] bcd)
		{
			StringBuffer s = new StringBuffer(bcd.length * 2);

			for (int i = 0; i < bcd.length; i++)
			{
				s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
				s.append(bcdLookup[bcd[i] & 0x0f]);
			}

			return s.toString();
		}
	 /*
     * 将16进制字符串转换为字符数组
     */
	public static final byte[] hexStrToBytes(
			String	s)
		{
		
		if ( s == null ){
			return null;
		}
			byte[]	bytes;

			bytes = new byte[s.length() / 2];

			for (int i = 0; i < bytes.length; i++)
			{
				bytes[i] = (byte)Integer.parseInt(
						s.substring(2 * i, 2 * i + 2), 16);
			}

			return bytes;
		}
	

	 /*
    * 将16进制字符串转换为字符数组
    */
	public static final String hexStrToString(String	s)
		{
		
		if ( s == null ){
			return null;
		}
		
			byte[]	bytes;

			bytes = new byte[s.length() / 2];

			for (int i = 0; i < bytes.length; i++)
			{
				bytes[i] = (byte)Integer.parseInt(
						s.substring(2 * i, 2 * i + 2), 16);
			}

			return new String(bytes);
		}
	
	private static final char[] bcdLookup =
	{
		'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'
	};
	
	public static String convertToMd5(String str){
		
		if ( str == null ){
			return null;
		}
		byte newByte1[] = str.getBytes();
		try{
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			byte newByte2[] = messagedigest.digest(newByte1);
			String cryptograph = "";
			for(int i=0; i<newByte2.length; i++){
				String temp = Integer.toHexString( newByte2[i] & 0x000000ff );
				if( temp.length()<2 ) temp = "0"+temp;
				cryptograph += temp;
			}
			return cryptograph;
		}
		catch(Exception e){
			System.out.println("[ "+new java.util.Date().toString()+" ] [ CodeUtil.convertToMd5 ] "+e.toString());
			return null;
		}
	}
	
	public static String convertToSHA1(String str){
		
		if ( str == null ){
			return null;
		}
		byte newByte1[] = str.getBytes();
		try{
			MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
			byte newByte2[] = messagedigest.digest(newByte1);
			String cryptograph = "";
			for(int i=0; i<newByte2.length; i++){
				String temp = Integer.toHexString( newByte2[i] & 0x000000ff );
				if( temp.length()<2 ) temp = "0"+temp;
				cryptograph += temp;
			}
			return cryptograph;
		}
		catch(Exception e){
			System.out.println("[ "+new java.util.Date().toString()+" ] [ CodeUtil.convertToSHA1 ] "+e.toString());
			return null;
		}
	}
	
	public static String getHmacSha1(String data, String key) {
		byte[] byteHMAC = null;
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec spec = new SecretKeySpec(key.getBytes(), "HmacSHA1");
			mac.init(spec);
			byteHMAC = mac.doFinal(data.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException ignore) {
		}
		String oauth = new BASE64Encoder().encode(byteHMAC);
		return oauth;
	}
	
	public static String getMT4Password() {
		String base1 = "ABCDEFGHJKLMNPQRSTUVWXYZ";
		Random random = new Random();
		Set<String> set = new HashSet<String>();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 2; i++) {
			int number = random.nextInt(base1.length());
			set.add(String.valueOf(base1.charAt(number)));
		}
		String base2 = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < 2; i++) {
			int number = random.nextInt(base2.length());
			set.add(String.valueOf(base2.charAt(number)));
		}
		String base3 = "0123456789";
		for (int i = 0; i < 2; i++) {
			int number = random.nextInt(base3.length());
			set.add(String.valueOf(base3.charAt(number)));
		}
		for(String s : set){
			sb.append(s);
		}
		return sb.toString();
	}
	
	public static String getRandomString(int length) {
		if ( length < 1 ){
			length = 1;
		}
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
		int number = random.nextInt(base.length());
		sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	/**
	 * public static String getRandomString(int length)是用来生成BaiduPassportBean里的签名的
	 * 本方法是用来生成登录和注册的验证码的
	 * 验证码不区分大小写，所以大小写之前的易混淆不考虑
	 * 已去除[I, l, 1]，[O, o, 0]等易混淆字母，如发现还有易混淆的，应该继续去除
	 * @param length
	 * @return
	 */
	public static String getRandomStringForVerifyCode(int length) {
		if ( length < 1 ){
			length = 1;
		}
		String base = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	public static String bSubstring(String s, int length) throws Exception  {
		return bSubstring(s, length, "");
	}
    /**
     * 截取字符串
     * @param s
     * @param length
     * @param suffix
     * @return
     * @throws Exception
     */
	public static String bSubstring(String s, int length, String suffix) throws Exception  
    {  
		if(null==s )
		{
			return s;
		}
        byte[] bytes = s.getBytes("Unicode");  
        
        if(bytes.length<length)
        {
        	return s;
        }
        
        int n = 0; // 表示当前的字节数  
        int i = 2; // 要截取的字节数，从第3个字节开始  
        for (; i < bytes.length && n < length; i++)  
        {  
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节  
            if (i % 2 == 1)  
            {  
                n++; // 在UCS2第二个字节时n加1  
            }  
            else 
            {  
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节  
                if (bytes[i] != 0)  
                {  
                    n++;  
                }  
            }  
        }  
        // 如果i为奇数时，处理成偶数  
        if (i % 2 == 1)  
     
        {  
            // 该UCS2字符是汉字时，去掉这个截一半的汉字  
            if (bytes[i - 1] != 0)  
                i = i - 1;  
            // 该UCS2字符是字母或数字，则保留该字符  
            else 
                i = i + 1;  
        }  
        String  result = new String(bytes, 0, i, "Unicode");
        if(suffix != null && length < bytes.length) {
        	result += suffix; 
        }
        return result;  
    }  
	
	/**
	 * 取得字符串的字节长度
	 * @param word
	 * @return
	 */
	public static int getBytesLength(String word) {
		String str = word.replaceAll("[^\\x00-\\xff]", "**");
		return str.length();
	}
	
	public static String md5(String str, String encode) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes(encode));
			byte[] digest = md5.digest();
			StringBuffer hexString = new StringBuffer();
			String strTemp;
			for (int i = 0; i < digest.length; i++) {
				strTemp = Integer.toHexString(
						(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
				hexString.append(strTemp);
			}
			return hexString.toString();
		} catch (Exception e) {
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptPassword("1234"));
	}
	
}
