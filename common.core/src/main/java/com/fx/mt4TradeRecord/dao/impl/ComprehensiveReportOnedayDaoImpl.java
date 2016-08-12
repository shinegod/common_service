package com.fx.mt4TradeRecord.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mybatis.framework.core.dao.BaseDao;

import org.springframework.stereotype.Repository;

import com.fx.mt4TradeRecord.dao.IComprehensiveReportOnedayDao;
import com.fx.mt4TradeRecord.model.ComprehensiveReportOneday;

@Repository
public class ComprehensiveReportOnedayDaoImpl extends BaseDao<ComprehensiveReportOneday> implements IComprehensiveReportOnedayDao {

    public ComprehensiveReportOnedayDaoImpl() {
        super(IComprehensiveReportOnedayDao.class.getName());
    }

	@Override
	public List<ComprehensiveReportOneday> getByTimeLoginEarly(
			String interviewDate1, List<Integer> login) {
		Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate1 != ""  && interviewDate1.length() > 0 ) {
        	GregorianCalendar gc =new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                try {
                    Date date1 = simpleDateFormat.parse(interviewDate1);
                    gc.setTime(date1);
                    gc.add(5,-1);
                    map.put("date1", gc.getTime());
                } catch (Exception e) {
            }

        }else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                Date date1 = simpleDateFormat.parse(time1);
                map.put("date1", date1);
            } catch (Exception e) {
            }
        }
        for(Integer ss:login){
            int i = ss;
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        int pagenum =1 ;
        map.put("pagenum",pagenum);
        return findList("getByTimeLoginEarly", map);
	}

	@Override
	public List<ComprehensiveReportOneday> getByTimeLogin(
			String interviewDate1, String interviewDate2, List<Integer> login) {
		Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1 != "" && interviewDate2 != "" && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try {
                    Date date1 = simpleDateFormat.parse(interviewDate1);
                    Date date2 = simpleDateFormat3.parse(interviewDate2+" 23:59:59");
                    map.put("date1", date1);
                    map.put("date2", date2);
                } catch (Exception e) {
            }

        } else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                String time2 = simpleDateFormat2.format(date)+" 23:59:59";
                Date date1 = simpleDateFormat.parse(time1);
                Date date2 = simpleDateFormat3.parse(time2);
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        int pagenum =1 ;
        map.put("pagenum",pagenum);
        return findList("getByTimeLogin", map);
	}

    @Override
    public ComprehensiveReportOneday getByTimeLoginEarlyTotal(String interviewDate1, List<Integer> login) {
        Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate1 != ""  && interviewDate1.length() > 0 ) {
            GregorianCalendar gc =new GregorianCalendar();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            try {
                Date date1 = simpleDateFormat.parse(interviewDate1);
                gc.setTime(date1);
                gc.add(5,-1);
                map.put("date1", gc.getTime());
            } catch (Exception e) {
            }

        }else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                Date date1 = simpleDateFormat.parse(time1);
                map.put("date1", date1);
            } catch (Exception e) {
            }
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        int pagenum =1 ;
        map.put("pagenum",pagenum);
        return (ComprehensiveReportOneday)findOne("getByTimeLoginEarlyTotal", map);
    }

    @Override
    public ComprehensiveReportOneday getByTimeLoginTotal(String interviewDate1, String interviewDate2, List<Integer> login) {
        Map map = new HashMap();
        if (interviewDate1!=null&&interviewDate2!=null&&interviewDate1 != "" && interviewDate2 != "" && interviewDate1.length() > 0 && interviewDate2.length() > 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date1 = simpleDateFormat.parse(interviewDate1);
                Date date2 = simpleDateFormat3.parse(interviewDate2+" 23:59:59");
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }

        } else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            try {
                Date date = new Date();
                String time1 = simpleDateFormat.format(date)+"/01";
                String time2 = simpleDateFormat2.format(date)+" 23:59:59";
                Date date1 = simpleDateFormat.parse(time1);
                Date date2 = simpleDateFormat3.parse(time2);
                map.put("date1", date1);
                map.put("date2", date2);
            } catch (Exception e) {
            }
        }
        if(login!=null && login.size()>0){
            map.put("loginList",login);
        }
        int pagenum =1 ;
        map.put("pagenum",pagenum);
        return (ComprehensiveReportOneday)findOne("getByTimeLoginTotal", map);
    }
}