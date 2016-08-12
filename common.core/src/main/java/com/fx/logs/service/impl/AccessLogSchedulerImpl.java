package com.fx.logs.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fx.logs.model.AccessLogs;
import com.fx.logs.service.IAccessLogScheduler;
import com.fx.logs.service.IAccessLogsService;
import com.fx.util.Config;

/**
 * 访问日志定时调度
 * 
 * @author jason.jiang
 */
@Service("IAccessLogScheduler")
public class AccessLogSchedulerImpl implements IAccessLogScheduler{
	
	private static final Logger logger = LoggerFactory.getLogger(AccessLogSchedulerImpl.class);

	private static final String PERFIX = "rc-";
	
	private static final String ENDFIX = ".log";
	
	//private static final String LOGPATH = "D:/";
	
	private static final String LOGPATH = Config.getConfig("LOG_PATH");
	
	private static final String ENCODING = "GBK";
	
	@Autowired
	IAccessLogsService iAccessLogsService;
	
	/**
	 * 调度执行日志收集入口
	 */
	@SuppressWarnings("resource")
	public void executeAccessLogs() {
		logger.debug("日志收集调度任务执行开始 !!");
		String dataString = getDateString("yyyyMMdd");
		if (dataString != null && !("").equals(dataString)){
			for (int i = 0; i < 24; i++) {
				  StringBuffer sb = new StringBuffer();
				  if (i < 10) {
					  sb.append(PERFIX).append(dataString).append("0").append(i).append(ENDFIX);
				  } else {
					  sb.append(PERFIX).append(dataString).append(i).append(ENDFIX);
				  }
		     try {
				  File file = new File(LOGPATH + sb.toString());
				  if(file.isFile() && file.exists()) {
					    InputStreamReader read = new InputStreamReader(new FileInputStream(file), ENCODING); 
						BufferedReader bufferedReader = new BufferedReader(read);
						String lineTxt = null;
						while((lineTxt = bufferedReader.readLine()) != null) {
							String [] arr = lineTxt.split("%%%%%");
							if (arr.length >= 13) {
								AccessLogs accessLogs = new AccessLogs();
								accessLogs.setAccessIp(arr[0]);
								String [] accesstimes = arr[1].split(" ");
								if (accesstimes != null && accesstimes.length >= 2) {
									accessLogs.setAccessTime(getTime_local_Date(accesstimes[0]));
								}
								accessLogs.setAccessReferer(arr[3]);
								accessLogs.setTrackerId(arr[12]);
								accessLogs.setAccessBrowser(arr[13]);
								accessLogs.setDomain(getHttp_referer_domain(arr[6]));
								accessLogs.setTitle(arr[7]);
					    		accessLogs.setWebId(arr[9]);
					    		accessLogs.setUserName(arr[10]);
					    		accessLogs.setAccessOperation(arr[11]);
					    		accessLogs.setUserCookie(arr[8]);
							    iAccessLogsService.saveAccessLogs(accessLogs);
							}
						}
					} else {
						logger.debug("系统找不到" + sb.toString() + "日志文件 !!");
					}
				  } catch (Exception e) {
					  logger.error("——调度执行日志收集错误!!", e);
				  }
			  }
		}
		logger.debug("日志收集调度任务执行结束 !!");
	}
	
	/**
	 * @return 返回当前日期字符串
	 */
	private String getDateString(String format) {
		if (format == null || ("").equals(format)) {
			return null;
		}
		String dateString = null;
		try {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			dateString = formatter.format(currentTime);
		} catch (Exception e){
			logger.error("——返回当前日期字符串错误!!", e);
		}
		return dateString;
	}
	
	private String getHttp_referer_domain(String http_referer) {
		if (http_referer.length() < 8) {
			return http_referer;
		}
		String str = http_referer.replace("\"", "").replace("http://", "").replace("https://", "");
		return str.indexOf("/") > 0 ? str.substring(0, str.indexOf("/")) : str;
	}
	
	
	private String getTime_local_Date(String time_local) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.US);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy.MM.dd:HH:mm:ss", Locale.US);
		return df1.format(df.parse(time_local));
	}
	
	@SuppressWarnings("unused")
	private String getArgValue (String arg) {
		if (arg == null && ("").equals(arg)) {
			return null;
		}
		String value = null;
		try {
			String [] temp = arg.split("=");
			if (temp.length >= 2) {
				value = URLDecoder.decode(temp[1], ENCODING);
			}
		} catch(Exception e) {
			logger.error("——获取request值错误!!", e);
		}
		return value;
	}
}
