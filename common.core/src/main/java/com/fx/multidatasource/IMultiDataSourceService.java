package com.fx.multidatasource;

/**
 * 多数据源调用接口服务
 * 
 * @author jason.jiang
 */
public interface IMultiDataSourceService {
	
	/**
	 * 多数据源切换设置
	 * @param dataSourceId 数据源ID
	 */
	public void multiDataSourceSet(Integer dataSourceId); 
	
}
