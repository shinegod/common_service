package com.fx.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 9/1/2016.
 */
public class RequestUtil {

    public static Map<String, Object> getUrlParams(String param) {
        Map<String, Object> map = new HashMap<>();
        if ("".equals(param) || null == param) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }

}
