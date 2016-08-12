package com.fx.util;

import java.io.*;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Created by bei2love@gmail.com on 15/5/15.
 */
public class I18N {

    public static String CONST_LANG_CODE_CHINESE = "zh_CN";
    public static String CONST_LANG_CODE_ENGLISH = "en_US";
    public static String CONST_LANG_CODE_CHINESE_TW = "zh_TW";

    private static Map<String, Map<String, String>> langCache = new HashMap<String, Map<String, String>>();

    /**
     * 获取多语言的资源
     *
     */
    public static Map<String, Map<String, String>> get() {
        return langCache;
    }

    /**
     * 加载多语言的资源
     *
     */
    public static void load() {
        if (langCache.size() == 0) {
            langCache.putAll(I18N.loadLangResource());
        }
    }

    /**
     * 重置多语言的资源
     */
    public static void reset() {
        langCache.clear();
        load();
    }

    /**
     * 根据语言代码获取资源
     *
     * @param strLangCode
     * @return
     */
    public static Map<String, String> getLangByCode(String strLangCode) {
        if (langCache.containsKey(strLangCode)) {
            return langCache.get(strLangCode);
        } else {
            return new HashMap<String, String>();
        }
    }

    /**
     * 获取多语言资源
     *
     * @param strLangCode
     * @param strLangKey
     * @return
     */
    public static String getLangValue(String strLangCode, String strLangKey) {
        if (strLangCode == null || strLangKey == null) {
            return "";
        }
        if (getLangByCode(strLangCode).containsKey(strLangKey)) {
            return getLangByCode(strLangCode).get(strLangKey).toString();
        } else {
            return "";
        }
    }

    /**
     * 获取多语言资源
     *
     * @param local
     * @param strLangKey
     * @return
     */
    public static String getLangValue(Locale local, String strLangKey) {
        if (local == null || strLangKey == null) {
            return "";
        }
        return getLangValue(local.toString(), strLangKey);
    }

    /**
     * 获取多语言资源
     *
     * @param local
     * @param strLangKey
     * @param strLangDefaultValue
     * @return
     */
    public static String getLangValue(Locale local, String strLangKey,
                                      String strLangDefaultValue) {
        String val = getLangValue(local, strLangKey);
        if (val == null || val.length() == 0) {
            return strLangDefaultValue;
        } else {
            return val;
        }
    }

    /**
     * 获取多语言资源，支持{0}、{1}、{N}参数化
     * @param strLangKey
     * @param args
     * @param locale
     * @return
     */
    public static String getLangValue(String strLangKey, Object[] args, Locale locale) {
        String result = getLangValue(locale, strLangKey);
        if (args != null && args.length > 0)
            result = MessageFormat.format(result, args);
        return result;
    }

    /**
     * 加载多语言的资源
     *
     * @return Map<String, Object>,key为语言代码，value为对应语言的Map
     */
    private static Map<String, Map<String, String>> loadLangResource() {
        Map<String, Map<String, String>> mapLangReturn = new HashMap<String, Map<String, String>>();
        try {
            String[] arraylangCode = Config.getLangCodeSupport().split(";");
            for (String strTempLangCode : arraylangCode) {
                mapLangReturn.put(strTempLangCode,
                        readLangFileJava(strTempLangCode));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapLangReturn;
    }

    /**
     * 读取资源文件，前后端两套多语言，建议尽量用服务器端的多语言
     *
     * @param strLangCode
     * @return
     * @throws IOException
     */
    @Deprecated
    private static String readLangFileJS(final String strLangCode)
            throws IOException {
        String sRootClassPath = I18N.class.getClassLoader().getResource("/")
                .getPath();
        sRootClassPath = sRootClassPath.substring(0, sRootClassPath.length()
                - "/WEB-INF/classes/".length());
        String sLangFileJS = sRootClassPath + "/static/lang/"
                + strLangCode + ".js";
        String sLangFileJava = sRootClassPath + "/lang/" + "crm_"
                + strLangCode + ".properties";
        File file = new File(sLangFileJS);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new BufferedInputStream(new FileInputStream(file)), "UTF-8"));
        String record = new String();
        StringBuffer sb = new StringBuffer();
        while ((record = br.readLine()) != null) {
            if (record.startsWith("crm.lang = {")) {
                sb.append("{");
            } else if (record.startsWith("};")) {
                sb.append("}");
                break;
            } else {
                sb.append(record);
            }
        }
        br.close();
        return sb.toString();
    }

    /**
     * 读取多语言，默认读取所有的多语言文件（类型_语言代码.properties文件）
     *
     * @param strLangCode
     * @return
     */
    private static Map<String, String> readLangFileJava(final String strLangCode) {
        Map<String, String> lang = new HashMap<String, String>();
        if (strLangCode == null
                || (!CONST_LANG_CODE_CHINESE_TW.equals(strLangCode)
                    && !CONST_LANG_CODE_CHINESE.equals(strLangCode)
                    && !CONST_LANG_CODE_ENGLISH.equals(strLangCode))) {
            return lang;
        }
        String sRootClassPath = null;
        try {
            // 如果路径中有空格则转换成%20,需先调用toURI()
            sRootClassPath = I18N.class.getResource("/").toURI().getPath();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        String sLangFileJava = sRootClassPath + "lang/";
        File dir = new File(sLangFileJava);
        File[] files = dir.listFiles();
        if (files != null)
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isDirectory()
                        && (files[i].getName().contains(strLangCode
                        + ".properties"))) {
                    Properties property = new Properties();
                    try {
                        FileInputStream inputStream = new FileInputStream(
                                files[i].getAbsolutePath());
                        property.load(inputStream);
                        inputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    for (Map.Entry<Object, Object> entry : property.entrySet()) {
                        lang.put(entry.getKey().toString(), entry.getValue()
                                .toString());
                    }
                }
            }
        return lang;
    }

    /**
     * 取配置文件路径
     */
    public static String getPropertyPath(){
        String sRootClassPath = null;
        try {
            // 如果路径中有空格则转换成%20,需先调用toURI()
            sRootClassPath = I18N.class.getResource("/").toURI().getPath();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        String sLangFileJava = sRootClassPath + "lang/";
        return sLangFileJava;
    }
    /**
     * 读取多语言，默认读取所有的多语言文件（类型_语言代码.properties文件）
     *
     * @param strBaseName
     * @param strLangCode
     * @return
     */
    public static Map<String, String> readLangFileJava(final String strBaseName,final String strLangCode) {
        Map<String, String> lang = new HashMap<String, String>();
        if (strLangCode == null
                || (!CONST_LANG_CODE_CHINESE_TW.equals(strLangCode)
                && !CONST_LANG_CODE_CHINESE.equals(strLangCode)
                && !CONST_LANG_CODE_ENGLISH.equals(strLangCode))) {
            return lang;
        }
        String sRootClassPath = null;
        try {
            // 如果路径中有空格则转换成%20,需先调用toURI()
            sRootClassPath = I18N.class.getResource("/").toURI().getPath();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        String sLangFileJava = sRootClassPath + "lang/" +strBaseName+"_"+strLangCode+".properties";
        File file = new File(sLangFileJava);
        if (file != null)
            if (file.exists()) {
                Properties property = new Properties();
                try {
                    FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
                    property.load(inputStream);
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                for (Map.Entry<Object, Object> entry : property.entrySet()) {
                    lang.put(entry.getKey().toString(), entry.getValue().toString());
                }
            }
        return lang;
    }
}
