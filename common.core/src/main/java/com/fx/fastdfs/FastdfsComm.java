package com.fx.fastdfs;

import java.io.IOException;
import java.net.InetSocketAddress;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fx.util.Config;

/**
 * fastdbs common
 * 
 * @author jason.jiang
 */
public abstract class FastdfsComm {
	
	private static final Logger log = LoggerFactory.getLogger(FastdfsComm.class);
	
	private static final ThreadLocal<TrackerGroup> trackerServerHolder = new ThreadLocal<TrackerGroup>();  
	
	private static final String TRACK_SERVER_CHART = Config.getConfig("tracker_server_chart");
	
	private static final String TRACK_SERVER_IP = Config.getConfig("tracker_server_ip");
	
	private static final String TRACK_SERVER_PORT = Config.getConfig("tracker_server_port");
	
	private static final String STORAGE_SERVER_GROUP = Config.getConfig("storage_server_group");
	
	private static final String STORAGE_SERVER_PORT = Config.getConfig("storage_server_port");
	
	private static final String STORAGE_SERVER_M00 = Config.getConfig("storage_server_m00");
	
	
	/**
	 * 创建tracker组
	 * 
	 * @return 返回tracker组
	 */
	private static TrackerGroup creatTrackerGroup() {
		TrackerGroup TrackerGroup = null;
		try{
			if (trackerServerHolder.get() == null) {
				ClientGlobal.setG_charset(TRACK_SERVER_CHART);
				TrackerGroup = new TrackerGroup(new InetSocketAddress[]{new InetSocketAddress(TRACK_SERVER_IP, Integer.parseInt(TRACK_SERVER_PORT))});
				trackerServerHolder.set(TrackerGroup);
				log.debug("——生成连接服务——");
			} else {
				TrackerGroup = trackerServerHolder.get();
				log.debug("——本地变量中获取连接服务——");
			}
		} catch(Exception e) {
			log.error("创建tracker组错误!!", e);
		}
		return TrackerGroup;
	}
	
	/**
	 * 根据tracker组得到tracker客户端
	 * 
	 * @return 返回tracker客户端
	 */
	private static TrackerClient getTrackerClient(TrackerGroup trackerGroup) {
		TrackerClient trackerClient = null;
		if (trackerGroup != null) {
			trackerClient = new TrackerClient(trackerGroup);
		}
		log.debug("——tracker client生成——");
		return trackerClient;
	}
	
	/**
	 * 根据tracker客户端得到tracker服务
	 * 
	 * @return 返回tracker服务
	 */
	private static TrackerServer getTrackerServer(TrackerClient trackerClient) {
		TrackerServer trackerServer = null;
		if (trackerClient != null) {
			try {
					trackerServer = trackerClient.getConnection();
			} catch (IOException e) {
				log.error("根据tracker客户端得到tracker服务错误!!", e);
			}
		}
		log.debug("——tracker server生成");
		return trackerServer;
	}
	
	/**
	 * 得到Storage服务
	 * 
	 * @param trackerClient
	 * @param trackerServer
	 * @return 返回Storage服务
	 */
	private static StorageServer getStorageServer(TrackerClient trackerClient, TrackerServer trackerServer) {
		StorageServer storageServer = null;
		if (trackerClient != null && trackerServer != null) {
			try {
				storageServer = trackerClient.getStoreStorage(trackerServer);
			} catch (IOException e) {
				log.error("得到Storage服务错误!!", e);
			}
		}
		log.debug("——storage server生成");
		return storageServer;
	}
	
	/**
	 * 得到Storage服务
	 * 
	 * @param storageIp
	 * @return 返回Storage服务
	 */
	private static StorageServer getStorageServer(String storageIp) {
		StorageServer storageServer = null;
		if (storageIp != null && !("").equals(storageIp)) {
			try {
				storageServer = new StorageServer(storageIp, Integer.parseInt(STORAGE_SERVER_PORT), Integer.parseInt(STORAGE_SERVER_M00));
			} catch (IOException e) {
				log.error("得到Storage服务错误!!", e);
			}
		}
		log.debug("——storage server生成");
		return storageServer;
	}
	
	/**
	 * 获得可用的storage IP
	 * 
	 * @param trackerClient
	 * @param trackerServer
	 * @return 返回storage IP
	 */
	private static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
		String storageIp = null;
		if (trackerClient != null && trackerServer != null) {
			try {
				StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, STORAGE_SERVER_GROUP);
				storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
			} catch (IOException e) {
				log.error("得到Storage Ip错误!!", e);
			}
		}
		log.debug("——获取组中可用的storage IP——" + storageIp);
		return storageIp;
	}
	
	/**
	 * 获得Storage客户端
	 * 
	 * @param trackerServer
	 * @param storageServer
	 * @return 返回Storage客户端
	 */
	public static StorageClient1 getStorageClient() {
		StorageClient1 storageClient = null;
		try {
			TrackerGroup trackerGroup = creatTrackerGroup();
			TrackerClient trackerClient = getTrackerClient(trackerGroup);
			TrackerServer trackerServer = getTrackerServer(trackerClient);
			//String storageIp = getStorageServerIp(trackerClient, trackerServer);
			//StorageServer storageServer = getStorageServer(storageIp);
			StorageServer storageServer = getStorageServer(trackerClient, trackerServer);
			storageClient = new StorageClient1(trackerServer, storageServer);
		} catch (Exception e) {
			log.error("获得Storage客户端错误!!", e);
		}
		log.debug("——storage client生成——");
		return storageClient;
	}
}
