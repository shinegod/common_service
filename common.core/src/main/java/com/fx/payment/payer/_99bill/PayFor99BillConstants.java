package com.fx.payment.payer._99bill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import com.fx.payment.util.PayConstants;
import com.fx.util.PayerInfoProperties;
import com.fx.util.XmlUtils;


/**
* @ClassName: PayFor99BillConstants
* @Description: 快钱支付时一些常量
* @author zhangpeijin
* @date 2011-12-23 下午03:43:15
*/
public class PayFor99BillConstants extends PayConstants {
	
	
	/**
	* @Fields PAY_SUCCESS_URL_FOR_BGURL : 使用bgUrl接收信息时，支付正确时跳转到的页面
	*/
	public static final String PAY_SUCCESS_URL_FOR_BGURL = PayerInfoProperties.getProperty("rencaiSiteUrlB")+"/servlet/Pay?action=queryPayInfo";	
	/**
	* @Fields PAY_FAIL_URL_FOR_BGURL : 使用bgUrl接收信息时，支付失败时跳转到的页面
	*/
	public static final String PAY_FAIL_URL_FOR_BGURL = PayerInfoProperties.getProperty("rencaiSiteUrlB")+"/servlet/Pay?action=queryPayInfo";
	
	/**
	* @Fields MY_PRIVATE_KEY_PATH : 私钥的存放地址，也就是pfx密钥库的存放地址
	*/
	public static final String PRIVATE_PRIVATE_KEY_PATH = PayerInfoProperties.getProperty("PRIVATE_PRIVATE_KEY_PATH");
	
	/**
	* @Fields MY_KEY_STORE_PASSWORD : 密钥库的访问密码
	*/
	public static final String PRIVATE_KEY_STORE_PASSWORD = PayerInfoProperties.getProperty("PRIVATE_KEY_STORE_PASSWORD");

	/**
	* @Fields MY_KEY_ALIAS : 私钥的别名，主要用于java语言进行签名时使用
	*/
	public static final String PRIVATE_KEY_ALIAS = PayerInfoProperties.getProperty("PRIVATE_KEY_ALIAS");
	
	/**
	* @Fields _99BILL_PUBLIC_CERT_PATH : 快钱公钥证书的存放路径
	*/
	public static final String _99BILL_PUBLIC_CERT_PATH = PayerInfoProperties.getProperty("_99BILL_PUBLIC_CERT_PATH");
	
	/**
	* @Fields ENCRYPT_ARITHMETIC : 摘要算法
	*/
	public static final String ENCRYPT_ARITHMETIC = "SHA1withRSA";
	
	public static final String GET_99BILL_ORDERTIMESTAMP = "https://www.99bill.com/gateway/getOrderTimestamp.htm";
	
	/**
	 * 快钱支付网关地址
	* @ClassName: PAY_ACTION_URL
	* @author zhangpeijin
	* @date 2011-12-23 下午02:50:02
	* 
	*/
	public enum PAY_ACTION_URL{
		/**
		* @Fields DEFAULT_URL : 默认提交地址
		*/
		DEFAULT_URL(PayerInfoProperties.getProperty("DEFAULT_PAY_ACTION_URL")),	
		/**
		* @Fields SECOND_URL : 第二提交地址
		*/
		SECOND_URL("http://www.99bill.com/gateway/recvMerchantInfoAction.htm");
		public String value;
		PAY_ACTION_URL(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	* @Fields MERCHANT_ACCTID : 人民币网关账号
	*/
	public static final String MERCHANT_ACCTID = ((PayerInfoProperties.getProperty("MERCHANT_ACCTID")==null || PayerInfoProperties.getProperty("MERCHANT_ACCTID").equals(""))?"1002153422301":PayerInfoProperties.getProperty("MERCHANT_ACCTID"));
	
	/**
	* @ClassName: INPUT_CHARSET
	* @Description: 编码方式:	1:UTF-8		2:GBK	3:GB2312	默认为1
	* @author zhangpeijin
	* @date 2011-12-23 下午02:52:34
	* 
	*/
	public enum INPUT_CHARSET{
		UTF_8("1"),//UTF-8编码
		GBK("2"),//GBK
		GB2312("3");//GB2312
		public String value;
		INPUT_CHARSET(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	* @Fields PAGE_URL : 接受支付结果的页面地址
	*/
	public static final String PAGE_URL = PayerInfoProperties.getProperty("siteUrl")+"/_99bill/payaction";
	
	/**
	* @Fields BG_URL : 服务器接受支付结果的后台地址，一般来讲，推荐使用这个，因为商户后台需要进行相应处理。
	* 该URL需要是绝对地址，与PageUrl 不能同时为空；当bgUrl 为空时，快钱直接将支付结果Get到PageUrl；当bgUrl 不为空时，按照bgUrl 的方式返回。
	*/
	public static final String BG_URL = PayerInfoProperties.getProperty("siteUrl")+"/_99bill/payaction";
//	public static final String BG_URL = "";
	
	/**
	* @Fields VERSION : 网关版本，固定值v2.0
	*/
	public static final String VERSION="v2.0";
	
	/**
	* @Fields LANGUAGE : 语言种类，固定为1，代表中文
	*/
	public static final String LANGUAGE="1";
	
	/**
	* @Fields SIGN_TYPE : 签名类型。固定为4，代表DSA或者RSA签名
	*/
	public static final String SIGN_TYPE="4";
	
	/**
	* @Fields PAYER_NAME : 支付人姓名
	*/
	public static final String PAYER_NAME="";
	
	/**
	* @ClassName: PAYER_CONTACT_TYPE
	* @Description: 支付人联系类型	1:电子邮件	2:手机
	* @author zhangpeijin
	* @date 2011-12-23 下午02:56:13
	* 
	*/
	public enum PAYER_CONTACT_TYPE{
		EMAIL("1"),//电子邮件
		TEL("2");//手机
		public String value;
		PAYER_CONTACT_TYPE(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	* @Fields PAYER_CONTACT : 支付人联系方式
	*/
	public static final String PAYER_CONTACT="";
	
	/**
	* @ClassName: PAYER_ID_TYPE
	* @Description: 指定付款人;
	* 数字串;类型固定值0，1，2，3	
	*{0 代表不指定;
	* 1 代表通过商户方ID 指定付款人;
	* 2 代表通过快钱账户指定付款人;
	* 3 代表付款方在商户方的会员编号}
	* 如果为空代表不需要指定
	* @author zhangpeijin
	* @date 2011-12-23 下午03:44:54
	* 
	*/
	public enum PAYER_ID_TYPE{
		NONE("0"),//不指定
		BY_MERCHANT_ID("1"),//通过商户方ID指定付款人
		BY_99BILL_ID("2"),	//通过快钱账户指定付款人
		BY_COMPANY_ID("3");	//付款方在商户方会员ID
		public String value;
		PAYER_ID_TYPE(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	* @Fields PRODUCT_NAME : 商品名称
	*/
	public static final String PRODUCT_NAME="RenCai";
	
	/**
	* @Fields PRODUCT_NUM : 商品数量
	*/
	public static final String PRODUCT_NUM="1";
	
	/**
	* @Fields PRODUCT_ID : 商品代码
	*/
	public static final String PRODUCT_ID="";
	
	/**
	* @Fields PRODUCT_DESC : 商品描述
	*/
	public static final String PRODUCT_DESC="";
	
	/**
	 * 支付方式;
	 * DEFAULT 代表显示快钱各支付方式列表（BANK_CARD、PHONE_BANK、_99BILL_ACCT、OFF_LINE 四种支付方式）；
	 * BANK_CARD 代表只显示银行卡支付方式；
	 * PHONE_BANK 代表只显示电话银行支付方式
	 * _99BILL_ACCT 代表只显示快钱账户支付方式
	 * OFF_LINE 代表只显示线下支付方式；
	 * B2B 代表显示B2B 支付；
	 * CREDIT_CARD 信用卡支付；
	 * PREPAIED_CARD 预付卡支付;
	 * PHONE_VOICE 手机语音支付;
	 * CREDIT_CARD_SHORT_CUT 信用卡快捷支付;CREDIT_CARD_EBANK 信用卡网银支付
	 * *B2B、信用卡支付、手机语音支付、预付卡支付需单独申请，默认不开通;
	 * @ClassName: PAY_TYPE
	* @author zhangpeijin
	* @date 2011-12-23 下午03:31:43
	* 
	*/
	public enum PAY_TYPE{
		DEFAULT("00"),
		BANK_CARD("10"),
		PHONE_BANK("11"),
		_99BILL_ACCT("12"),
		OFF_LINE("13"),
		B2B("14"),
		CREDIT_CARD("15"),
		CREDIT_CARD_SHORT_CUT("15-1"),
		CREDIT_CARD_EBANK("15-2"),
		PREPAIED_CARD("17"),
		PHONE_VOICE("19");
		public String value;
		PAY_TYPE(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	 * 同一定单禁止重复提交标志
	 * 固定选择值: 1、0
	 * 1 代表同一订单号只允许提交1 次;0表示同一订单号在没有支付成功的前提下可重复提交多次；
	 * 默认为0
	 * 建议实物购物车结算类商户采用0；虚拟产品类商户采用1；
	* @ClassName: REDO_FLAG
	* @author zhangpeijin
	* @date 2011-12-23 下午03:38:56
	* 
	*/
	public enum REDO_FLAG{
		/**
		* @Fields ENABLE : 可以多次提交
		*/
		ENABLE("0"),
		/**
		* @Fields DISABLE : 只能提交一次
		*/
		DISABLE("1");
		public String value;
		REDO_FLAG(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		};
	}
	
	/**
	* @Fields PID : 快钱合作伙伴的帐户号
	*/
	public static final String PID="";
	
	public enum PAY_RESULT{
		SUCCESS("10");
		String value;
		PAY_RESULT(String value_){
			this.value = value_;
		}
		public String value(){
			return this.value;
		}
	}
	
	/**
	* @Description: 获取快钱交易时间戳，主要用于交易时间验证，如果时间戳时间与订单提交时间大于30秒，
	* 则快钱主动拦截此交易，以防止商户的订单报文被非法盗用
	* @param @return
	* @param @throws HttpException
	* @param @throws IOException
	* @return String
	* @throws
	* @author zhangpeijin
	* @date 2011-12-29 下午02:02:12
	*/
	public static String get99BillOrderTimestamp() {
		StringBuffer response = new StringBuffer(); 
        HttpMethod method = new GetMethod(GET_99BILL_ORDERTIMESTAMP); 
        try {
        	HttpClient client = new HttpClient(); 
			client.executeMethod(method);
			if (method.getStatusCode() == HttpStatus.SC_OK) { 
				BufferedReader reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "UTF-8")); 
				String line; 
				while ((line = reader.readLine()) != null) { 
					response.append(line).append(System.getProperty("line.separator"));
				} 
				reader.close(); 
			} 
			method.releaseConnection();
		} catch (HttpException e) {
			throw new RuntimeException("获取快钱时间戳时连接失败");
		} catch (IOException e) {
			throw new RuntimeException("解析返回的时间戳数据失败");
		} 
        return XmlUtils.getTextForElement(response.toString(), "timestamp");
	}

}
