package com.fx.util;


import com.google.common.collect.Lists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by thinker on 15/12/11.
 */
public class CSVUtils {

    public static final String CVS_SPLIT_CHAR = ",";

    private final static byte commonCsvHead[] = { (byte) 0xEF, (byte) 0xBB,
            (byte) 0xBF };
    /**
     * 导出
     *
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;

        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            bw.write(new String(commonCsvHead));
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSucess;
    }

    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file){
        List<String> dataList=new ArrayList<String>();

        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br!=null){
                try {
                    br.close();
                    br=null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return dataList;
    }

    public static String getTitle(List<String> titleList) {
        StringBuffer title = new StringBuffer();
        for (String tt : titleList) {
            title.append(CVS_SPLIT_CHAR).append(tt);
        }
        return title.length() > 0 ? title.substring(1) : title.toString();
    }

    public static List<String> getDataList(List<Map<String, String>> dataList, List<String> titleList) {
        List<String> datas = Lists.newArrayList();
        for (Map<String, String> map : dataList) {
            datas.add(getData(map, titleList));
        }
        return datas;
    }

    public static List<String> getDataList(List<Map<String, String>> dataList) {
        List<String> datas = Lists.newArrayList();
        for (Map<String, String> map : dataList) {
            datas.add(getData(map));
        }
        return datas;
    }

    private static String getData(Map<String, String> map, List<String> titleList) {
        StringBuffer row = new StringBuffer();;
        for (String key : titleList) {
            row.append(CVS_SPLIT_CHAR).append("\"").append(StringUtils.defaultString(map.get(key), "")).append("\"");
        }
        return row.length() > 0 ? row.substring(1) : row.toString();
    }

    private static String getData(Map<String, String> map) {
        StringBuffer row = new StringBuffer();
        for (Map.Entry<String, String> m : map.entrySet()) {
            row.append(CVS_SPLIT_CHAR).append(StringUtils.defaultString(m.getValue(),""));
        }
        return row.length() > 0 ? row.substring(1) : row.toString();
    }
}
