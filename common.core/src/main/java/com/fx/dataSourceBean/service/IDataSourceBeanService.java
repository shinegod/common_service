package com.fx.dataSourceBean.service;

import com.fx.dataSourceBean.model.DataSourceBean;

import mybatis.framework.core.service.IValueObjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IDataSourceBeanService extends IValueObjectService<DataSourceBean> {
   
	public List<DataSourceBean> findAll();

	public DataSourceBean findDataSourceBeanById(int dataSourceBeanId);
	
	public Map<Integer, DataSourceBean> getDataSourceBeanMap(Map<String, Object> params);
	
	public Map<Integer, String> getDataSourceNameMap(Map<String, Object> params);

	public List<DataSourceBean> selectByMt4DatasourceTypeAndSourceWebSideType(int mt4DatasourceType,int sourceWebSideType);
	
	/**
	 * 获取所有的mysql真实数据源
	 * @param params
	 * @return
	 */
	public List<DataSourceBean> getDataSourceBeanByParams(
			HashMap<String, Object> params);
	
}