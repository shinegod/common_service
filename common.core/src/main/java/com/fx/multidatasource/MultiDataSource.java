package com.fx.multidatasource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import com.fx.dataSourceBean.model.DataSourceBean;
import com.fx.dataSourceBean.service.IDataSourceBeanService;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * 多数据源处理器
 * 
 * @author jason.jiang
 */
public class MultiDataSource extends AbstractRoutingDataSource implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(MultiDataSource.class);
	
	private static final String MYSQL = "MySQL";
	
	private static final String MYSQL_DRIVERCLASS = "com.mysql.jdbc.Driver";
	
	private static final Integer DEFAULT_DATASOURCE = 6;
    
    private static ApplicationContext applicationContext ;
    
    private String idleConnectionTestPeriod;
    
    private String idleMaxAge;
    
    private String maxConnectionsPerPartition;
    
	private String minConnectionsPerPartition;
	
	private String partitionCount;
	
	private String acquireIncrement;
	
	private String statementsCacheSize;
	
	private String releaseHelperThreads;
	
	public String getIdleMaxAge() {
		return idleMaxAge;
	}

	public void setIdleMaxAge(String idleMaxAge) {
		this.idleMaxAge = idleMaxAge;
	}

	public String getMaxConnectionsPerPartition() {
		return maxConnectionsPerPartition;
	}

	public void setMaxConnectionsPerPartition(String maxConnectionsPerPartition) {
		this.maxConnectionsPerPartition = maxConnectionsPerPartition;
	}

	public String getMinConnectionsPerPartition() {
		return minConnectionsPerPartition;
	}

	public void setMinConnectionsPerPartition(String minConnectionsPerPartition) {
		this.minConnectionsPerPartition = minConnectionsPerPartition;
	}

	public String getPartitionCount() {
		return partitionCount;
	}

	public void setPartitionCount(String partitionCount) {
		this.partitionCount = partitionCount;
	}

	public String getAcquireIncrement() {
		return acquireIncrement;
	}

	public void setAcquireIncrement(String acquireIncrement) {
		this.acquireIncrement = acquireIncrement;
	}

	public String getStatementsCacheSize() {
		return statementsCacheSize;
	}

	public void setStatementsCacheSize(String statementsCacheSize) {
		this.statementsCacheSize = statementsCacheSize;
	}

	public String getReleaseHelperThreads() {
		return releaseHelperThreads;
	}

	public void setReleaseHelperThreads(String releaseHelperThreads) {
		this.releaseHelperThreads = releaseHelperThreads;
	}

	public IDataSourceBeanService getiDataSourceBeanService() {
		return iDataSourceBeanService;
	}

	public void setiDataSourceBeanService(
			IDataSourceBeanService iDataSourceBeanService) {
		this.iDataSourceBeanService = iDataSourceBeanService;
	}

	public String getIdleConnectionTestPeriod() {
		return idleConnectionTestPeriod;
	}

	public void setIdleConnectionTestPeriod(String idleConnectionTestPeriod) {
		this.idleConnectionTestPeriod = idleConnectionTestPeriod;
	}

	@Autowired
	private IDataSourceBeanService iDataSourceBeanService;
    
	@Override
    public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
        super.setDataSourceLookup(dataSourceLookup);
    }
	
	//查找当前用户上下文变量中设置的数据源.
	@Override
     protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceId();
     }
	
	//设置默认的数据源
    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
    }
    
   //设置数据源集合.
    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    	applicationContext=ctx;
    }
    
    @Override
    public void afterPropertiesSet() {
    	logger.info("初始化数据源!");
        try {
            initailizeMutiDataSource();
        } catch (Exception e) {
        	logger.error("初始化数据源失败!!", e);
        }
        logger.info("数据源加入spring容器中成功!");
        super.afterPropertiesSet();
    }
    
	@SuppressWarnings("deprecation")
	private  void initailizeMutiDataSource() throws Exception{
	   DataSourceContextHolder.setDATASOURCEBEAN_MAP(iDataSourceBeanService.getDataSourceBeanMap(null));
	   DataSourceContextHolder.setDATASOURCENAME_MAP(iDataSourceBeanService.getDataSourceNameMap(null));
       DefaultListableBeanFactory acf  = (DefaultListableBeanFactory)applicationContext.getAutowireCapableBeanFactory();
       Map<Object,Object> bcpMap  = new HashMap<Object, Object>();
       List<DataSourceBean> dataSourceBeanList = iDataSourceBeanService.findAll();
       if (dataSourceBeanList != null && dataSourceBeanList.size() > 0) {
    	  for (DataSourceBean dataSourceBean : dataSourceBeanList) {
    		    BoneCPDataSource bcp = new BoneCPDataSource();
    		    if (dataSourceBean.getDatabaseType().equals(MYSQL)){
    		    	bcp.setDriverClass(MYSQL_DRIVERCLASS);
    		    	StringBuffer sb = new StringBuffer();
    		    	sb.append("jdbc:mysql://");
    		    	sb.append(dataSourceBean.getDataIp()+":" + dataSourceBean.getDatabasePort() + "/");
    		    	sb.append(dataSourceBean.getDataBaseName());
    		    	sb.append("?useUnicode=true&amp;characterEncoding=UTF-8&amp;autoReconnect=true&amp;failOverReadOnly=false");
	    		    bcp.setJdbcUrl(sb.toString());
    		    }
    	        bcp.setUsername(dataSourceBean.getDataUser());
    	        bcp.setPassword(dataSourceBean.getDataPass());
    		    bcp.setIdleConnectionTestPeriod(Integer.parseInt(idleConnectionTestPeriod));
    		    bcp.setIdleMaxAge(Integer.parseInt(idleMaxAge));
    		    bcp.setMaxConnectionsPerPartition(Integer.parseInt(maxConnectionsPerPartition));
    		    bcp.setMinConnectionsPerPartition(Integer.parseInt(minConnectionsPerPartition));
    		    bcp.setPartitionCount(Integer.parseInt(partitionCount));
    		    bcp.setAcquireIncrement(Integer.parseInt(acquireIncrement));
    		    bcp.setStatementCacheSize(Integer.parseInt(statementsCacheSize));
    		    bcp.setReleaseHelperThreads(Integer.parseInt(releaseHelperThreads));
    		    acf.registerSingleton(dataSourceBean.getId().toString(), bcp);
    		    bcpMap.put(dataSourceBean.getId(), bcp);
    	  }
    	  this.setTargetDataSources(bcpMap);
          setDefaultTargetDataSource(bcpMap.get(DEFAULT_DATASOURCE)); //设置默认数据源
       }
       logger.info("数据源初始化成功!!");
    }
}
