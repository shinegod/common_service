package com.fx.payment.payer._99bill;

import java.net.URLEncoder;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.fx.payment.model.TradeDetail;
import com.fx.payment.service.impl.PayerServiceImpl;
import com.fx.util.DateUtil;

@Service("_99BillService")
public class _99BillPayerServiceImpl extends PayerServiceImpl {

	@Override
	protected String getAddMoneyPayerUrl(TradeDetail tradeDetail) {
		String payUrl = get99BillActionURL(tradeDetail);
		return payUrl;
	}

	/**
	 * 鏍规嵁璁㈠崟瀵硅薄锛岀敓鎴愭敮浠樻椂鐨勭鍚嶅瓧绗︿覆锛岀劧鍚庤繘琛岀紪绛撅紝杩斿洖鍙戦�鍒板揩閽辩殑URL鍦板潃
	 * 
	 * @param @param order 璁㈠崟瀵硅薄
	 * @param @return
	 * @return String
	 * @throws
	 * @author zhangpeijin
	 * @date 2011-12-23 涓嬪崍02:36:23
	 */
	private String get99BillActionURL(TradeDetail tradeDetail) {

		// 浜烘皯甯佺綉鍏宠处鍙�
		String merchantAcctId = PayFor99BillConstants.MERCHANT_ACCTID;

		// 缂栫爜鏂瑰紡: 1:UTF-8 2:GBK 3:GB2312 榛樿涓�
		String inputCharset = PayFor99BillConstants.INPUT_CHARSET.UTF_8.value();
		// 鎺ュ彈鏀粯缁撴灉鐨勯〉闈㈠湴鍧�
		String pageUrl = PayFor99BillConstants.PAGE_URL;

		// 鏈嶅姟鍣ㄦ帴鍙楁敮浠樼粨鏋滅殑鍚庡彴鍦板潃锛屼竴鑸潵璁诧紝鎺ㄨ崘浣跨敤杩欎釜锛屽洜涓哄晢鎴峰悗鍙伴渶瑕佽繘琛岀浉搴斿鐞�
		String bgUrl = PayFor99BillConstants.BG_URL;

		// 缃戝叧鐗堟湰
		String version = PayFor99BillConstants.VERSION;
		// 璇█绉嶇被
		String language = PayFor99BillConstants.LANGUAGE;
		// 绛惧悕绫诲瀷
		String signType = PayFor99BillConstants.SIGN_TYPE;
		// 鏀粯浜哄鍚�
		String payerName = PayFor99BillConstants.PAYER_NAME;

		// 鏀粯浜鸿仈绯荤被鍨�1锛氱數瀛愰偖浠�2锛氭墜鏈�
		String payerContactType = PayFor99BillConstants.PAYER_CONTACT_TYPE.EMAIL
				.value();

		// 鏀粯浜鸿仈绯绘柟寮�
		String payerContact = "";

		// 鎸囧畾浠樻浜虹被鍨�
		String payerIdType = PayFor99BillConstants.PAYER_ID_TYPE.NONE.value();
		// 鎸囧畾浠樻浜�
		String payerId = String.valueOf(tradeDetail.getUid());

		// 鍟嗘埛瀹氬崟鍙�
		String orderId = String.valueOf(tradeDetail.getId());
		// 瀹氬崟閲戦
		DecimalFormat df = new DecimalFormat("0");
		String orderAmount = df
				.format(tradeDetail.getOperMoney().movePointRight(2));

		// 瀹氬崟鎻愪氦鏃堕棿
		String orderTime = DateUtil.formatTime2yyyyMMddHHmmss(tradeDetail.getCreateTime());
		// 蹇挶鏃堕棿鎴�
		// String orderTimestamp =
		// PayFor99BillConstants.get99BillOrderTimestamp();
		String orderTimestamp = ""; // 鐜板湪鏈烘埧涓棤娉曡闂揩閽辨椂闂存埑鐨勯摼鎺ワ紝鎵�互鍏堟殏鏃朵笉浣跨敤鏃堕棿鎴�
		// 鍟嗗搧鍚嶇О
		String productName = PayFor99BillConstants.PRODUCT_NAME;
		// 鍟嗗搧鏁伴噺
		String productNum = PayFor99BillConstants.PRODUCT_NUM;
		// 鍟嗗搧浠ｇ爜
		String productId = PayFor99BillConstants.PRODUCT_ID;
		// 鍟嗗搧鎻忚堪
		String productDesc = PayFor99BillConstants.PRODUCT_DESC;

		// 鎵╁睍瀛楁1
		String ext1 = PayFor99BillConstants.AFTER_RETURN_JUMP_TO_URL;
		// 鎵╁睍鑷2
		String ext2 = "";
		// 鏀粯鏂瑰紡
		String payType = PayFor99BillConstants.PAY_TYPE.BANK_CARD.value();
		// 閾惰浠ｇ爜锛屼娇鐢ㄩ摱琛岀洿鑱旀椂璇ュ�涓嶈兘涓虹┖
		String bankId = tradeDetail.getBankName();
		// 鍚屼竴瀹氬崟绂佹閲嶅鎻愪氦鏍囧織銆傝櫄鎷熶骇鍝佽涓�锛岀姝㈤噸澶嶆彁浜�
		String redoFlag = PayFor99BillConstants.REDO_FLAG.DISABLE.value();
		// 蹇挶鍚堜綔浼欎即鐨勫笎鎴峰彿
		String pid = "";
		// signMsg 绛惧悕瀛楃涓�涓嶅彲绌�
		// 浠ヤ笂鎵�湁闈炵┖鍙傛暟鍙婂叾鍊间笌瀵嗛挜缁勫悎锛岀粡MD5鍔犲瘑骞剁敓鎴愬苟杞寲涓哄ぇ鍐欑殑32浣嶅瓧绗︿覆
		// 瀵逛簬鎵�湁鍊间笉涓虹┖鐨勫弬鏁板強瀵瑰簲鍊硷紝鎸夌収濡備笂椤哄簭鍙婂涓嬭鍒欑粍鎴愬瓧绗︿覆
		// MD5鏂瑰紡锛氬弬鏁�={鍙傛暟1}&鍙傛暟2={鍙傛暟2}&鈥︹�&鍙傛暟n={鍙傛暟n}&key={key}琛岀劧鍚庤繘琛�2浣嶇畻娉曠殑MD5鍔犲瘑鍚庯紝杞寲涓哄ぇ鍐�

		// 鐢熸垚鍔犲瘑绛惧悕涓�
		String signMsgVal = "";
		signMsgVal = appendParam(signMsgVal, "inputCharset", inputCharset);
		signMsgVal = appendParam(signMsgVal, "pageUrl", pageUrl);
		signMsgVal = appendParam(signMsgVal, "bgUrl", bgUrl);
		signMsgVal = appendParam(signMsgVal, "version", version);
		signMsgVal = appendParam(signMsgVal, "language", language);
		signMsgVal = appendParam(signMsgVal, "signType", signType);
		signMsgVal = appendParam(signMsgVal, "merchantAcctId", merchantAcctId);
		signMsgVal = appendParam(signMsgVal, "payerName", payerName);
		signMsgVal = appendParam(signMsgVal, "payerContactType",
				payerContactType);
		signMsgVal = appendParam(signMsgVal, "payerContact", payerContact);
		signMsgVal = appendParam(signMsgVal, "payerIdType", payerIdType);
		signMsgVal = appendParam(signMsgVal, "payerId", payerId);
		signMsgVal = appendParam(signMsgVal, "orderId", orderId);
		signMsgVal = appendParam(signMsgVal, "orderAmount", orderAmount);
		signMsgVal = appendParam(signMsgVal, "orderTime", orderTime);
		signMsgVal = appendParam(signMsgVal, "orderTimestamp", orderTimestamp);
		signMsgVal = appendParam(signMsgVal, "productName", productName);
		signMsgVal = appendParam(signMsgVal, "productNum", productNum);
		signMsgVal = appendParam(signMsgVal, "productId", productId);
		signMsgVal = appendParam(signMsgVal, "productDesc", productDesc);
		signMsgVal = appendParam(signMsgVal, "ext1", ext1);
		signMsgVal = appendParam(signMsgVal, "ext2", ext2);
		signMsgVal = appendParam(signMsgVal, "payType", payType);
		signMsgVal = appendParam(signMsgVal, "bankId", bankId);
		signMsgVal = appendParam(signMsgVal, "redoFlag", redoFlag);
		signMsgVal = appendParam(signMsgVal, "pid", pid);
		Pkipair pki = new Pkipair();
		String signMsg = pki.signMsg(signMsgVal);
		String signMsgUrl = URLEncoder.encode(signMsg);
		String action_url = PayFor99BillConstants.PAY_ACTION_URL.DEFAULT_URL
				.value();
		if (null == action_url || "".equals(action_url.trim())) {
			action_url = PayFor99BillConstants.PAY_ACTION_URL.SECOND_URL
					.value();
		}
		if (null == action_url || "".equals(action_url.trim())) {
			throw new RuntimeException("鑾峰彇鍒板揩閽辨敮浠樼綉鍏冲湴鍧�负绌猴紝璇锋煡鐪嬪叿浣撻厤");
		}
		String actionURL = action_url + "?" + signMsgVal + "&signMsg="
				+ signMsgUrl;
		return actionURL;
	}

	/**
	 * 
	 * @param @param returns 宸叉湁鐨勫緟绛惧悕淇℃伅
	 * @param @param paramId 鍙傛暟鍚�
	 * @param @param paramValue 鍙傛暟鍊�
	 * @param @return 杩斿洖娣诲姞杩囪鍙傛暟鐨勫緟绛惧悕淇℃伅
	 * @return String
	 * @throws
	 * @author zhangpeijin
	 * @date 2011-12-23 涓嬪崍02:21:28
	 */
	private String appendParam(String returns, String paramId, String paramValue) {
		if (returns != "") {
			if (paramValue != "") {
				returns += "&" + paramId + "=" + paramValue;
			}

		} else {
			if (paramValue != "") {
				returns = paramId + "=" + paramValue;
			}
		}

		return returns;
	}

}
