package com.fx.util;

import java.io.*;
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

    public static String getPostParams(InputStream in) throws IOException {
        StringBuffer info = new StringBuffer(0);
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] buffer = new byte[1024];
        int iRead;
        while ((iRead = buf.read(buffer)) != -1) {
            info.append(new String(buffer, 0, iRead, "UTF-8"));
        }
        return info.toString();
    }

}
