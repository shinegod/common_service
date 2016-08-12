package com.fx.util;

import com.fx.fastdfs.FastdfsClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Excel util, create excel sheet, cell and style.
 * @param <T> generic class.
 */
public class ExcelUtil<T> {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";

    public static final String EMPTY = "";

    public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
    public static final String PROCESSING = "Processing...";


    public static HSSFCellStyle  getCellStyle(HSSFWorkbook workbook,boolean isHeader){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        if (isHeader) {
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
        }
        return style;
    }

    public static HSSFCellStyle  getCellStyle(HSSFWorkbook workbook,short bg){
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        style.setFillForegroundColor(bg);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return style;
    }

    public  static void generateHeader(HSSFWorkbook workbook,HSSFSheet sheet,String[] headerColumns){
        HSSFCellStyle style = getCellStyle(workbook, true);
        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);
        for(int i=0;i<headerColumns.length;i++){
            Cell cell = row.createCell(i);
            String[] column = headerColumns[i].split("_#_");
            sheet.setColumnWidth(i, Integer.valueOf(column[1]));
            cell.setCellValue(column[0]);
            cell.setCellStyle(style);
        }
    }

    public static InputStream writeExcelToStream(HSSFWorkbook workbook,HSSFSheet sheet) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //数据在bos中
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        return bis;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public   HSSFSheet creatAuditSheet(HSSFWorkbook workbook,String sheetName,
                                          List<T> dataset,String[] headerColumns,String[] fieldColumns) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        HSSFSheet sheet = workbook.createSheet(sheetName);
        //sheet.protectSheet(""); //保护生成Excel文档，设置密码访问.
        //自动对生成的Excel 文档第一行标题栏设置成filter 过滤形式, 方便用户使用
        char[] endChar = Character.toChars( 'A' + (headerColumns.length - 1) );
        String rangeAddress = "A1:" + String.valueOf(endChar) + "1";
        /*sheet.setAutoFilter(CellRangeAddress.valueOf(rangeAddress));
*/
        generateHeader(workbook,sheet,headerColumns);
        HSSFCellStyle style = getCellStyle(workbook,false);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int rowNum = 0;
        for(T t:dataset){
            rowNum++ ;
            Row row = sheet.createRow(rowNum);
            row.setHeightInPoints(25);
            for(int i = 0; i < fieldColumns.length; i++){
                String fieldName = fieldColumns[i] ;

                String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
                try {
                    Class clazz = t.getClass();
                    Method getMethod;
                    getMethod = clazz.getMethod(getMethodName, new Class[]{} );
                    Object value = getMethod.invoke(t, new Object[]{});
                    String cellValue = "";
                    if (value instanceof Date){
                        Date date = (Date)value;
                        cellValue = sd.format(date);
                    }else{
                        cellValue = null != value ? value.toString() : "";
                    }
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(style);
                    cell.setCellValue(cellValue);

                } catch (Exception e) {

                }
            }
        }
        return sheet;
    }
    

    /**
     * 列表数据通过map来取，调用之前判断map是否有数据
     * @param workbook
     * @param sheetName
     * @param maps
     * @param headerColumns
     * @param fieldColumns
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    	
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public  static HSSFSheet creatAuditSheet(HSSFWorkbook workbook,String sheetName,
                                          Map[] maps ,String[] headerColumns,String[] fieldColumns) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        HSSFSheet sheet = workbook.createSheet(sheetName);
        //sheet.protectSheet(""); //保护生成Excel文档，设置密码访问.
        //自动对生成的Excel 文档第一行标题栏设置成filter 过滤形式, 方便用户使用
        char[] endChar = Character.toChars( 'A' + (headerColumns.length - 1) );
        String rangeAddress = "A1:" + String.valueOf(endChar) + "1";
        sheet.setAutoFilter(CellRangeAddress.valueOf(rangeAddress));

        generateHeader(workbook,sheet,headerColumns);
        HSSFCellStyle style = getCellStyle(workbook,false);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int rowNum = 0;
        for(int i=0;i<maps.length;i++) {
        	rowNum++ ;
            Row row = sheet.createRow(rowNum);
            row.setHeightInPoints(25);
        	for(int j = 0; j < fieldColumns.length; j++){
        		 String fieldName = fieldColumns[j] ;
        		 Object value = maps[i].get(fieldName);
        		 System.out.println(value);
        		 String cellValue = "";
                 if (value instanceof Date){
                     Date date = (Date)value;
                     cellValue = sd.format(date);
                 }else{
                     cellValue = null != value ? value.toString() : "";
                 }
                 Cell cell = row.createCell(j);
                 cell.setCellStyle(style);
                 cell.setCellValue(cellValue);
        	}
        }
        return sheet;
    }

    public  static HSSFSheet creatAuditSheet(HSSFWorkbook workbook,String sheetName,
                                            Map<String,List<String>> listMap ) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCellStyle style = getCellStyle(workbook,false);
        HSSFCellStyle styleText = getCellStyle(workbook,false);
        HSSFCellStyle styleHead = getCellStyle(workbook,true);
        HSSFCellStyle styleRed =  getCellStyle(workbook,HSSFColor.RED.index);
        HSSFCellStyle styleYellow = getCellStyle(workbook,HSSFColor.YELLOW.index);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int rowNum = 0;
        Set<Map.Entry<String, List<String>>> entrySet = listMap.entrySet();
        for (Map.Entry<String, List<String>> entry : entrySet) {
            rowNum++ ;
            Row row = sheet.createRow(rowNum);
            row.setHeightInPoints(25);
            if(entry.getKey().contains("HEAD")) {
                style = styleHead;
            }
            if(entry.getKey().contains("LIST_RED")) {
                style = styleRed;
            }
            if(entry.getKey().contains("LIST_YELLOW")) {
                style = styleYellow;
            }
            int col = 0;
            for (String s : entry.getValue()) {
                Cell cell = row.createCell(++col);
                sheet.setColumnWidth(col-1, 6000);
                cell.setCellStyle(style);
                cell.setCellValue(s);
            }
            style = styleText;
        }

        return sheet;
    }


    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024*4];
        int n=0;
        while ( (n=in.read(buffer)) !=-1) {
            out.write(buffer,0,n);
        }
        return out.toByteArray();
    }

    public static String excelToZip(String originalFilename,HSSFWorkbook workbook){
        //写入fds文件系统并返回路径:
        FileOutputStream out = null;
        ZipInputStream zipInputStream = null;
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        String uploadUrl = "";
        try {
            File zipFileTemp = File.createTempFile(originalFilename,".zip");
            File fileTemp = File.createTempFile(originalFilename, ".xls");
            out = new FileOutputStream(fileTemp);
            workbook.write(out);

            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFileTemp));
            ZipEntry entry=new ZipEntry(originalFilename+".xls");
            zos.putNextEntry(entry);
            fis = new FileInputStream(fileTemp);
            bis = new BufferedInputStream(fis, 1024*10);
            byte[] bufs = new byte[1024*10];
            int read = 0;
            while((read=bis.read(bufs, 0, 1024*10)) != -1){
                zos.write(bufs,0,read);
            }
            zos.flush();
            zos.close();
            FileInputStream fileInputStream = new FileInputStream(zipFileTemp);
            uploadUrl = "/"
                    + FastdfsClient.upload_file(fileInputStream,zipFileTemp.length(), "zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
            return uploadUrl;
    }

    /**
     * 读取Excel文件，返回Map集合
     * @param path
     * @return
     * @throws IOException
     */
    public static Map<String,List<Map<String, Object>>> readExcel(String path) throws Exception {
        if (path == null || EMPTY.equals(path)) {
            return null;
        } else {
            String postfix = StringUtils.substring(path,path.lastIndexOf(".")+1);
            if (!EMPTY.equals(postfix)) {
                if (OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
                    throw new Exception("暂不支持低版本office");
//                    return readXls(path);
                } else if (OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
                    return readXlsx(path);
                }
            } else {
                logger.info(path + NOT_EXCEL_FILE);
            }
        }
        return null;
    }


    /**
     * Read the Excel 2010
     *
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public static Map<String,List<Map<String, Object>>> readXlsx(String path) throws IOException {
        logger.debug(PROCESSING + path);
        Map<String,List<Map<String, Object>>> excelData = Maps.newHashMap();
        InputStream is = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        XSSFSheet sheet = null;
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            logger.debug("解析第{}个sheet页数据......", sheetNum);
            String sheetName = workbook.getSheetAt(sheetNum).getSheetName();
            excelData.put(sheetName, getSheetData(workbook.getSheetAt(sheetNum)));
            sheet = null;
        }
        return excelData;
    }

    /**
     * Read the Excel 2010
     *
     * @param inputStream inputStream of the excel file
     * @return
     * @throws IOException
     */
    public static Map<String,List<Map<String, Object>>> readExcel(InputStream inputStream) throws IOException {
        Map<String,List<Map<String, Object>>> excelData = Maps.newHashMap();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = null;
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
            logger.debug("解析第{}个sheet页数据......", sheetNum);
            String sheetName = workbook.getSheetAt(sheetNum).getSheetName();
            excelData.put(sheetName, getSheetData(workbook.getSheetAt(sheetNum)));
            sheet = null;
        }
        return excelData;
    }

    /**
     * 获取sheet页面数据
     * @param sheet
     * @return
     */
    private static List<Map<String, Object>> getSheetData(XSSFSheet sheet) {

        List<Map<String, Object>> list = Lists.newArrayList();

        if (sheet == null) {
            return null;
        }
        // 获取标题行
        String[] title = getRowData(sheet.getRow(sheet.getFirstRowNum()));
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++){
            try {
                list.add(getDataRow(title, sheet.getRow(i)));
                logger.info("解析第二{}行数据。。", i);
            } catch (Exception e) {
                logger.error("解析sheet:{} 的第{}行出错。{}",sheet.getSheetName(), i, e);
            }
        }
        return list;
    }

    private static Map<String, Object> getDataRow(String[] title, XSSFRow row) throws Exception {
        Map<String, Object> rowData = Maps.newHashMap();
        String[] cellsData = getRowData(row);
//        if (cellsData.length > title.length) {
//            throw new Exception("数据内容和标题不符，请查看。");
//        }
        for(int i = 0; i < title.length; i++){
            try{
                rowData.put(title[i], cellsData[i]);
            }catch (Throwable ex){
                logger.debug("获取第{}行，title : {} 的内容出错，{}",row.getRowNum(),title[i], ex);
            }
        }
        return rowData;
    }

    /**
     * 获取行数据
     * @param row
     * @return
     */
    private static String[] getRowData(XSSFRow row) {
        String[] rowData = new String[row.getLastCellNum()];
        if (row == null) {
            logger.debug("第{}行无数据。", row.getRowNum());
            return null;
        }
        for (int i = row.getFirstCellNum(); i <= row.getLastCellNum(); i++) {
            if (row.getCell(i) == null) {
                continue;
            }
            try {
                rowData[i] = getValue(row.getCell(i));
            } catch (Exception ex) {
                ex.printStackTrace();
                logger.error("col : {}, row : {}, error : {}", i, row.getRowNum(),ex.getStackTrace());
            }
        }
        return rowData;
    }

    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell cell) {
        switch (cell.getCellType()) {
            //布尔类型
            case XSSFCell.CELL_TYPE_BOOLEAN :
                return String.valueOf(cell.getBooleanCellValue());
            //数值类型
            case XSSFCell.CELL_TYPE_NUMERIC :
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = cell.getDateCellValue();
                    return sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式:m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    return sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    CellStyle style = cell.getCellStyle();
                    DecimalFormat format = new DecimalFormat();
                    String temp = style.getDataFormatString();
                    // 单元格设置成常规
                    if (temp.equals("General")) {
                        format.applyPattern("#0.###");
                    }
                    return format.format(value);
                }
            case XSSFCell.CELL_TYPE_BLANK :
                return "";
            case XSSFCell.CELL_TYPE_ERROR :
                return "";
            case XSSFCell.CELL_TYPE_STRING :
                return String.valueOf(cell.getStringCellValue());
            default :
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                return String.valueOf(cell.getStringCellValue());
        }

    }
    
}