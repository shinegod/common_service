package com.fx.payment.util;

public enum UserMT4StatusEnum {
    MONEY_NOBACK(1, "非返金"),//交易帐号
    MONEY_BACK(2, "返金"),
    IB_COMMISSION(3, "IB佣金"),//返佣账号
    EXPERIENCE_ACCOUNT(4, "体验账户");
    int value;
    String text;

    UserMT4StatusEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }

    public static UserMT4StatusEnum valueOf(int value) {
        if (value == 1) {
            return MONEY_NOBACK;
        } else if (value == 2) {
            return MONEY_BACK;
        } else if (value == 3) {
            return IB_COMMISSION;
        } else if (value == 4) {
            return EXPERIENCE_ACCOUNT;
        }
        return null;
    }
}
