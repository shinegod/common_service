package com.fx.payment.payer._99bill;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class Pkipair {
	
	
	public String signMsg( String signMsg) {

		String base64 = "";
		try {
			// 密钥仓库
			KeyStore ks = KeyStore.getInstance("PKCS12");

			String priKeyPath = PayFor99BillConstants.PRIVATE_PRIVATE_KEY_PATH;
			if(priKeyPath.startsWith("file://")){
				priKeyPath = priKeyPath.substring(7);
			}
			if(priKeyPath.startsWith("file:")){
				priKeyPath = priKeyPath.substring(5);
			}
			FileInputStream ksfis = new FileInputStream(priKeyPath);
			// 读取密钥仓库
			BufferedInputStream ksbufin = new BufferedInputStream(ksfis);

			char[] keyPwd = PayFor99BillConstants.PRIVATE_KEY_STORE_PASSWORD.toCharArray();
			ks.load(ksbufin, keyPwd);
			// 从密钥仓库得到私钥
			PrivateKey priK = (PrivateKey) ks.getKey(PayFor99BillConstants.PRIVATE_KEY_ALIAS, keyPwd);
			// 初始化签名对象，参数为摘要算法
			Signature signature = Signature.getInstance(PayFor99BillConstants.ENCRYPT_ARITHMETIC);
			// 使用私钥进行签名
			signature.initSign(priK);
			// 对数据进行签名
			signature.update(signMsg.getBytes());
			// 对签名信息进行Base64转码
			sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
			base64 = encoder.encode(signature.sign());
			
		} catch(FileNotFoundException e){
			System.out.println("密钥库文件找不到");
			throw new RuntimeException("密钥库文件找不到");
		}catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("签名过程发生异常");
		}
		return base64;
	}

	
	
	
	public boolean enCodeByCer( String val, String signMsg) {
		boolean flag = false;
		try {
			//获得证书文件
			String certPath = PayFor99BillConstants._99BILL_PUBLIC_CERT_PATH;
			if(certPath.startsWith("file://")){
				certPath = certPath.substring(7);
			}
			if(certPath.startsWith("file:")){
				certPath = certPath.substring(5);
			}
			InputStream inStream = new FileInputStream(certPath);
			
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			// 获取公钥证书
			X509Certificate cert = (X509Certificate) cf.generateCertificate(inStream);
			//获得公钥
			PublicKey pk = cert.getPublicKey();
			// 初始化签名对象，参数为摘要算法
			Signature signature = Signature.getInstance(PayFor99BillConstants.ENCRYPT_ARITHMETIC);
			//使用公钥进行签名
			signature.initVerify(pk);
			// 对数据进行签名
			signature.update(val.getBytes());
			
			//解码
			/*
			 * 对获取到的快钱支付信息签名进行Base64的解码
			 */
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
//			System.out.println("快钱签名信息经Base64解码之后信息为:\n"+new String(decoder.decodeBuffer(signMsg)));
			// 验签
			flag = signature.verify(decoder.decodeBuffer(signMsg));
//			System.out.println(flag);
		} catch (Exception e) {
			System.out.println("验签过程发生异常");
			e.printStackTrace();
			throw new RuntimeException("验签过程发生异常");
		} 
		return flag;
	}

	public static void main(String[] args){
		Pkipair test = new Pkipair();
		String msg = "good morning";
		String signMsg = test.signMsg(msg);
		System.out.println("Sign Message:"+signMsg);
		boolean flag = test.enCodeByCer(msg, signMsg);
		System.out.println("验签结果为:"+flag);
	}
	
	
	public static void main_(String[] args) throws IOException{
		
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

		String str = "hello word !";
		
		String encode_str = encoder.encode(str.getBytes());
		System.out.println("Encode String:"+encode_str);
		
		byte[] decode_strArray = decoder.decodeBuffer(encode_str);
		System.out.println("Decode String:"+new String(decode_strArray));
	
		
	}
	
	
}
