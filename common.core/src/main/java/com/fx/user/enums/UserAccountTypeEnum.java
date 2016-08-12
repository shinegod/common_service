package com.fx.user.enums;


public enum UserAccountTypeEnum {
    DEMO_ACCOUNT(1, "模拟账户"),
    PERSONAL_ACCOUNT(2, "真实账户"),
    COMPANY_ACCOUNT(3, "公司账户"),
    VIP_ACCOUNT(4, "vip账户"),
    IB_ACCOUNT(5, "IB账户"),
    INTERNAL_ACCOUNT(6, "内部账户"),   //内部账户 和admin对应
    EXPERIENCE_ACCOUNT(7,"体验账户"),
    LEADS_ACCOUNT(8, "Leads");

    int value;
    String text;

    UserAccountTypeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }

    public String getVal(int value) {

        if (value == 1) {
            return "DEMO_ACCOUNT";
        } else if (value == 2) {
            return "PERSONAL_ACCOUNT";
        } else if (value == 3) {
            return "COMPANY_ACCOUNT";
        } else if (value == 4) {
            return "VIP_ACCOUNT";
        } else if (value == 5) {
            return "IB_ACCOUNT";
        } else if (value == 6) {
            return "INTERNAL_ACCOUNT";
        } else if (value == 7) {
            return "EXPERIENCE_ACCOUNT";
        } else if (value == 8) {
            return "LEADS_ACCOUNT";
        }
        return null;
    }

    public static UserAccountTypeEnum valueOf(int value) {
        if (value == 1) {
            return DEMO_ACCOUNT;
        } else if (value == 2) {
            return PERSONAL_ACCOUNT;
        } else if (value == 3) {
            return COMPANY_ACCOUNT;
        } else if (value == 4) {
            return VIP_ACCOUNT;
        } else if (value == 5) {
            return IB_ACCOUNT;
        } else if (value == 6) {
            return INTERNAL_ACCOUNT;
        } else if (value == 7) {
            return EXPERIENCE_ACCOUNT;
        } else if (value == 8) {
            return LEADS_ACCOUNT;
        }
        return null;
    }
}
