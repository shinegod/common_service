package com.fx.crm.transfer;

import com.fx.mt4TradeRecord.model.Mt4Users;
import com.fx.util.StringUtils;

import java.math.BigDecimal;

/**
 * Created by thinker on 16/2/19.
 */
public class TransferUtils {

    public static final String GROUP_SPLIT_CHAR = "_";

    /**
     * 通过mt4组获取币种;
     * 要求mt4组必须为以下格式: M_VFX_USD , S_VFX_USD
     * @param mt4Group
     * @return
     */
    public static String getCurrencyByGroup(String mt4Group) {
        if (StringUtils.isBlank(mt4Group)) {
            throw new RuntimeException("mt4 group is null");
        }
        if (mt4Group.indexOf(GROUP_SPLIT_CHAR) < 0) {
            throw new RuntimeException("mt4 group not normal setting");
        }
        return mt4Group.substring(mt4Group.lastIndexOf(GROUP_SPLIT_CHAR) + 1);
    }

    /**
     * 根据可用余额的逻辑获取账户的可用余额
     * @param mt4Users
     * @return
     */
    public static BigDecimal getCurrentBalance(Mt4Users mt4Users) {
        BigDecimal b = new BigDecimal(Double.toString(mt4Users.getEquity())).subtract(new BigDecimal(Double.toString(mt4Users.getMargin()))).subtract(new BigDecimal(Double.toString(mt4Users.getCredit())));
        return  b;
    }


}
