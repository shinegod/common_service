package com.fx.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 
 * @Description: 海玩秘钥服务类
 * @author: <a href="mailto:beier.zheng@haiwan.com">beier.zheng</a>
 * @since: 郑贝尔-2016年1月5日
 * @version: 0.0.1
 *
 */
@Service("hwDigestServiceImpl")
public class HwDigestServiceImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(HwDigestServiceImpl.class);
	private static final String HMAC_SHA = "HmacSHA256";//"HmacSHA256",HmacSHA1  
	private static final String CHARSET = "utf-8";
	
	/**
     * 
     * @Description:  产生秘钥 HmacSHA256 
     * @return 
	 * @throws NoSuchAlgorithmException 
     * @since：郑贝尔-2015年12月31日
     *
     */
	public String createkey() throws NoSuchAlgorithmException {  
		// 初始化摘要算法的密钥产生器  
		KeyGenerator generator = KeyGenerator.getInstance(HMAC_SHA);  
		 // 产生密钥  
		SecretKey secretKey = generator.generateKey();  
		byte[] value = secretKey.getEncoded();
		// 获得密钥 
		//return  new HexBinaryAdapter().marshal(secretKey.getEncoded()); 
		return DatatypeConverter.printHexBinary(secretKey.getEncoded());
	}  
	

	/**
	 * 
	 * @Description: 验证签名服务，直接将参数签名
	 * @param privateKey 参与验证的参数和accessKey
	 * @return boolean :true 成功,false失败
	 * @author：<a href="mailto:beier.zheng@haiwan.com">beier.zheng</a>
	 * @since：郑贝尔-2016年1月5日
	 *
	 */
	public String signature(String data,String privateKey) {
		try {
			return gethmac(data.getBytes(CHARSET),privateKey.getBytes(CHARSET));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @Description: 验证签名服务，把map排序后签名
	 * @param param 参与验证的参数
	 * @return boolean :true 成功,false失败
	 * @author：<a href="mailto:beier.zheng@haiwan.com">beier.zheng</a>
	 * @throws APIException 
	 * @since：郑贝尔-2016年1月5日
	 *
	 */
	public String signatureSort(Map<String,String> params,String privateKey) {
		return getSign(params,privateKey);
	}


	/**
	    * 生成签名数据
	    * @param sPara 要签名的数组
	    * @return 签名结果字符串
	    */
	public String getSign(Map<String, String> params,String signKey) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr = "";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
		   String value = params.get(key);
		   if(i == keys.size() - 1) {//拼接时，不包括最后一个&字符
		       prestr = prestr + key + "=" + value;
		   }else{
		       prestr = prestr + key + "=" + value + "&";
		   }
		}
		try {
			logger.info("=====pre sign Str:["+prestr+"]==========");
			return gethmac(prestr.getBytes(CHARSET),signKey.getBytes(CHARSET));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	   
	   
	/**
	 * 
	 * @Description: 获取hmac
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception String
	 * @author：<a href="mailto:beier.zheng@haiwan.com">beier.zheng</a>
	 * @since：郑贝尔-2015年12月31日
	 *
	 */
	private String gethmac(byte[] data, byte[] key) throws Exception {  
		SecretKey secretKey = new SecretKeySpec(key, HMAC_SHA);  
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());  
		mac.init(secretKey);  
		byte[] digest = mac.doFinal(data);  
		return DatatypeConverter.printHexBinary(digest);  
	}  

	public static void main(String[]a) throws NoSuchAlgorithmException{
		HwDigestServiceImpl hw = new HwDigestServiceImpl();
		//System.out.println(hw.createkey());
		//秘钥
		String key = "32959E34807D5C60DEBA3A2C9D1F196DABB7FC60EC66613C28BF0456388B4013";
		//获取签名
		//Map<String,String> map = new HashMap<String,String>();
		//map.put("createTime", "1452152688427");
		//System.out.println("---");
//		System.out.println(hw.signature("我是春磊", key));
		
	}
}
