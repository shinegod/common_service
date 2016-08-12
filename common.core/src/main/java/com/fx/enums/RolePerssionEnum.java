package com.fx.enums;

/**
 * Created by Administrator on 2016/5/5.
 */
public enum RolePerssionEnum {

    PERSON_INNER("p1612","查看自己及以下佣金规则"), //usr:innercommission:see:me
    ALL_INNER("p1545","查看全部佣金规则"), //usr:innercommission:see
    ALL_IB("p1549","查看全部佣金规则"), //usr:extercommission:see
    PERSON_IB("p1613","查看自己及以下佣金规则");//usr:extercommission:see:me

    String value;
    String text;
    RolePerssionEnum(String value, String text){
        this.value = value;
        this.text = text;
    }

    public String getValue(){
        return this.value;
    }

    public String getText(){
        return this.text;
    }

    public static RolePerssionEnum toValue(String value){
        for(RolePerssionEnum eValue : RolePerssionEnum.values()){
            if(eValue.getValue()==value){
                return eValue;
            }
        }
        return null;
    }

}

