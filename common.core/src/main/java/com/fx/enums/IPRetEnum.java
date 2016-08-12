package com.fx.enums;

/**
 * @author zhangpj
 * @ClassName: IsDelEnum
 * @Description: 是否删除的通用枚举
 * @date 2013-12-2 下午11:24:00
 */
public enum IPRetEnum {
    SUCCESS(1),
    INNERIP(-1),
    ILLEGAL_IP(-2),
    NOIP(-3);
    int value;

    IPRetEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static IPRetEnum valueOf(int value) {
        if (value == 1) {
            return SUCCESS;
        } else if (value == -1) {
            return INNERIP;
        } else if (value == -2) {
            return ILLEGAL_IP;
        }else if (value == -3) {
            return NOIP;
        }
        return null;
    }
    }
