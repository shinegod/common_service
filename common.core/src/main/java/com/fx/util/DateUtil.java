package com.fx.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil extends DateUtils {

	/** */
	/**
	 * 计算两个时间之间相隔天数
	 * 
	 * @param startday
	 *            开始时间
	 * @param endday
	 *            结束时间
	 * @return
	 */
	public static int getIntervalDays(Date startday, Date endday) {
		// 确保startday在endday之前
		if (startday.after(endday)) {
			Date cal = startday;
			startday = endday;
			endday = cal;
		}
		// 分别得到两个时间的毫秒数
		long sl = startday.getTime();
		long el = endday.getTime();

		long ei = el - sl;
		// 根据毫秒数计算间隔天数
		return (int) (ei / (1000 * 60 * 60 * 24));
	}

	/**
	 * getCurrentTime 返回当前时间的字段串类型，格式:yyyy-MM-dd HH:mm:ss.S
	 * 
	 * @return String
	 * @exception
	 */
	public static String getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		java.util.Date date = new java.util.Date();
		String str = sdf.format(date);
		return str;
	}

	public static long getCurrentTimeZone() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getTimeZone().getRawOffset();
	}

	/**
	 * formatTime2yyyyMMddHHmmss 将时间从 yyyy-MM-dd HH:mm:ss.S 转换为 yyyyMMddHHmmss
	 * (这里描述这个方法适用条件 – 可选)
	 * 
	 * @param time
	 * @exception
	 */
	public static String formatTime2yyyyMMddHHmmss(String time) {
		if (StringUtils.isBlank(time)) {
			return null;
		}
		time = time.replace("-", "").replace(":", "");
		return time.substring(0, time.length() - 4);
	}

	public static String getYear() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		java.util.Date date = new java.util.Date();
		String str = sdf.format(date);
		return str;
	}

	public static String getMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		java.util.Date date = new java.util.Date();
		String str = sdf.format(date);
		return str;
	}

	public static String getDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		java.util.Date date = new java.util.Date();
		String str = sdf.format(date);
		return str;
	}

	public static String getDateFromyyyyMMdd(Date strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(strDate);
	}


	public static Date getDateFromyyyyMMdd(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	// 返回时间格式 yyyyMMddHHmmss
	public static String getDateyyyyMMddHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String newDate = null;
		newDate = sdf.format(date);
		return newDate;
	}

	public static String getDateyyyyMMddHHmmssS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String newDate = null;
		newDate = sdf.format(date);
		return newDate;
	}

	// 返回时间格式 yyyy-MM-dd HH:mm:ss.S
	public static String getDateyyyyMMddHHmmssSplit(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String newDate = null;
		newDate = sdf.format(date);
		return newDate;
	}

	// 获取GMT时间，i代表时区值 北京 8 莫斯科 3,date为要转的时间，如要获取当前时间，则传null
	public static String getGMTTime(Date date, int i) {
		String gmt = "GMT+" + i;
		Date date2 = new Date();
		if (date != null) {
			date2 = date;
		}
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(gmt)); // 设置时区为GMT
		return sdf.format(date2);
	}

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取时间偏移量
	 * 
	 * @param sourceDate
	 * @param targetTimeZone
	 * @return
	 */
	public static String dateTransformBetweenTimeZone(Date sourceDate,
			long targetTimeZone) {

		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

		Long targetTime = sourceDate.getTime() - timeZone.getRawOffset()
				+ targetTimeZone;// targetTimeZone.getRawOffset();
		return DateUtil.getTime(new Date(targetTime), formatter);
	}

	public static Date dateTransformBetweenTimeZoneDate(Date sourceDate,
			long targetTimeZone) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(sourceDate);
		// TODO 时区
		TimeZone timeZone = cal.getTimeZone();

		Long targetTime = sourceDate.getTime() - timeZone.getRawOffset()
				+ targetTimeZone;// targetTimeZone.getRawOffset();
		return new Date(targetTime);
	}

	public static String dateTransformBetweenTimeZone(Date sourceDate,
			DateFormat formatter, long sourceTimeZone, long targetTimeZone) {
		Long targetTime = sourceDate.getTime() - sourceTimeZone
				+ targetTimeZone;// targetTimeZone.getRawOffset();
		return DateUtil.getTime(new Date(targetTime), formatter);
	}

	public static Date dateTransformBetweenTimeZone(Date sourceDate,
			long sourceTimeZone, long targetTimeZone) {
		Long targetTime = sourceDate.getTime() - sourceTimeZone
				+ targetTimeZone;// targetTimeZone.getRawOffset();
		return new Date(targetTime);
	}

	public static String dateTransformBetweenTimeZone(Date sourceDate,
			DateFormat formatter, TimeZone sourceTimeZone, long targetTimeZone) {
		Long targetTime = sourceDate.getTime() - sourceTimeZone.getRawOffset()
				+ targetTimeZone;// targetTimeZone.getRawOffset();
		return DateUtil.getTime(new Date(targetTime), formatter);
	}

	public static String getTime(Date date, DateFormat formatter) {
		return formatter.format(date);
	}

	public static void main(String[] args) throws Exception {
		String dateTime = "2015-06-04 14:33:42.705";
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date date = formatter.parse(dateTime);

		Calendar cal = Calendar.getInstance();
		TimeZone timeZone = cal.getTimeZone();
		long destTimeZone = -28800000;// 28800000
		System.out.println(DateUtil.dateTransformBetweenTimeZone(date,
				formatter, timeZone, destTimeZone));
		System.out.println(DateUtil.dateTransformBetweenTimeZone(date,
				destTimeZone));
	}

	public static String dateTransformBetweenTimeZone(String startTime,
			String dateFormat, String userUTC, long currentTimeZone) {
      
		DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		Date date = null;
		try {
			date = formatter.parse(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Long targetTime = date.getTime() -currentTimeZone + Long.parseLong(userUTC);
		return DateUtil.getTime(new Date(targetTime), formatter);	
	}

	public static Date getDate(Long aLong) {
		Date date = new Date(aLong);
		return date;
	}

    public static Date getCurrentTime(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
