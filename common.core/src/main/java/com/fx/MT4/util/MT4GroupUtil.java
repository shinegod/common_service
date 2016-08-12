package com.fx.MT4.util;

import com.fx.MT4.enums.QueryMtT4GroupIdEnum;
import com.fx.MT4.vo.MT4GroupConfig;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.util.DateUtil;
import com.fx.util.MT4CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class MT4GroupUtil {
	//---- MT4 Server IP and manager account 
	// store it in MSSQL later
	private static Logger logger = LoggerFactory.getLogger(MT4GroupUtil.class);
	/**
	 * JNI调用DLL
	 * @author Dean
	 */
	public static MT4GroupConfig getGroupConfigByName(String groupName,QueryMtT4GroupIdEnum groupIdEnum){

		logger.info("---- Request Group Config:{} ", groupName, new Date());
		HashMap<String,MT4GroupConfig> groups = new HashMap<String,MT4GroupConfig>();
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			groups = DLLAgent.requestGroupConfig(groups, MT4Configuration.lgn, MT4Configuration.pwd, MT4Configuration.ipDemo) ;
		else
			groups = DLLAgent.requestGroupConfig(groups, MT4Configuration.lgn, MT4Configuration.pwd, MT4Configuration.ipLive) ;
		if(groups ==null){
			logger.error("---- Request Group config has no result");
		}else{

			MT4GroupConfig groupConfig=groups.get(groupName);
			if(groupConfig==null)
				logger.info("---- Request Groups config no result");
			else {
				logger.info("---- Request Groups {} config successful",groupConfig.getGroup());
				return groupConfig;
			}

		}
		return null ;
	}

	public static boolean isDemoGroup(int groupId){
		if (groupId == QueryMtT4GroupIdEnum.DEMO.getValue()) {
			return true;
		}
		return false;
	}


	public static void main(String args[]){
		//System.out.print(getGroupList(false).size());
		
//		Set<String> setGroup = MT4GroupUtil.getGroupList(false, );
	}

	public static int updateGroupConfigByName(MT4GroupConfig groupConfig, QueryMtT4GroupIdEnum groupIdEnum) {
		logger.info("---- Update Group Config:{} ", groupConfig.getGroup(), new Date());
		int res;
		if (groupIdEnum.getValue()==QueryMtT4GroupIdEnum.DEMO.getValue())
			res = DLLAgent.updateGeneralGroupConfig(groupConfig, MT4Configuration.lgn, MT4Configuration.pwd, MT4Configuration.ipDemo) ;
		else
			res = DLLAgent.updateGeneralGroupConfig(groupConfig, MT4Configuration.lgn, MT4Configuration.pwd, MT4Configuration.ipLive) ;
		if(res !=0){
			logger.error("---- Request Group config has no result");
		}
		return res ;
	}

	//todo 将获取mt4组的方法改为多数据源
	public static Set<String> getGroupList(boolean isDemoServer,DataSourceBean dataSourceBean){
		logger.info("---- Request Group Config by cache :{} ", DateUtil.getCurrentTime());
		return MT4CacheUtil.getGroupCache(isDemoServer, dataSourceBean);
	}

	public static Set<String> getGroupListByDll(boolean isDemoServer,DataSourceBean dataSourceBean){

		logger.info("---- Request Group Config begin :{} ", DateUtil.getCurrentTime());
		HashMap<String,MT4GroupConfig> groups = new HashMap<String,MT4GroupConfig>();
		if (isDemoServer)
			groups = DLLAgent.requestGroupConfig(groups, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4DemoIp()) ;
		else
			groups = DLLAgent.requestGroupConfig(groups,dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4LiveIp()) ;
		logger.info("---- Request Group Config end :{} ", DateUtil.getCurrentTime());
		if(groups ==null){
			logger.error("---- Request Group config has no result");
			return null ;
		}else{
			logger.info("---- Request Group list successful",new Date());
			// 去掉manager组选项
			groups.remove("manager");
			// 去掉IB返佣组选项
			String ibcomm = dataSourceBean.getMt4Group();
			String[] ibcommArr = ibcomm.split(";");
			for(String ibco : ibcommArr){
				groups.remove(ibco);
			}
			return groups.keySet();
		}

	}

	//包含IB组 不包含manager
	public static Set<String> getGroupListAndIb(boolean isDemoServer,DataSourceBean dataSourceBean){

		logger.info("---- Request Group Config:{} ",  new Date());
		HashMap<String,MT4GroupConfig> groups = new HashMap<String,MT4GroupConfig>();
		if (isDemoServer)
			groups = DLLAgent.requestGroupConfig(groups, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4DemoIp()) ;
		else
			groups = DLLAgent.requestGroupConfig(groups,dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4LiveIp()) ;
		if(groups ==null){
			logger.error("---- Request Group config has no result");
			return null ;
		}else{
			logger.info("---- Request Group list successful",new Date());
			// 去掉manager组选项
			groups.remove("manager");
			return groups.keySet();
		}

	}

	public static Set<String> getGroupWangList(boolean isDemoServer,DataSourceBean dataSourceBean){

		logger.info("---- Request Group Config:{} ",  new Date());
		HashMap<String,MT4GroupConfig> groups = new HashMap<String,MT4GroupConfig>();
		if (isDemoServer)
			groups = DLLAgent.requestGroupConfig(groups, dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4DemoIp()) ;
		else
			groups = DLLAgent.requestGroupConfig(groups,dataSourceBean.getMt4User(), dataSourceBean.getMt4Pass(),dataSourceBean.getMt4LiveIp()) ;
		if(groups ==null){
			logger.error("---- Request Group config has no result");
			return null ;
		}else{
			logger.info("---- Request Group list successful",new Date());
            
            return groups.keySet();
		}

	}
	
	
}
