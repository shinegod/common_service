package com.fx.util;

import org.springframework.util.FileCopyUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Download Excel Util
 */
public class ExcelOperateUtil {


    public static void downloadExcel(InputStream inputStream, HttpServletResponse response, String excelName){
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;" + "filename=" + excelName);
        try {
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
