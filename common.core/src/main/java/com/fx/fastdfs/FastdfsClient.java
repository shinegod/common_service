package com.fx.fastdfs;

import java.io.InputStream;
import org.csource.fastdfs.UploadStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 创建fastDFS服务
 * 
 * @author jason.jiang
 */
public class FastdfsClient extends FastdfsComm{

	private static final Logger logger = LoggerFactory.getLogger(FastdfsClient.class);
	
	/**
	 * 上传文件url
	 */
	public static String upload_file(String url) {
		if (url == null && ("").equals(url)) {
			return null;
		}
		String fastDfsUrl = null;
		try {
			String ext = url.substring(url.lastIndexOf(".") + 1);
			fastDfsUrl = getStorageClient().upload_file1(url, ext, null);
		} catch (Exception e) {
			logger.error("上传文件url类型错误!!", e);
		}
		return fastDfsUrl;
	}
	
	/**
	 * 上传字节流
	 */
	public static String upload_file(byte[] bytes, String ext) {
		if (ext == null || ("").equals(ext) || bytes == null || bytes.length > 0) {
			return null;
		}
		String fastDfsUrl = null;
		try {
			fastDfsUrl = getStorageClient().upload_file1(bytes, ext, null);
		} catch (Exception e) {
			logger.error("上传文件字节流类型错误!!", e);
		}
		return fastDfsUrl;
	}
	
	/**
	 * 上传文件流
	 */
	public static String upload_file(InputStream fis, long file_size, String ext) {
		if (fis == null || file_size == 0 || ext == null || ("").equals(ext)) {
			return null;
		}
		String fastDfsUrl = null;
		try {
			UploadStream stream = new UploadStream(fis, file_size);
			fastDfsUrl = getStorageClient().upload_file1(null, file_size, stream, ext, null);
		} catch (Exception e) {
			logger.error("上传文件流类型错误!!", e);
		}
		return fastDfsUrl;
	}
	
	/**
	 * 删除文件
	 * 
	 * @param fileId 删除文件(storage返回的路径)
	 * @return 删除是否成功
	 */
	public static int delete_file(String fileId) {
		if (fileId == null || ("").equals(fileId)) {
			return 0;
		}
		int deleflag = 0;
		try {
			deleflag = getStorageClient().delete_file1(fileId);
		} catch (Exception e) {
			logger.error("删除文件错误!!", e);
		}
		return deleflag;
	}
	
}
