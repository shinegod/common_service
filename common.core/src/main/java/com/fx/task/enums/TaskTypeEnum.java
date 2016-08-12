package com.fx.task.enums;

public enum TaskTypeEnum {

    ACCOUNT_OPENING("p1512", "ACCOUNT_OPENING"),
    ADDITIONAL_ACCOUNT("p1507", "ADDITIONAL_ACCOUNT"),
    Bank_Deposit("p1508", "DEPOSIT_BANK"),
    Atm_Deposit("P1515", "DEPOSIT_ATM"),
    CHANGE_LEVERAGE("p1509", "LEVERAGE"),
    WITHDRAW_COMMISSION("p1510", "WITHDRAW_COMMISSION"),
    WITHDRAW_TRADE("p1510", "WITHDRAW_TRADE"),
    TRANSFER_TRADE("p1513", "TRANSFER_TRADE"),
    TRANSFER_COMMISSION("p1513", "TRANSFER_COMMISSION"),
    BING_MT4("p1557", "BING_MT4"),
    DEPOSIT_NAB("p1636", "DEPOSIT_NAB");

    String value;
    String text;

    TaskTypeEnum(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }

    public static TaskTypeEnum toValue(String value) {
        for (TaskTypeEnum eValue : TaskTypeEnum.values()) {
            if (eValue.getValue() == value) {
                return eValue;
            }
        }
        return null;
    }

}



