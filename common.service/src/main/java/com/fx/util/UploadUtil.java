package com.fx.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.fx.config.ConfigProperties;
import com.fx.util.DateUtil;

public class UploadUtil {
	
	private static Logger logger = LoggerFactory.getLogger(UploadUtil.class);
	
	public static String FILE_UPLOAD_SUCCESS = "0";
	public static String FILE_SIZE_ERROR = "1";
	public static String FILE_FORMAT_ERROR = "2";
	public static String FILE_UPLOAD_ERROR = "3";
	public static int MAX_SIZE = 2097152;//单位B，共2MB
	
	
	@SuppressWarnings("serial")
	public static Map<String,Boolean> allowSuffixMap = new HashMap<String, Boolean>(){
		{
			put("jpg", true);
			put("png", true);
			put("pdf", true);
			put("doc", true);
			put("docx", true);
		}
	};
	
	public static String uploadFile(String originalFilename, byte[] bytes,Integer fileSize,String baseUploadPath) {
		String uploadUrl = "";
		try{
			//获取文件后缀
			String suffix = getSuffix(originalFilename);
			//判断上传的文件格式是否正确
			boolean matchSuffix = matchSuffix(suffix);
			if(matchSuffix){
				if(fileSize<=MAX_SIZE){
					uploadUrl = getUploadUrl(originalFilename);
					String uploadPath = baseUploadPath + uploadUrl;
					logger.info("######uploadFile,uploadPath:{}",uploadPath);
					File filePath = new File(uploadPath);
					File parent = filePath.getParentFile(); 
					logger.info("######uploadFile,uploadPath parent:{}",parent);
					if(parent!=null && !parent.exists()){
						parent.mkdirs();
						logger.info("######uploadFile,parent.mkdirs");
					} 
					//文件开始上传到服务器上
					FileCopyUtils.copy(bytes, filePath);
					return "/" + getFileUploadBaseUrl() + uploadUrl;
				} else {
					uploadUrl = FILE_SIZE_ERROR;//文件大小超过最大上限
				}
			}else {
				uploadUrl = FILE_FORMAT_ERROR;//文件格式不对
			}
		}catch (Exception ex) {
			ex.printStackTrace();
			uploadUrl = FILE_UPLOAD_ERROR;//上传文件异常
		}
		return uploadUrl;
	}
	
	public static boolean matchSuffix(String suffix) {
		if (allowSuffixMap.containsKey(suffix.toLowerCase())) {
			return true;
		}
		return false;
	}
	
	public static String getSuffix(String originalFilename) {
		String[] suffixs=originalFilename.split("\\.");
		String suffix = suffixs[suffixs.length-1];
		return suffix;
	}

	public static String getUploadUrl(String originalFilename) {
		String uploadPath = "/"+DateUtil.getYear()+"/"+DateUtil.getMonth()+"/"+DateUtil.getDay()+"/"+System.currentTimeMillis()+ originalFilename;
		return uploadPath;
	}
	
	public static String getFileUploadBaseUrl(){
		return ConfigProperties.getProperty("file.upload.base.url");
	}
}
