package com.fx.enums;

/**
 * @author zhangpj
 * @ClassName: IsDelEnum
 * @Description: 是否删除的通用枚举
 * @date 2013-12-2 下午11:24:00
 */
public enum IPStatusEnum {
    INIT(0),
    SUCCESS(1),
    ERROR(2);
    int value;

    IPStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static IPStatusEnum valueOf(int value) {
        if (value == 0) {
            return INIT;
        } else if (value == 1) {
            return SUCCESS;
        } else if (value == 2) {
            return ERROR;
        }
        return null;
    }
    }
