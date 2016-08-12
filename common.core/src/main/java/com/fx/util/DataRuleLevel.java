package com.fx.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by bei2love@gmail.com on 15/5/25.
 */
public class DataRuleLevel implements java.io.Serializable{



    public enum  Level {
        All_DATA(1), ALL_LEVEL(2), UNDER_LEVEL(3);

        private int value;

        Level(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }


    }

    /**
     * 构造:传入menuid和权限的拼接字符串，拼接字符为！
     * @parammix
     */
    public DataRuleLevel(){

    }
    public DataRuleLevel(String mix){
        if(mix != null){
            this.menuid = StringUtils.substring(mix, 0, mix.indexOf("!"));
            this.level = getLevelByStringValue(StringUtils.substring(mix, mix.indexOf("!") + 1, mix.length()));
        }
    }

    private Level level;

    private String menuid;

    public static Level getLevelByStringValue(String value){
        if("ALL_DATA".equals(value)){
            return Level.All_DATA;
        }else if("ALL_LEVEL".equals(value)){
            return Level.ALL_LEVEL;
        }else if("UNDER_LEVEL".equals(value)){
            return Level.UNDER_LEVEL;
        }else if("DIRECT_LEVEL".equals(value)){        //  后续还要同意修改
            return Level.UNDER_LEVEL;
        }
        //默认有全部权限
        return Level.All_DATA;
    }

    public void setLevelByString(String levelString) {
        Level level = getLevelByStringValue(levelString);
        setLevel(level);
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        if(this.level == null){
            this.level = level;
        }else if(this.level.getValue() < level.getValue()){
            this.level = level;
        }
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
}
