package com.fx.multidatasource;

/**
 * 多数据源连接调用服务
 * 
 * @author jason.jiang
 */
public class MultiDataSourceService extends MultidatasourceComm{

	/**
	 * 多数据源切换调用设置
	 */
	@Override
	public void multiDataSourceSet(Integer dataSourceId) {
		DataSourceContextHolder.setDataSourceId(dataSourceId);
	}
}
