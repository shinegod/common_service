package com.fx.shiro.authz;

import com.fx.util.DataRuleLevel;

import java.util.Map;

/**
 * Created by bei2love@gmail.com on 15/5/21.
 */
public class SimpleAuthorizationInfo extends org.apache.shiro.authz.SimpleAuthorizationInfo {

    /**
     * 当前用户的数据权限
     * sqlid为key
     */
    protected Map<String, DataRuleLevel> dataRuleDefines = null;

    public Map<String, DataRuleLevel> getDataRuleDefines() {
        return dataRuleDefines;
    }

    public void setDataRuleDefines(Map<String, DataRuleLevel> dataRuleDefines) {
        this.dataRuleDefines = dataRuleDefines;
    }
}
