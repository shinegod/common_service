package com.fx.report;

import com.fx.bbookuserReport.model.BbookuserReport;
import com.fx.bbookuserReport.service.IBbookuserReportService;
import com.fx.config.ConfigProperties;
import com.fx.mt4TradeRecord.service.IMt4TradesService;
import com.fx.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/10/15.
 */
public class BbookProfitReport {

    //从MySql中取出相应数据，插入到SQLServer中
    public static void getProfitInsertBbook(ApplicationContext context){

        IMt4TradesService mt4TradesService = context.getBean(IMt4TradesService.class);
        IBbookuserReportService bbookuserReportService = context.getBean(IBbookuserReportService.class);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = DateUtil.addDays(date,-1);
        String sqldate = sdf.format(date);
        String startDate = sdf.format(date2)+" 00:00:00";
        String endDate = sdf.format(date2)+" 23:59:59";
        Map map = new HashMap();
        map.put("startDate",startDate);
        map.put("endDate", endDate);
        //添加SYMBOL
        map.put("exchange",ConfigProperties.getProperty("trades.exchange"));
        map.put("gold",ConfigProperties.getProperty("trades.gold"));
        map.put("silver",ConfigProperties.getProperty("trades.silver"));
        map.put("A50",ConfigProperties.getProperty("trades.A50"));
        map.put("oil",ConfigProperties.getProperty("trades.oil"));
        List<BbookuserReport> bbookuserReportList = mt4TradesService.selectBbookProfit(map);
        for(BbookuserReport bbookuserReport: bbookuserReportList){
            bbookuserReport.setCreateDate(sqldate);
            bbookuserReportService.doInsert(bbookuserReport);
        }
    }

    //比较
    public static void BbokUserCompare(ApplicationContext context){
        IBbookuserReportService bbookuserReportService = context.getBean(IBbookuserReportService.class);
        List<Integer> loginList = new ArrayList<>();
        List<BbookuserReport> downLoadbbookuserReportList = new ArrayList<>();
        double compareA =0;
        double compareB =0;
        Date date = new Date();
        Date date2 = DateUtil.addDays(date, -1);   //昨天时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String createDate = sdf.format(date);
        String oldDate = sdf.format(date2);
        List<BbookuserReport> oldbbookuserReportList = bbookuserReportService.selectBycreateDate(oldDate);
        if(oldbbookuserReportList!=null&&oldbbookuserReportList.size()>0){
            List<BbookuserReport> bbookuserReportList = bbookuserReportService.selectBycreateDate(createDate);
            BbookuserReport oldBbookuserReport = new BbookuserReport();
            for(BbookuserReport bbookuserReport: bbookuserReportList){
                oldBbookuserReport = bbookuserReportService.selectByLoginAndDate(bbookuserReport.getLogin(),oldDate);
                if(oldBbookuserReport!=null){
                    compareA = bbookuserReport.getOldEquity() - oldBbookuserReport.getOldEquity();  //基数A
                    compareB = bbookuserReport.getExchange()*3 +bbookuserReport.getGold()*3
                            +bbookuserReport.getSilver()*3+bbookuserReport.getA50()*3
                            +bbookuserReport.getOil()*3;                                            //基数B
                    if(compareB>compareA){
                        loginList.add(bbookuserReport.getLogin());
                        downLoadbbookuserReportList.add(bbookuserReport);
                    }
                }
            }
            profitDownload(downLoadbbookuserReportList);
        }
    }

    public static void profitDownload(List<BbookuserReport> bbookuserReportList) {
        if(bbookuserReportList!=null&&bbookuserReportList.size()>0){
            String[] USER_RECORES_COLUMNS = new String[]{
                    "MT4账号_#_3000",
                    "入金_#_3000",
                    "出金_#_3000",
                    "外汇_#_3000",
                    "黄金_#_3000",
                    "白银_#_3000",
                    "石油_#_3000",
                    "A50_#_3000",
                    "时间_#_3000"
            };
            String[] USER_RECORES_FIELDS = new String[]{
                    "login","deposit","withdraw","exchange","gold","silver","oil","exchange","a50"
            };
            try {
                String path ="C:/Users/Administrator/Desktop/profitReport/bbookuser_profit_excel_"+ DateUtil.getDateyyyyMMddHHmmss(new Date()) + ".xls";
                FileOutputStream outf = new FileOutputStream(path);
                HSSFWorkbook workbook = new HSSFWorkbook();
                ExcelUtil excelUtil = new ExcelUtil();
                HSSFSheet sheet = excelUtil.creatAuditSheet(workbook, "report_order_excel", bbookuserReportList, USER_RECORES_COLUMNS, USER_RECORES_FIELDS);
                ByteArrayInputStream byteinputStream = (ByteArrayInputStream) ExcelUtil.writeExcelToStream(workbook, sheet);
                byte[] b = new byte[1024];
                while((byteinputStream.read(b)) != -1){
                    outf.write(b);
                }
                byteinputStream.close();
                outf.close();
                //ExcelOperateUtil.downloadExcel(byteinputStream, response, "bbookuser_profit_excel_" + DateUtil.getDateyyyyMMddHHmmss(new Date()) + ".xls");
            } catch (NoSuchMethodException | IllegalAccessException
                    | IllegalArgumentException | InvocationTargetException |IOException e ) {
                e.printStackTrace();
            }
        }

    }

/*    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        getProfitInsertBbook(context);
        BbokUserCompare(context);
    }*/
/*    public void start(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext.xml");
        getProfitInsertBbook(context);
        BbokUserCompare(context);
    }*/
}
