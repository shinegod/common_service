package com.fx.gopay.util;

import com.fx.gopay.bean.GoPayBean;
import com.fx.util.CodeUtil;
import com.fx.util.HttpClientUtil;

import java.security.NoSuchAlgorithmException;


/**
 * Created by Administrator on 2015/11/30.
 */
public class GopayUtil {

    //提交订单参数加密
    public static String submitSign(GoPayBean goPayBean) throws NoSuchAlgorithmException {
        String sign = "version=["+goPayBean.getVersion()+"]tranCode=["+goPayBean.getTranCode()+"]merchantID=["+goPayBean.getMerchantID()+"]" +
                "merOrderNum=["+goPayBean.getMerOrderNum()+"]tranAmt=["+goPayBean.getTranAmt()+"]feeAmt=[0]" +
                "tranDateTime=["+goPayBean.getTranDateTime()+"]frontMerUrl=["+goPayBean.getFrontMerUrl()+"]" +
                "backgroundMerUrl=["+goPayBean.getBackgroundMerUrl()+"]orderId=[]" +
                "gopayOutOrderId=[]tranIP=["+goPayBean.getTranIP()+"]respCode=[]" +
                "gopayServerTime=["+goPayBean.getGopayServerTime()+"]VerficationCode=["+goPayBean.getVerficationCode()+"]";
        sign = CodeUtil.md5(sign);
        return sign;
    }

    //接收订单参数加密
    public static String receiveSign(GoPayBean goPayBean) throws NoSuchAlgorithmException{
        String sign = "version=["+goPayBean.getVersion()+"]tranCode=["+goPayBean.getTranCode()+"]merchantID=["+goPayBean.getMerchantID()+"]" +
                "merOrderNum=["+goPayBean.getMerOrderNum()+"]tranAmt=["+goPayBean.getTranAmt()+"]feeAmt=["+goPayBean.getFeeAmt()+"]" +
                "tranDateTime=["+goPayBean.getTranDateTime()+"]frontMerUrl=["+goPayBean.getFrontMerUrl()+"]" +
                "backgroundMerUrl=["+goPayBean.getBackgroundMerUrl()+"]orderId=["+goPayBean.getOrderId()+"]" +
                "gopayOutOrderId=["+goPayBean.getGopayOutOrderId()+"]tranIP=["+goPayBean.getTranIP()+"]respCode=["+goPayBean.getRespCode()+"]" +
                "gopayServerTime=[]VerficationCode=["+goPayBean.getVerficationCode()+"]";
        sign = CodeUtil.md5(sign);
        return sign;
    }

}
